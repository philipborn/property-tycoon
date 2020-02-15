package com.watson.propert.tycoon.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DicePair {

  private int firstDice = 0;
  private int secondDice = 0;

  Optional<List<Integer>> throwDices() {
    List<Integer> list = new ArrayList<>();
    list.add(2);
    list.add(1);
    firstDice = 2;
    secondDice = 1;
    return Optional.of(list);
  }

  Optional<List<Integer>> lastThrow() {
    if (firstDice == 0 || secondDice == 0) {
      return Optional.empty();
    }
    ArrayList<Integer> list = new ArrayList<Integer>();
    list.add(firstDice);
    list.add(secondDice);
    return Optional.of(list);
  }
}
