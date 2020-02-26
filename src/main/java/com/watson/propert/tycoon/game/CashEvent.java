package com.watson.propert.tycoon.game;

public class CashEvent {

  private int oldCash;
  private int newCash;

  private CashEvent(int oldCash, int newCash) {
    this.oldCash = oldCash;
    this.newCash = newCash;
  }

  int getOldCash() {
    return oldCash;
  }

  int getNewCash() {
    return newCash;
  }

  public static CashEvent write(int oldCash, int newCash) {
    return new CashEvent(oldCash, newCash);
  }
}
