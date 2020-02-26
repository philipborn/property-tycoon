package com.watson.propert.tycoon.game;

import com.google.common.eventbus.EventBus;

public class Player {

  private String name = "g";
  private Square location;

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
    Square old = this.location;
    Square node;
    if (steps > 0) {
      node = stepForward(steps);
    } else {
      node = stepBack(-steps);
    }
    return node;
  }

  private Square stepForward(int steps) {
    Square node = location;
    while (steps > 0) {
      node = node.nextSquare();
      --steps;
    }
    return node;
  }

  private Square stepBack(int steps) {
    Square node = location;
    while (steps > 0) {
      node = node.backSquare();
      --steps;
    }
    return node;
  }

  public Square postion() {
    return location;
  }
}
