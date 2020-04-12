package com.watson.propert.tycoon.game.rules;

import com.google.common.eventbus.EventBus;
import com.watson.propert.tycoon.game.Player;
import com.watson.propert.tycoon.game.bord.*;
import com.watson.propert.tycoon.game.events.HouseChangeEvent;

public class ToSellHouses implements SquareVisitor {

  private Player player;
  private int seqNum;
  private EventBus channel;

  public ToSellHouses(Player player, EventBus channel) {
    this.player = player;
    this.channel = channel;
  }

  public void sellHouses(Square square) {
    this.seqNum = square.getNumber();
    square.visitBy(this);
  }

  @Override
  public void areAt(Street street) {
    street
        .owner()
        .filter((owner) -> owner.equals(player))
        .ifPresent((owner) -> this.sellHouse(street));
  }

  private void sellHouse(Street street) {
    street.sellHouse();
    channel.post(new HouseChangeEvent(seqNum, street.getNumHouse()));
  }
}
