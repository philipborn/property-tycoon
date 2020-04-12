package com.watson.propert.tycoon.game.bord;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class StreetGroup implements Group {

  Street.Colour colour;
  Set<Street> members = new HashSet<>();

  public StreetGroup(Street.Colour colour) {
    this.colour = colour;
  }

  public Street.Colour getColor() {
    return colour;
  }

  public boolean owensTheGroup(Owner owner) {
    Optional<Owner> testOwner = Optional.ofNullable(owner);
    return members.stream().allMatch((street) -> street.owner().equals(testOwner));
  }

  public int largesHouseSize() {
    return members.stream().mapToInt(Street::getNumHouse).max().getAsInt();
  }

  public int smallestHouseSize() {
    return members.stream().mapToInt(Street::getNumHouse).min().getAsInt();
  }

  public int getHousePrice() {
    switch (colour) {
      case BROWN:
      case BLUE:
        return 50;
      case PURPLE:
      case ORANGE:
        return 100;
      case RED:
      case YELLOW:
        return 150;
      case GREEN:
      case DEEP_BLUE:
        return 200;
    }
    return 0;
  }

  protected void add(Street street) {
    members.add(street);
  }
}
