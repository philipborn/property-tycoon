package com.watson.propert.tycoon.game.events;

import com.watson.propert.tycoon.game.entitys.Player;

public class PropertyEvent {

  private int seqNum;
  private Player.Id owner;

  public PropertyEvent(int seqNum, Player.Id id) {
    this.seqNum = seqNum;
    this.owner = id;
  }

  public int getSeqNumber() {
    return seqNum;
  }

  public Player.Id getOwner() {
    return owner;
  }
}
