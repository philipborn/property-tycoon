package com.watson.propert.tycoon.game.events;

import com.watson.propert.tycoon.game.entitys.Player;

public class PlayerToJailEvent {

  public final Player.Id playerId;

  public PlayerToJailEvent(Player.Id id) {
    playerId = id;
  }
}
