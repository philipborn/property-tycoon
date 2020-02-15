package com.watson.propert.tycoon.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class DicePair {

  private Random genrator;
  private int firstDice = 0;
  private int secondDice = 0;

  public DicePair() {
    genrator = new Random();
  }

  Optional<List<Integer>> throwDices() {
    firstDice = randomInt();
    secondDice = randomInt();
    List<Integer> list = new ArrayList<>();
    list.add(firstDice);
    list.add(secondDice);
    return Optional.of(list);
  }

  private Integer randomInt() {
    return genrator.nextInt(6) + 1;
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
