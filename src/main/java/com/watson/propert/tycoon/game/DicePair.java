package com.watson.propert.tycoon.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.common.eventbus.EventBus;

public class DicePair {

  private EventBus channel;
  private Random genrator;

  public DicePair(EventBus channel) {
    this.channel = channel;
    genrator = new Random();
  }

  List<Integer> throwDices() {
    int firstDice = randomInt();
    int secondDice = randomInt();
    channel.post(new DiceEvent(firstDice, secondDice));
    List<Integer> list = new ArrayList<>();
    list.add(firstDice);
    list.add(secondDice);
    return list;
  }

  private Integer randomInt() {
    return genrator.nextInt(6) + 1;
  }
}
