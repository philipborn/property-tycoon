package com.watson.propert.tycoon.game.events;

public class DiceEvent {
  private int firstDice;
  private int secondDice;

  public DiceEvent(int firstDice, int secondDice) {
    this.firstDice = firstDice;
    this.secondDice = secondDice;
  }

  public int firstDice() {
    return firstDice;
  }

  public int secondDice() {
    return secondDice;
  }
}
