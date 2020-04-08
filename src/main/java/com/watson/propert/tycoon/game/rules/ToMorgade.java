package com.watson.propert.tycoon.game.rules;

import com.watson.propert.tycoon.game.*;
import com.watson.propert.tycoon.game.bord.*;

public class ToMorgade implements SquareVisitor {

  private Player player;
  private int reviveCash = 0;

  public ToMorgade(Player player) {
    this.player = player;
  }

  public int morgade(Square square) {
    square.visitBy(this);
    return reviveCash;
  }

  @Override
  public void street(Street street) {
    street.owner().filter((owner) -> owner.equals(player)).ifPresent((owner) -> street.mortgage());
  }

  @Override
  public void station(Station station) {
    station
        .owner()
        .filter((owner) -> owner.equals(player))
        .ifPresent((owner) -> station.mortgage());
  }

  @Override
  public void utilities(Utilities utilities) {
    utilities
        .owner()
        .filter((owner) -> owner.equals(player))
        .ifPresent((owner) -> utilities.mortgage());
  }
}
