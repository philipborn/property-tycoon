package com.watson.propert.tycoon.game;

import java.util.List;
import java.util.Optional;

public class GameMaster implements PropertTycoon {

  private DicePair dicePair;

  public static PropertTycoon activateTycoon() {
    return new GameMaster();
  }

  private GameMaster() {
    dicePair = new DicePair();
  }

  @Override
  public Optional<List<Integer>> throwDices() {
    List<Integer> dices = dicePair.throwDices().orElseThrow();
    return dicePair.throwDices();
  }

  @Override
  public Optional<List<Integer>> getDices() {
    return dicePair.lastThrow();
  }
}
