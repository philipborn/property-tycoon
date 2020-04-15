package com.watson.propert.tycoon.game.events;

public class MortgageChangedEvent {

  public final int squareNumber;
  public final boolean mortgageStatus;

  public MortgageChangedEvent(int squareNumber, boolean mortgageStatus) {
    this.squareNumber = squareNumber;
    this.mortgageStatus = mortgageStatus;
  }
}
