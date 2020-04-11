package com.watson.propert.tycoon.game.rules;

import com.watson.propert.tycoon.game.Owner;
import com.watson.propert.tycoon.game.bord.SquareVisitor;
import com.watson.propert.tycoon.game.bord.Station;
import com.watson.propert.tycoon.game.bord.Street;
import com.watson.propert.tycoon.game.bord.Utilities;

public class RuleSellProperty implements SquareVisitor {

  private Owner player;

  public RuleSellProperty(Owner owner) {
    this.player = owner;
  }

  @Override
  public void areAt(Street street) {
    if (street.getNumHouse() == 0) {
      street.owner().filter((owner -> owner.equals(player))).ifPresent((owner) -> street.sell());
    }
  }

  @Override
  public void areAt(Station station) {
    station.owner().filter((owner -> owner.equals(player))).ifPresent((owner) -> station.sell());
  }

  @Override
  public void areAt(Utilities utilities) {
    utilities
        .owner()
        .filter((owner -> owner.equals(player)))
        .ifPresent((owner) -> utilities.sell());
  }
}
