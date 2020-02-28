package com.watson.propert.tycoon.game;

import com.google.common.eventbus.EventBus;

public class Player implements CashUser, Comparable<Player> {
  public final PlayerId id;

  private Square location;
  private int cash;

  private EventBus channel;

  public Player(PlayerId id, Square startLocation, EventBus channel) {
    this.id = id;
    location = startLocation;
    this.channel = channel;
  }

  public Square move(int steps) {
    if (steps < 0) {
      stepBack(steps);
    } else if (steps > 0) {
      stepForward(steps);
    }
    return location;
  }

  private void stepForward(int steps) {
    if (steps > 0) {
      location = location.nextSquare();
      location.vist(PassingRule.rulesFor(this));
      stepForward(steps - 1);
    }
  }

  private void stepBack(int steps) {
    if (steps < 0) {
      location = location.backSquare();
      location.vist(PassingRule.rulesFor(this));
      stepBack(steps + 1);
    }
  }

  public Square postion() {
    return location;
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
      channel.post(CashEvent.write(oldCash, cash));
    }
  }

  @Override
  public int compareTo(Player player) {
    final int BEFORE = -1;
    final int EQUAL = 0;
    final int AFTER = 1;

    int comp = id.compareTo(player.id);
    if (comp != EQUAL) {
      return comp;
    }
    return Integer.compare(cash, player.cash);
  }
}
