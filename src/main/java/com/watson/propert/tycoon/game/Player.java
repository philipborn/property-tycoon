package com.watson.propert.tycoon.game;

import com.google.common.eventbus.EventBus;

public class Player implements CashUser, Comparable<Player> {
  public final PlayerId id;

  private Square location;
  private int cash;
  private boolean buyRights = false;

  private EventBus channel;

  public Player(PlayerId id, Square startLocation, EventBus channel) {
    this.id = id;
    location = startLocation;
    this.channel = channel;
    cash = Game.START_CASH;
  }

  public Square move(int steps) {
    SquareVisitor passingRulse = RulePassing.rulesFor(this);
    location = location.move(steps, passingRulse);
    return location;
  }

  public Square postion() {
    return location;
  }

  public int totalValue() {
    return cash;
  }

  public PlayerId getId() {
    return id;
  }

  @Override
  public int cash() {
    return cash;
  }

  @Override
  public void receiveCash(int amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("Amount most be postive");
    } else if (amount > 0) {
      int oldCash = cash;
      cash += amount;
      channel.post(CashEvent.write(id, oldCash, cash));
    }
  }

  @Override
  public void payTo(CashUser cashUser, int amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("Amount most be postive");
    }
    if (cash < amount) {
      throw new NoCashException("Player " + id + " can't pay, need more cash!", amount);
    } else if (amount > 0) {
      int oldCash = cash;
      cash -= amount;
      channel.post(CashEvent.write(id, oldCash, cash));
      cashUser.receiveCash(amount);
    }
  }

  @Deprecated
  public void payCash(int amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("Amount most be postive");
    } else if (amount > 0) {
      int oldCash = cash;
      cash -= amount;
      channel.post(CashEvent.write(id, oldCash, cash));
    }
  }

  @Override
  public int compareTo(Player player) {
    return id.compareTo(player.id);
  }

  public boolean hasBuyRights() {
    return buyRights;
  }

  public void receiveBuyRights() {
    buyRights = true;
  }
}
