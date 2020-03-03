package com.watson.propert.tycoon.game;

public class ChangePlayerEvent {

  public static PlayerId activePlayer;

  public ChangePlayerEvent(PlayerId activePlayer) {
    this.activePlayer = activePlayer;
  }
}
