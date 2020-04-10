package com.watson.propert.tycoon.game;

import java.util.*;
import java.util.stream.Collectors;

import com.google.common.eventbus.EventBus;
import com.watson.propert.tycoon.game.bord.*;
import com.watson.propert.tycoon.game.events.*;
import com.watson.propert.tycoon.game.rules.*;
import com.watson.propert.tycoon.io.BoardReaderJson;

public class Game implements PropertTycoon {

  protected static final int START_CASH = 1500;

  private GameMaster master;
  private GameEnd gameEnd = new GameEnd() {};
  private Square bord;
  private DicePair dicePair;
  private EventBus channel;
  private State state;
  private Player player;
  private Deque<NoCashException> debs = new ArrayDeque<>();

  public Game(Square startPostion, EventBus channel) {
    master = new GameMaster();
    state = new waitOnStart();
    bord = startPostion;
    dicePair = new DicePair(channel);
    this.channel = channel;
  }

  @Override
  public void send(PlayerAction playerAction) {
    state.handle(playerAction);
  }

  public void startGame(GameSetting settings) {
    List<Player> players = new ArrayList<>();
    settings.getPlayers().forEach((id) -> players.add(new Player(id, bord, channel)));
    master.newGame(players);
    player = master.currentPlayer();
    state = new NewTurn();
  }

  @Override
  public Optional<PropertyInfo> propertInfo(int squareNum) {
    return PropertyInfo.getInfo(bord.forward(squareNum - 1));
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

  private void switchTo(State nextState) {
    if (master.numActivePlayers() <= 1 || gameEnd.isGameDone()) {
      nextState = new GameOver();
    }
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
    GameSetting settings = new GameSetting();
    settings.set(Player.Id.ONE);
    settings.set(Player.Id.TWO);
    settings.set(Player.Id.THREE);
    settings.set(Player.Id.FOUR);
    settings.set(Player.Id.FIVE);
    settings.set(Player.Id.SIX);
    game.startGame(settings);
    return game;
  }

  public interface State {

    default void entry() {}

    default void exit() {}

    void handle(PlayerAction playerAction);
  }

  private class NewTurn implements State {

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
      int sum = dicePair.throwDices().stream().mapToInt((a) -> a).sum();
      Square newLocation = player.move(sum);
      try {
        newLocation.visitBy(new AfterMove(player));
        if (PropertyCanBought.by(player)) {
          switchTo(new NoOwner());
        } else if (CanFixProperty.forPlayer(player)) {
          switchTo(new FixProperty());
        } else {
          switchTo(new NewTurn());
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

  private class FixProperty implements State {

    @Override
    public void handle(PlayerAction playerAction) {
      if (playerAction instanceof PlayerAction.DonePropertyUpgrade) {
        donePropertyManagement();
      }
    }
  }

  private class NoOwner implements State {

    @Override
    public void entry() {
      Property prop = (Property) player.postion();
      String propertyName = prop.getName();
      int price = prop.value();
      channel.post(new BuyOrNotMsg(propertyName, price));
    }

    @Override
    public void handle(PlayerAction playerAction) {
      if (playerAction instanceof PlayerAction.BuyProperty) {
        buyProperty();
        nextState();
      } else if (playerAction instanceof PlayerAction.Auction) {
        nextState();
      }
    }

    private void nextState() {
      if (CanFixProperty.forPlayer(player)) {
        fixPropertyOrNewTurn();
      } else {
        switchTo(new NewTurn());
      }
    }
  }

  class NoCash implements State {

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
          fixPropertyOrNewTurn();
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

  /** Game have finish */
  private class GameOver implements State {

    @Override
    public void entry() {
      channel.post(
          new GameOverEvent(
              master
                  .theRanking()
                  .stream()
                  .map((player) -> player.getId())
                  .collect(Collectors.toList())));
    }

    @Override
    public void handle(PlayerAction playerAction) {
      // Do nothing
    }
  }

  /** Game haven started jet */
  private class waitOnStart implements State {

    @Override
    public void handle(PlayerAction playerAction) {
      // Do nothing
    }
  }

  private void fixPropertyOrNewTurn() {
    if (CanFixProperty.forPlayer(player)) {
      switchTo(new FixProperty());
    } else {
      switchTo(new NewTurn());
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
    property.visitBy(new BuyProperty(player));
    channel.post(new PropertyEvent(property.getName(), player.id));
    switchTo(new FixProperty());
  }

  private void notBuyingProperty() {
    switchTo(new FixProperty());
  }

  private void mortgaged(PlayerAction.Mortgaged msg) {
    ToMorgade rule = new ToMorgade(player);
    rule.morgade(bord.forwardTo(msg.propertyName));
  }

  private void sellHouse(PlayerAction.SellHouse msg) {
    Square street = bord.forwardTo(msg.streetName);
    new ToSellHouses(player).sellHouses(street);
  }

  private void sellProperty(PlayerAction.SellProperty msg) {
    Optional<Property> property =
        Optional.of(bord.forwardTo(msg.squareName))
            .filter(Property.class::isInstance)
            .map(Property.class::cast);
    boolean playerIsOwner =
        property.flatMap(Property::owner).filter((owner -> owner.equals(player))).isPresent();
    if (playerIsOwner) {
      property.ifPresent(Property::sell);
    }
  }
}
