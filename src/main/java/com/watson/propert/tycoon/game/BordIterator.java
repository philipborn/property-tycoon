package com.watson.propert.tycoon.game;

import java.util.Iterator;

public class BordIterator implements Iterator<Square> {

  private Square start;
  private Square next;
  private Boolean atBegin = Boolean.TRUE;

  public BordIterator(Square start) {
    this.start = start;
    this.next = start;
  }

  @Override
  public boolean hasNext() {
    if (atBegin) {
      return true;
    }
    return next != start;
  }

  @Override
  public Square next() {
    atBegin = false;
    Square current = next;
    next = next.nextSquare();
    return current;
  }
}
