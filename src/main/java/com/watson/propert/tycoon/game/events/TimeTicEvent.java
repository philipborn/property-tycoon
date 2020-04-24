package com.watson.propert.tycoon.game.events;

public class TimeTicEvent {

  public final int secondsLeft;

  public TimeTicEvent(int secondLeft) {
    this.secondsLeft = secondLeft;
  }
}
