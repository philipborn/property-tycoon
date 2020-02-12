package com.watson.propert.tycoon.game;

public class Player {

  private String name = "g";
  private Square location;

  String getName() {
    return name;
  }

  void changeName(String newName) {
    if (newName == null) {
      throw new NullPointerException();
    }
    name = newName;
  }
}
