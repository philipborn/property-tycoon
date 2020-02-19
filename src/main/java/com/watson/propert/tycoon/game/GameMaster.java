package com.watson.propert.tycoon.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.google.common.eventbus.EventBus;

public class GameMaster implements PropertTycoon {

  private DicePair dicePair;
  private Square bord;
  private Player token;
  private EventBus bus;

  public GameMaster(Square startPostion, EventBus channle) {
    dicePair = new DicePair();
    bord = startPostion;
    token = new Player(bord, channle);
    bus = channle;
  }

  @Override
  public void throwDicesAndMove() {
    List<Integer> dices = dicePair.throwDices();
    Integer sum = dices.stream().mapToInt((a) -> a).sum();
    token.move(sum);
  }
}
