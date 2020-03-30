package com.watson.propert.tycoon.game;

import com.google.common.eventbus.EventBus;

public class FixPropertyState implements GameState {

  GameMaster master;
  EventBus channel;

  FixPropertyState(GameMaster master, EventBus channel) {
    this.master = master;
    this.channel = channel;
  }

  public GameState donePropertyManagement() {
    return switchTo(new NewTurnState(this.master, channel));
  }
}
