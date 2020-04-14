package com.watson.propert.tycoon.game;

import java.util.Iterator;
import java.util.List;

import com.google.common.collect.ImmutableList;

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
    int factor = ifOwensSameColor() ? 2 : 1;
    return factor * rent.get(houseLevel);
  }

  private boolean ifOwensSameColor() {
    for (Iterator<Street> it = SameColourIter(); it.hasNext(); ) {
      Street street = it.next();
      if (!street.owner().equals(this.owner())) {
        return false;
      }
    }
    return true;
  }

  protected ImmutableList<Integer> getRentByHouses() {
    return ImmutableList.copyOf(rent);
  }

  public int getNumHouse() {
    return houseLevel;
  }

  public void changeNumHouses(int numHouses) {
    if (houseLevel + numHouses >= rent.size()) {
      throw new RuntimeException("Fail: To many houses");
    }
    if (houseLevel + numHouses < 0) {
      throw new RuntimeException("Can't have negative number of houses");
    }
    houseLevel += numHouses;
  }

  public Colour getColour() {
    return colour;
  }

  @Override
  public void vist(SquareVisitor visitor) {
    visitor.street(this);
  }
}
