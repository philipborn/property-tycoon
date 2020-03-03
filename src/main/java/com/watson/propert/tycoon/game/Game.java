package com.watson.propert.tycoon.game;

import java.util.ArrayList;
import java.util.List;

import com.google.common.eventbus.EventBus;
import com.watson.propert.tycoon.io.BoardReaderJson;

public class Game implements PropertTycoon {

  private DicePair dicePair;
  private Square bord;
  private GameMaster master;
  private EventBus channel;
  private GameState state;

  public Game(Square startPostion, EventBus channel) {
    dicePair = new DicePair(channel);
    bord = startPostion;
    List<Player> players = new ArrayList<>();
    players.add(new Player(PlayerId.ONE, bord, channel));
    players.add(new Player(PlayerId.TWO, bord, channel));
    master = new GameMaster(players);
    this.channel = channel;
    state = new NewTurnState(master, channel);
  }

  @Override
  public void throwDicesAndMove() {
    state = state.throwDicesAndMove();
  }

  @Override
  public void buyProperty() {
    state = state.buyProperty();
  }

  @Override
  public void notBuyingProperty() {
    state = state.notBuyingProperty();
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
}
