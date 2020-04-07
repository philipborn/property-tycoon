package com.watson.propert.tycoon.game;

public class RuleToMorgade implements SquareVisitor {

  private Player player;
  private int reviveCash = 0;

  public RuleToMorgade(Player player) {
    this.player = player;
  }

  public int morgade(Square square) {
    square.vist(this);
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
