package com.watson.propert.tycoon.game;

import java.util.Iterator;
import java.util.List;

public class Street extends Property {

  enum StreetColour {
    RED,
    BLUE,
    GREEN
  } // etc

  private int houseLevel = 0;
  private StreetColour colour;
  private List<Integer> rent;

  public Street(String name, int value, StreetColour colour, List<Integer> rent) {
    super(name, value);
    this.colour = colour;
    this.rent = rent;
  }

  public Iterator SameColourIter() {
    return new Iterator() {
      @Override
      public boolean hasNext() {
        return false;
      }

      @Override
      public Object next() {
        return null;
      }
    };
  }

  @Override
  public int getRent() {
    return 0;
  }

  @Override
  public void vist(SquareVisitor visitor) {
    visitor.street(this);
  }
}
