package com.watson.propert.tycoon.game;

public class PropertyEvent {

  private String name;
  private PlayerId owner;

  PropertyEvent(String name, PlayerId id) {
    this.name = name;
    this.owner = id;
  }

  public String getPropertyName() {
    return name;
  }

  public PlayerId getOwner() {
    return owner;
  }
}
