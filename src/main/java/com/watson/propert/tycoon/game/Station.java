package com.watson.propert.tycoon.game;

import java.util.Iterator;

public class Station extends Property {

  public Station(int amount) {
    super(amount);
  }

  public Iterator stationIter() {
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
