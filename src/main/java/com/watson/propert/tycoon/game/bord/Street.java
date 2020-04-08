package com.watson.propert.tycoon.game.bord;

import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Streams;
import com.watson.propert.tycoon.game.Bank;
import com.watson.propert.tycoon.game.Owner;

public class Street extends Property {

  public enum Colour {
    RED,
    BLUE,
    GREEN,
    BROWN,
    PURPLE,
    ORANGE,
    YELLOW,
    DEEP_BLUE
  }

  private int houseLevel = 0;
  private Colour colour;
  private List<Integer> rent;

  public Street(String name, int value, Colour colour, List<Integer> rent) {
    super(name, value);
    this.colour = colour;
    this.rent = rent;
  }

  public Iterator<Street> SameColourIter() {
    return new ColorIterator(this.colour, this);
  }

  @Override
  public int getRent() {
    int factor = (houseLevel == 0 && ifOwnerSameColor()) ? 2 : 1;
    return factor * rent.get(houseLevel);
  }

  private boolean ifOwnerSameColor() {
    for (Iterator<Street> it = SameColourIter(); it.hasNext(); ) {
      Street street = it.next();
      if (!street.owner().equals(this.owner())) {
        return false;
      }
    }
    return true;
  }

  public int getNumHouse() {
    return houseLevel;
  }

  public boolean canBuildHouse() {
    if (owner().isEmpty() || (houseLevel == rent.size() - 1) || !ifOwnerSameColor()) {
      return false;
    }
    int minHouse = Streams.stream(SameColourIter()).mapToInt(Street::getNumHouse).min().getAsInt();
    return houseLevel == minHouse;
  }

  public boolean canSellHouse() {
    if (owner().isEmpty() || houseLevel == 0 || !ifOwnerSameColor()) {
      return false;
    }
    int maxHouse = Streams.stream(SameColourIter()).mapToInt(Street::getNumHouse).max().getAsInt();
    return houseLevel == maxHouse;
  }

  public void buyHouses() {
    if (!canBuildHouse()) {
      throw new IllegalConstructionException("Forbidden to build houses right now");
    }
    Owner owner =
        owner().orElseThrow(() -> new RuntimeException("Street need owner to buy houses"));
    owner.payTo(Bank.instance(), priceForHouse());
    ++houseLevel;
  }

  public void sellHouse() {
    if (!canSellHouse()) {
      throw new IllegalConstructionException("Forbidden destroy houses right now");
    }
    Owner owner =
        owner().orElseThrow(() -> new RuntimeException("Street need owner to sell houses"));
    Bank.instance().payTo(owner, priceForHouse());
    --houseLevel;
  }

  private int priceForHouse() {
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

  public Colour getColour() {
    return colour;
  }

  @Override
  public void visitBy(SquareVisitor visitor) {
    visitor.street(this);
  }

  @Override
  public int sell() {
    if (houseLevel != 0) {
      throw new CantSellException("Can only sell street if there is no houses on it");
    }
    return super.sell();
  }
}
