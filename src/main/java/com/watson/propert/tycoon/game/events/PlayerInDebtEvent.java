package com.watson.propert.tycoon.game.events;

import com.watson.propert.tycoon.game.entitys.Player;

public final class PlayerInDebtEvent {

  public final Player.Id playerId;
  public final int amount;

  public PlayerInDebtEvent(Player.Id id, int amount) {
    this.playerId = id;
    this.amount = amount;
  }
}
