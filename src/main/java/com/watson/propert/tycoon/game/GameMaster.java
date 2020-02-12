package com.watson.propert.tycoon.game;

import java.util.ArrayList;
import java.util.List;

public class GameMaster implements PropertTycoon {

  public static PropertTycoon activateTycoon() {
    return new GameMaster();
  }

  private GameMaster() {}

  @Override
  public List<Integer> throwDices() {
    List<Integer> result = new ArrayList<>(2);
    result.add(1);
    result.add(2);
    return result;
  }

  @Override
  public List<Integer> getDices() {
    List<Integer> result = new ArrayList<>(2);
    result.add(1);
    result.add(2);
    return result;
  }
}
