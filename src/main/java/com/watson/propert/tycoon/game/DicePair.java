package com.watson.propert.tycoon.game;

import java.util.ArrayList;
import java.util.List;

public class DicePair {

  int firstDice = 0;
  int secondDice = 0;

  List<Integer> throwDices() {
    List<Integer> list = new ArrayList<>();
    list.add(2);
    list.add(1);
    firstDice = 2;
    secondDice = 1;
    return list;
  }

  List<Integer> lastThrow() {
    if (firstDice == 0 || secondDice == 0) {
      throw new DiceNotThrownError("Dices have't been thrown");
    }
    ArrayList<Integer> list = new ArrayList<Integer>();
    list.add(firstDice);
    list.add(secondDice);
    return list;
  }
}
