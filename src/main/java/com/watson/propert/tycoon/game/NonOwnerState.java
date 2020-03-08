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
    Property prop = (Property) current.postion();
    String propertyName = prop.name();
    int price = prop.value();
    channel.post(new BuyOrNotMsg(propertyName, price));
  }

  @Override
  public GameState buyProperty() {
    Player player = master.currentPlayer();
    Square property = player.postion();
    property.vist(new BuyProperty(player));
    channel.post(new PropertyEvent(property.name(), player.id));
    return switchTo(new FixPropertyState(master, channel));
  }

  @Override
  public GameState notBuyingProperty() {
    return switchTo(new FixPropertyState(master, channel));
  }
}
