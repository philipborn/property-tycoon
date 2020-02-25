package com.watson.propert.tycoon.game;

import java.util.List;

import com.google.common.eventbus.EventBus;
import com.watson.propert.tycoon.io.BoardReaderJson;

import javax.swing.*;

public class Game implements PropertTycoon {

  private DicePair dicePair;
  private Square bord;
  private Player token;
  private EventBus channel;

  public Game(Square startPostion, EventBus channel) {
    dicePair = new DicePair(channel);
    bord = startPostion;
    token = new Player(bord, channel);
    this.channel = channel;
  }

  @Override
  public void throwDicesAndMove() {
    List<Integer> dices = dicePair.throwDices();
    Integer sum = dices.stream().mapToInt((a) -> a).sum();
    token.move(sum);
  }

  @Override
  public void registerListener(Object listener) {
    channel.register(listener);
  }

  @Override
  public void unregisterListener(Object listener) {
    channel.unregister(listener);
  }

  static public PropertTycoon newGame() {
    BoardReaderJson br = new BoardReaderJson();
    br.readFile("src/main/resources/boardDataJSON.json");
    EventBus channel = new EventBus();
    BordBuilder f = new BordBuilder(channel);
    Square first = f.buildBord(br);
    PropertTycoon game = new Game(first, channel);
    return game;
  }
}
