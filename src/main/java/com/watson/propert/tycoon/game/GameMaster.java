package com.watson.propert.tycoon.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameMaster implements PropertTycoon {

  private DicePair dicePair;
  private Square bord;

  public GameMaster(Square startPostion) {
    dicePair = new DicePair();
    bord = startPostion;
  }

  @Override
  public List<Integer> throwDices() {
    return dicePair.throwDices();
  }

  @Override
  public Optional<List<Integer>> getDices() {
    return dicePair.lastThrow();
  }

  @Override
  public List<String> getSquaresNames() {
    List<String> names = new ArrayList<>(40);
    for (Square s : bord) {
      names.add(s.name());
    }
    return names;
  }
}
