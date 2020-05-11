package com.watson.propert.tycoon.game.rules;

import com.watson.propert.tycoon.game.bord.*;
import com.watson.propert.tycoon.game.entitys.Player;

public class TotalValue implements SquareVisitor {

  private int sum = 0;
  private Player player;

  private TotalValue(Player player) {
    this.player = player;
  }

  public static int calculateFor(Player player) {
    TotalValue totalValue = new TotalValue(player);
    player.postion().forEach((square -> square.visitBy(totalValue)));
    return totalValue.getSum() + player.cash();
  }

  private int getSum() {
    return sum;
  }

  @Override
  public void areAt(Street street) {
    street
        .owner()
        .filter((owner -> owner.equals(player)))
        .ifPresent((owner -> sum += street.totalValue()));
  }

  @Override
  public void areAt(Station station) {
    station
        .owner()
        .filter((owner -> owner.equals(player)))
        .ifPresent(owner -> sum += station.totalValue());
  }

  @Override
  public void areAt(Utilities utilities) {
    utilities
        .owner()
        .filter((owner -> owner.equals(player)))
        .ifPresent(owner -> sum += utilities.totalValue());
  }
}
