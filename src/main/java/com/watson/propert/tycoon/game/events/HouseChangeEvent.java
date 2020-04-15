package com.watson.propert.tycoon.game.events;

public class HouseChangeEvent {
  public final int seqNumber;
  public final int numHouses;

  public HouseChangeEvent(int seqNum, int numHouse) {
    this.seqNumber = seqNum;
    this.numHouses = numHouse;
  }
}
