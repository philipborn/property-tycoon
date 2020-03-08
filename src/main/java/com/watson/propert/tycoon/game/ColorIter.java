package com.watson.propert.tycoon.game;

import java.util.Iterator;

public class ColorIter implements Iterator<Street> {

  private Iterator<Square> iter;
  private Street next;
  private Street current;
  private Street.StreetColour color;

  protected ColorIter(Street.StreetColour color, Square start){
    this.color = color;
    this.iter = start.iterator();
  }

  @Override
  public boolean hasNext() {
    return next != null;
  }

  @Override
  public Street next() {
    Street street = current;
    current = next;
    next = findStreet();
    return street;
  }

  private Street findStreet() {
    while(iter.hasNext()){
      Square square = iter.next();
      if(square instanceof Street){
        Street street = (Street) square;
        if(street.getColour().equals(color)){
          return street;
        }
      }
    }
    return null;
  }
}
