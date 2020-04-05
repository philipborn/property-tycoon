package com.watson.propert.tycoon.game;

import java.util.ArrayList;
import java.util.List;

import com.google.common.eventbus.EventBus;
import com.watson.propert.tycoon.io.BoardReaderJson;

public class Game implements PropertTycoon {

  protected static final int START_CASH = 200;

  private GameMaster master;
  private DicePair dicePair;
  private EventBus channel;
  private Game.state state;

  public Game(Square startPostion, EventBus channel) {
    List<Player> players = new ArrayList<>();
    players.add(new Player(PlayerId.ONE, startPostion, channel));
    players.add(new Player(PlayerId.TWO, startPostion, channel));
    players.add(new Player(PlayerId.THREE, startPostion, channel));
    players.add(new Player(PlayerId.FOUR, startPostion, channel));
    players.add(new Player(PlayerId.FIVE, startPostion, channel));
    players.add(new Player(PlayerId.SIX, startPostion, channel));
    master = new GameMaster(players);
    dicePair = new DicePair(channel);
    this.channel = channel;
    state = new NewTurn();
  }

  @Override
  public void throwDicesAndMove() {
    state.throwDicesAndMove();
  }

  @Override
  public void buyProperty() {
    state.buyProperty();
  }

  @Override
  public void notBuyingProperty() {
    state.notBuyingProperty();
  }

  @Override
  public void donePropertyManagement() {
    state.donePropertyManagement();
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

  public static PropertTycoon newGame() {
    BoardReaderJson br = new BoardReaderJson();
    br.readFile("src/main/resources/boardDataJSON.json");
    EventBus channel = new EventBus();
    BordBuilder f = new BordBuilder(channel);
    Square first = f.buildBord(br);
    PropertTycoon game = new Game(first, channel);
    return game;
  }

  public interface state {

    default void entry() {}

    default void exit() {}

    default void throwDicesAndMove() {}

    default void buyProperty() {}

    default void notBuyingProperty() {}

    default void donePropertyManagement() {}

    default Game.state switchTo(Game.state nextState) {
      this.exit();
      nextState.entry();
      return nextState;
    }
  }

  private class NewTurn implements Game.state {

    @Override
    public void entry() {
      channel.post(new ChangePlayerEvent(master.newTurn().id));
    }

    @Override
    public void throwDicesAndMove() {
      Player currentPlayer = master.currentPlayer();
      List<Integer> dices = dicePair.throwDices();
      Integer sum = dices.stream().mapToInt((a) -> a).sum();
      currentPlayer.move(sum);

      if (currentPlayer.postion() instanceof Property) {
        state = switchTo(new NoOwner());
      } else {
        state = switchTo(new FixProperty());
      }
    }
  }

  private class FixProperty implements Game.state {

    public void donePropertyManagement() {
      state = switchTo(new NewTurn());
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
    public void buyProperty() {
      Player player = master.currentPlayer();
      Square property = player.postion();
      property.vist(new BuyProperty(player));
      channel.post(new PropertyEvent(property.name(), player.id));
      state = switchTo(new FixProperty());
    }

    @Override
    public void notBuyingProperty() {
      state = switchTo(new FixProperty());
    }
  }
}
