package com.watson.propert.tycoon.game.events;

import com.watson.propert.tycoon.game.entitys.Player;

public class ChangePlayerEvent {

  public static Player.Id activePlayer;

  public ChangePlayerEvent(Player.Id activePlayer) {
    this.activePlayer = activePlayer;
  }
}
