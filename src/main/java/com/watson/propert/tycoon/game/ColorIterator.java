package com.watson.propert.tycoon.game;

import java.util.Iterator;

public class ColorIterator implements Iterator<Street> {

  private Iterator<Square> iter;
  private Street next;
  private Street.Colour color;

  protected ColorIterator(Street.Colour color, Square start) {
    this.color = color;
    this.iter = start.iterator();
  }

  @Override
  public boolean hasNext() {
    return next != null;
  }

  @Override
  public Street next() {
    Street street = next;
    next = findStreet();
    return street;
  }

  private Street findStreet() {
    while (iter.hasNext()) {
      Square square = iter.next();
      if (square instanceof Street) {
        Street street = (Street) square;
        if (street.getColour().equals(color)) {
          return street;
        }
      }
    }
    return null;
  }
}
