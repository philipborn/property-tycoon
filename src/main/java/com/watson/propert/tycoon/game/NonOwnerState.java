package com.watson.propert.tycoon.game;

import com.google.common.eventbus.EventBus;

public class NonOwnerState implements GameState {

  GameMaster master;
  EventBus channel;

  NonOwnerState(GameMaster master, EventBus channel) {
    this.master = master;
    this.channel = channel;
  }

  @Override
  public void entry() {
    Player current = master.currentPlayer();
    String propertyName = current.postion().name();
    String msg = current.id + " buy " + propertyName + "?";
    channel.post(new BuyOrNotMsg(msg));
  }

  @Override
  public GameState buyProperty() {
    channel.post(new ChangePlayerEvent(master.newTurn().id));
    return switchTo(new NewTurnState(master, channel));
  }

  @Override
  public GameState notBuyingProperty() {
    channel.post(new ChangePlayerEvent(master.newTurn().id));
    return switchTo(new NewTurnState(master, channel));
  }
}
