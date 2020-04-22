package com.watson.propert.tycoon.game.events;

import com.watson.propert.tycoon.game.entitys.Player;

public final class CanFixPropertyEvent {

  public final Player.Id player;

  public CanFixPropertyEvent(Player.Id player) {
    this.player = player;
  }

}
