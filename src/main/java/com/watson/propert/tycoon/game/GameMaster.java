package com.watson.propert.tycoon.game;

import java.util.List;

import com.google.common.eventbus.EventBus;

public class GameMaster implements PropertTycoon {

  private DicePair dicePair;
  private Square bord;
  private Player token;
  private EventBus channle;

  public GameMaster(Square startPostion, EventBus channle) {
    dicePair = new DicePair(channle);
    bord = startPostion;
    token = new Player(bord, channle);
    this.channle = channle;
  }

  @Override
  public void throwDicesAndMove() {
    List<Integer> dices = dicePair.throwDices();
    Integer sum = dices.stream().mapToInt((a) -> a).sum();
    token.move(sum);
  }

  @Override
  public void registerListener(Object listener) {
    channle.register(listener);
  }

  @Override
  public void unregisterListener(Object listener) {
    channle.unregister(listener);
  }
}
