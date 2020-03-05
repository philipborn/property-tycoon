package com.watson.propert.tycoon.game;

import java.util.Iterator;

enum colourGroup {
  RED,
  BLUE,
  GREEN
} // etc

public class Street extends Property {

  private int houseLevel;
  private colourGroup colour;

  public Street(int value, colourGroup colour) {
    super(value);
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
}
