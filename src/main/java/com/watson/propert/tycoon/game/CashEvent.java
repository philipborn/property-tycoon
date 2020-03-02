package com.watson.propert.tycoon.game;

public class CashEvent {

  public final PlayerId id;
  private int oldCash;
  private int newCash;

  private CashEvent(PlayerId id, int oldCash, int newCash) {
    this.id = id;
    this.oldCash = oldCash;
    this.newCash = newCash;
  }

  int getOldCash() {
    return oldCash;
  }

  int getNewCash() {
    return newCash;
  }

  public static CashEvent write(PlayerId id, int oldCash, int newCash) {
    return new CashEvent(id, oldCash, newCash);
  }
}
