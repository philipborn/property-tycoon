package com.watson.propert.tycoon.game;

import java.util.function.Consumer;

import com.google.common.eventbus.EventBus;

public class Player implements CashUser {

  private String name = "g";
  private Square location;
  private int cash;

  private EventBus channel;

  public Player(Square startLocation, EventBus channel) {
    location = startLocation;
    this.channel = channel;
  }

  String getName() {
    return name;
  }

  void changeName(String newName) {
    if (newName == null) {
      throw new NullPointerException();
    }
    name = newName;
  }

  public Square move(int steps) {
    Consumer<Player> step;
    if (steps < 0) {
      step = Player::stepBack;
    } else {
      step = Player::stepForward;
    }
    for (int stepLeft = Math.abs(steps); stepLeft != 0; stepLeft--) {
      step.accept(this);
      location.vist(PassingRule.rulesFor(this));
    }
    return location;
  }

  private void stepForward() {
    location = location.nextSquare();
  }

  private void stepBack() {
    location = location.backSquare();
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
}
