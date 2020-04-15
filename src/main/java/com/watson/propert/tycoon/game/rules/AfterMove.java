package com.watson.propert.tycoon.game.rules;

import com.watson.propert.tycoon.game.bord.*;
import com.watson.propert.tycoon.game.entitys.Player;

public class AfterMove implements SquareVisitor {

  private Player player;

  public AfterMove(Player player) {
    this.player = player;
  }

  private void propertyHandling(Property property) {
    property
        .owner()
        .filter((owner) -> owner != player)
        .ifPresent((owner) -> player.payTo(owner, property.getRent()));
  }

  @Override
  public void areAt(Street street) {
    propertyHandling(street);
  }

  @Override
  public void areAt(Station station) {
    propertyHandling(station);
  }

  @Override
  public void areAt(Utilities utilities) {
    propertyHandling(utilities);
  }

  @Override
  public void areAt(ActionSquare actionSquare) {
    actionSquare.getAction().run(player);
  }

  @Override
  public void areAt(FreePark freePark) {
    int amount = freePark.cash();
    freePark.payTo(player, amount);
  }
}
