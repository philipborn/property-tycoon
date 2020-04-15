package com.watson.propert.tycoon.game.events;

import com.watson.propert.tycoon.game.entitys.Player;

/** This event is internals use only! Send new Active player */
public class NewTurnInternalEvent {

  public final Player newPlayer;

  public NewTurnInternalEvent(Player newPlayer) {
    this.newPlayer = newPlayer;
  }
}
