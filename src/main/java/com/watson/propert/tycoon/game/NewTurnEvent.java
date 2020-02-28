package com.watson.propert.tycoon.game;

public class NewTurnEvent {

  public static PlayerId activePlayer;

  public NewTurnEvent(PlayerId activePlayer) {
    this.activePlayer = activePlayer;
  }
}
