package com.watson.propert.tycoon.game;

import java.util.Iterator;

public class UtilitiesIterator implements Iterator<Utilities> {

  Iterator<Square> iter;
  Utilities next;

  @Override
  public boolean hasNext() {
    return next != null;
  }

  @Override
  public Utilities next() {
    Utilities utilities = next;
    next = findUtilities();
    return utilities;
  }

  private Utilities findUtilities() {
    Square square;
    while (iter.hasNext()) {
      square = iter.next();
      if (square instanceof Utilities) {
        return (Utilities) square;
      }
    }
    return null;
  }
}
