package com.watson.propert.tycoon.game;

import java.util.Iterator;

public class Utilities extends Property {

  public Utilities(int amount) {
    super(amount);
  }

  public Iterator utilitiesIter() {
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
