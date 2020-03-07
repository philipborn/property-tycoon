package com.watson.propert.tycoon.game;

import java.util.Iterator;

public class Street extends Property {

  enum colourGroup {
    RED,
    BLUE,
    GREEN
  } // etc

  private int houseLevel = 0;
  private colourGroup colour;

  public Street(String name, int value, colourGroup colour) {
    super(name, value);
    this.colour = colour;
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
