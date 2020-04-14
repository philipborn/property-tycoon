package com.watson.propert.tycoon.game;

import java.util.Iterator;

public class StationIterator implements Iterator<Station> {

  Iterator<Square> iter;
  Station next;

  private StationIterator(Square start) {
    iter = start.iterator();
    next = findStation();
  }

  @Override
  public boolean hasNext() {
    return next != null;
  }

  @Override
  public Station next() {
    Station station = next;
    next = findStation();
    return station;
  }

  private Station findStation() {
    Square square;
    while (iter.hasNext()) {
      square = iter.next();
      if (square instanceof Station) {
        return (Station) square;
      }
    }
    return null;
  }

  public static Iterator stationIterator(Square start) {
    return new StationIterator(start);
  }
}
