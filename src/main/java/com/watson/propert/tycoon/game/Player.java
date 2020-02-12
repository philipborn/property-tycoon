package com.watson.propert.tycoon.game;

public class Player {

  private String name = "g";
  private Square location;

  public Player() {}

  public Player(Square startLocation) {
    location = startLocation;
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
