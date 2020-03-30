package com.watson.propert.tycoon.game;

import static com.watson.propert.tycoon.game.StationIterator.stationIterator;

import java.util.Iterator;

public class Station extends Property {

  public Station(String name, int amount) {
    super(name, amount);
  }

  public Iterator stationIter() {
    return stationIterator(this);
  }

  @Override
  public int getRent() {
    return 0;
  }

  @Override
  public void vist(SquareVisitor visitor) {
    visitor.station(this);
  }
}
