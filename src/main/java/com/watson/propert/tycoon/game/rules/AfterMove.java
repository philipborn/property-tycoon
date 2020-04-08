package com.watson.propert.tycoon.game.rules;

import com.watson.propert.tycoon.game.*;
import com.watson.propert.tycoon.game.bord.*;

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
  public void street(Street street) {
    propertyHandling(street);
  }

  @Override
  public void station(Station station) {
    propertyHandling(station);
  }

  @Override
  public void utilities(Utilities utilities) {
    propertyHandling(utilities);
  }

  @Override
  public void actionSquare(ActionSquare actionSquare) {
    actionSquare.getAction().run();
  }
}
