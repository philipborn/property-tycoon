package com.watson.propert.tycoon.game;

import java.util.*;

import com.google.common.eventbus.EventBus;
import com.watson.propert.tycoon.game.bord.*;
import com.watson.propert.tycoon.game.events.BuyOrNotMsg;
import com.watson.propert.tycoon.game.events.ChangePlayerEvent;
import com.watson.propert.tycoon.game.events.PlayerInDebtEvent;
import com.watson.propert.tycoon.game.events.PropertyEvent;
import com.watson.propert.tycoon.game.rules.*;
import com.watson.propert.tycoon.io.BoardReaderJson;

public class Game implements PropertTycoon {

  protected static final int START_CASH = 1500;

  private GameMaster master;
  private Square bord;
  private DicePair dicePair;
  private EventBus channel;
  private Game.state state;
  private Player player;
  private Deque<NoCashException> debs = new ArrayDeque<>();

  public Game(Square startPostion, EventBus channel) {
    List<Player> players = new ArrayList<>();
    players.add(new Player(Player.Id.ONE, startPostion, channel));
    players.add(new Player(Player.Id.TWO, startPostion, channel));
    players.add(new Player(Player.Id.THREE, startPostion, channel));
    players.add(new Player(Player.Id.FOUR, startPostion, channel));
    players.add(new Player(Player.Id.FIVE, startPostion, channel));
    players.add(new Player(Player.Id.SIX, startPostion, channel));
    master = new GameMaster(players);
    player = master.currentPlayer();
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
      player = master.newTurn();
      channel.post(new ChangePlayerEvent(player.getId()));
    }

    @Override
    public void handle(PlayerAction playerAction) {
      if (playerAction instanceof PlayerAction.ThrowDices) {
        throwDicesAndMove();
      }
    }

    private void throwDicesAndMove() {
      Integer sum = dicePair.throwDices().stream().mapToInt((a) -> a).sum();
      Square newLocation = player.move(sum);
      try {
        newLocation.vist(new AfterMove(player));
        if (PropertyCanBought.by(player)) {
          switchTo(new NoOwner());
        } else {
          switchTo(new FixProperty());
        }
      } catch (NoCashException e) {
        if (player.totalValue() > e.amount()) {
          removeFromGame(player);
          switchTo(new NewTurn());
        } else {
          debs.addFirst(e);
          switchTo(new NoCash());
        }
      }
    }
  }

  private class FixProperty implements Game.state {

    @Override
    public void handle(PlayerAction playerAction) {
      if (playerAction instanceof PlayerAction.DonePropertyUpgrade) {
        donePropertyManagement();
      }
    }
  }

  private class NoOwner implements Game.state {

    @Override
    public void entry() {
      Property prop = (Property) player.postion();
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
  }

  class NoCash implements Game.state {

    private CashUser payTo;
    private int needToFree;

    @Override
    public void entry() {
      setUpDebtCollection(debs.pop());
    }

    @Override
    public void exit() {
      player = master.currentPlayer();
      channel.post(new ChangePlayerEvent(player.getId()));
    }

    @Override
    public void handle(PlayerAction playerAction) {
      if (playerAction instanceof PlayerAction.Mortgaged) {
        mortgaged((PlayerAction.Mortgaged) playerAction);
      } else if (playerAction instanceof PlayerAction.SellHouse) {
        sellHouse((PlayerAction.SellHouse) playerAction);
      } else if (playerAction instanceof PlayerAction.SellProperty) {
        sellProperty((PlayerAction.SellProperty) playerAction);
      }
      selectNextState();
    }

    private void selectNextState() {
      if (player.cash() >= needToFree) {
        player.payTo(payTo, needToFree);
        if (debs.isEmpty()) {
          switchTo(new FixProperty());
        } else {
          setUpDebtCollection(debs.pop());
        }
      }
    }

    private void setUpDebtCollection(NoCashException e) {
      player = (Player) e.needToPay();
      payTo = e.payTo();
      needToFree = e.amount();
      channel.post(new ChangePlayerEvent(player.getId()));
      channel.post(new PlayerInDebtEvent(player.getId(), needToFree));
    }
  }

  private void removeFromGame(Player player) {
    Bankruptcy rule = new Bankruptcy(master);
    rule.forPlayer(player);
  }

  public void donePropertyManagement() {
    switchTo(new NewTurn());
  }

  private void buyProperty() {
    Square property = player.postion();
    property.vist(new BuyProperty(player));
    channel.post(new PropertyEvent(property.name(), player.id));
    switchTo(new FixProperty());
  }

  private void notBuyingProperty() {
    switchTo(new FixProperty());
  }

  private void mortgaged(PlayerAction.Mortgaged msg) {
    ToMorgade rule = new ToMorgade(player);
    rule.morgade(bord.moveTo(msg.propertyName));
  }

  private void sellHouse(PlayerAction.SellHouse msg) {
    Square street = bord.moveTo(msg.streetName);
    new ToSellHouses(player).sellHouses(street);
  }

  private void sellProperty(PlayerAction.SellProperty msg) {
    Optional<Property> property =
            Optional.of(bord.moveTo(msg.squareName))
                    .filter(Property.class::isInstance)
                    .map(Property.class::cast);
    boolean playerIsOwner =
            property.flatMap(Property::owner).filter((owner -> owner.equals(player))).isPresent();
    if (playerIsOwner) {
      property.ifPresent(Property::sell);
    }
  }
}
