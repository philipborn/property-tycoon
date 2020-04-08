package com.watson.propert.tycoon.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.google.common.eventbus.EventBus;
import com.watson.propert.tycoon.game.bord.*;
import com.watson.propert.tycoon.game.events.BuyOrNotMsg;
import com.watson.propert.tycoon.game.events.ChangePlayerEvent;
import com.watson.propert.tycoon.game.events.PropertyEvent;
import com.watson.propert.tycoon.game.rules.*;
import com.watson.propert.tycoon.io.BoardReaderJson;

public class Game implements PropertTycoon {

  protected static final int START_CASH = 200;

  private GameMaster master;
  private Square bord;
  private DicePair dicePair;
  private EventBus channel;
  private Game.state state;

  public Game(Square startPostion, EventBus channel) {
    List<Player> players = new ArrayList<>();
    players.add(new Player(Player.Id.ONE, startPostion, channel));
    players.add(new Player(Player.Id.TWO, startPostion, channel));
    players.add(new Player(Player.Id.THREE, startPostion, channel));
    players.add(new Player(Player.Id.FOUR, startPostion, channel));
    players.add(new Player(Player.Id.FIVE, startPostion, channel));
    players.add(new Player(Player.Id.SIX, startPostion, channel));
    master = new GameMaster(players);
    state = new NewTurn();
    bord = startPostion;
    dicePair = new DicePair(channel);
    this.channel = channel;
  }

  @Override
  public void send(PlayerAction playerAction) {
    state.handle(playerAction);
  }

  @Override
  public Optional<PropertyInfo> propertInfo(int squareNum) {
    return PropertyInfo.getInfo(bord.move(squareNum - 1));
  }

  @Override
  public int startCash() {
    return START_CASH;
  }

  @Override
  public void registerListener(Object listener) {
    channel.register(listener);
  }

  @Override
  public void unregisterListener(Object listener) {
    channel.unregister(listener);
  }

  private void switchTo(Game.state nextState) {
    this.state.exit();
    nextState.entry();
    this.state = nextState;
  }

  public static PropertTycoon newGame() {
    BoardReaderJson br = new BoardReaderJson();
    br.readFile("src/main/resources/boardDataJSON.json");
    EventBus channel = new EventBus();
    Square first = BordBuilder.with(channel).addFrom(BoardSource.using(br)).getBord();
    PropertTycoon game = new Game(first, channel);
    return game;
  }

  public interface state {

    default void entry() {}

    default void exit() {}

    void handle(PlayerAction playerAction);
  }

  private class NewTurn implements Game.state {

    @Override
    public void entry() {
      channel.post(new ChangePlayerEvent(master.newTurn().id));
    }

    @Override
    public void handle(PlayerAction playerAction) {
      if (playerAction instanceof PlayerAction.ThrowDices) {
        throwDicesAndMove();
      }
    }

    private void throwDicesAndMove() {
      Player currentPlayer = master.currentPlayer();
      Integer sum = dicePair.throwDices().stream().mapToInt((a) -> a).sum();
      Square newLocation = currentPlayer.move(sum);
      try {
        newLocation.vist(new AfterMove(currentPlayer));
        if (PropertyCanBought.by(currentPlayer)) {
          switchTo(new NoOwner());
        } else {
          switchTo(new FixProperty());
        }
      } catch (NoCashException e) {
        if (currentPlayer.totalValue() > e.needToPay()) {
          removeFromGame(currentPlayer);
          switchTo(new NewTurn());
        } else {
          switchTo(new NoCash(e.needToPay()));
        }
      }
    }
  }

  private void removeFromGame(Player player) {
    Bankruptcy rule = new Bankruptcy(master);
    rule.forPlayer(player);
  }

  private class FixProperty implements Game.state {

    @Override
    public void handle(PlayerAction playerAction) {
      if (playerAction instanceof PlayerAction.DonePropertyUpgrade) {
        donePropertyManagement();
      }
    }

    public void donePropertyManagement() {
      switchTo(new NewTurn());
    }
  }

  private class NoOwner implements Game.state {

    @Override
    public void entry() {
      Player current = master.currentPlayer();
      Property prop = (Property) current.postion();
      String propertyName = prop.name();
      int price = prop.value();
      channel.post(new BuyOrNotMsg(propertyName, price));
    }

    @Override
    public void handle(PlayerAction playerAction) {
      if (playerAction instanceof PlayerAction.BuyProperty) {
        buyProperty();
      } else if (playerAction instanceof PlayerAction.Auction) {
        notBuyingProperty();
      }
    }

    private void buyProperty() {
      Player player = master.currentPlayer();
      Square property = player.postion();
      property.vist(new BuyProperty(player));
      channel.post(new PropertyEvent(property.name(), player.id));
      switchTo(new FixProperty());
    }

    private void notBuyingProperty() {
      switchTo(new FixProperty());
    }
  }

  class NoCash implements Game.state {

    private int needToFree;

    private NoCash(int needToFree) {
      this.needToFree = needToFree;
    }

    @Override
    public void handle(PlayerAction playerAction) {
      if (playerAction instanceof PlayerAction.Mortgaged) {
        mortgaged((PlayerAction.Mortgaged) playerAction);
      } else if (playerAction instanceof PlayerAction.RemoveHouse) {
        sellHouse((PlayerAction.RemoveHouse) playerAction);
      }
    }

    private void mortgaged(PlayerAction.Mortgaged msg) {
      Player player = master.currentPlayer();
      ToMorgade rule = new ToMorgade(player);
      rule.morgade(bord.moveTo(msg.propertyName));
      if (player.cash() > needToFree) {
        switchTo(new FixProperty());
      }
    }

    private void sellHouse(PlayerAction.RemoveHouse msg) {
      Square street = bord.moveTo(msg.streetName);
      new ToSellHouses(master.currentPlayer()).sellHouses(street, msg.houseRemoved);
    }
  }
}
