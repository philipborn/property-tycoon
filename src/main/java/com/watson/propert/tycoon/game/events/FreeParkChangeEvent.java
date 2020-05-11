package com.watson.propert.tycoon.game.events;

public class FreeParkChangeEvent {

  public final int oldCash;
  public final int newCash;

  public FreeParkChangeEvent(int newCash, int oldCash) {
    this.newCash = newCash;
    this.oldCash = oldCash;
  }
}
