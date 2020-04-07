package com.watson.propert.tycoon.game;

public class RuleAfterMove implements SquareVisitor {

  private Player player;

  public RuleAfterMove(Player player) {
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
}