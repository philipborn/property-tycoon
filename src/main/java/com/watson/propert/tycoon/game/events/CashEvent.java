package com.watson.propert.tycoon.game.events;

import com.watson.propert.tycoon.game.Player;

public class CashEvent {

  public final Player.Id id;
  private int oldCash;
  private int newCash;

  private CashEvent(Player.Id id, int oldCash, int newCash) {
    this.id = id;
    this.oldCash = oldCash;
    this.newCash = newCash;
  }

  public int getOldCash() {
    return oldCash;
  }

  public int getNewCash() {
    return newCash;
  }

  public static CashEvent write(Player.Id id, int oldCash, int newCash) {
    return new CashEvent(id, oldCash, newCash);
  }
}
