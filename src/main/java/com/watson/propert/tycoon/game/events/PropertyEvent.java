package com.watson.propert.tycoon.game.events;

import com.watson.propert.tycoon.game.Player;

public class PropertyEvent {

  private String name;
  private Player.Id owner;

  public PropertyEvent(String name, Player.Id id) {
    this.name = name;
    this.owner = id;
  }

  public String getPropertyName() {
    return name;
  }

  public Player.Id getOwner() {
    return owner;
  }
}
