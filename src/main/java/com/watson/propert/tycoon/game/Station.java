package com.watson.propert.tycoon.game;

import static com.watson.propert.tycoon.game.StationIterator.stationIterator;

import java.util.Iterator;

public class Station extends Property {

  protected static final int BASE_RENT = 25;

  public Station(String name, int amount) {
    super(name, amount);
  }

  public Iterator stationIter() {
    return stationIterator(this);
  }

  @Override
  public int getRent() {
    if (owner().isEmpty()) {
      return BASE_RENT;
    }
    int exponent = numberOfOwenStation() - 1;
    return (int) (BASE_RENT * Math.pow(2, exponent));
  }

  private int numberOfOwenStation() {
    int count = 0;
    for (Iterator<Station> it = stationIter(); it.hasNext(); ) {
      Station station = it.next();
      if (station.owner().equals(this.owner())) {
        count++;
      }
    }
    return count;
  }

  @Override
  public void vist(SquareVisitor visitor) {
    visitor.station(this);
  }
}
