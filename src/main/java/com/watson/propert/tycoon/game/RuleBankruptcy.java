package com.watson.propert.tycoon.game;

public class RuleBankruptcy implements SquareVisitor {
  private Player player;
  private GameMaster master;

  public RuleBankruptcy(GameMaster master) {
    this.master = master;
  }

  public void forPlayer(Player player) {
    player.postion().forEach((square) -> square.vist(this));
    player.payTo(Bank.instance(), player.cash());
  }

  @Override
  public void street(Street street) {
    street.owner().filter((owner) -> owner.equals(player)).ifPresent((owner) -> street.sell());
  }

  @Override
  public void station(Station station) {
    station.owner().filter((owner) -> owner.equals(player)).ifPresent((owner) -> station.sell());
  }

  @Override
  public void utilities(Utilities utilities) {
    utilities
        .owner()
        .filter((owner) -> owner.equals(player))
        .ifPresent((owner) -> utilities.sell());
  }
}
