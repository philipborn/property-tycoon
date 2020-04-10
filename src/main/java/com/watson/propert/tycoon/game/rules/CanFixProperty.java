package com.watson.propert.tycoon.game.rules;

import com.watson.propert.tycoon.game.Player;
import com.watson.propert.tycoon.game.bord.*;

public class CanFixProperty implements SquareVisitor {

  private Player player;
  private Boolean foundProperty = false;

  public CanFixProperty(Player player) {
    this.player = player;
  }

  public static boolean forPlayer(Player player) {
    CanFixProperty rule = new CanFixProperty(player);
    Square bord = player.postion();
    for (Square square : bord) {
      bord.visitBy(rule);
      if (rule.hasFoundProperty()) {
        return true;
      }
    }
    return false;
  }

  private boolean hasFoundProperty() {
    return foundProperty;
  }

  @Override
  public void street(Street street) {
    foundProperty = street.owner().filter((owner -> owner.equals(player))).isPresent();
  }

  @Override
  public void station(Station station) {
    foundProperty = station.owner().filter((owner -> owner.equals(player))).isPresent();
  }

  @Override
  public void utilities(Utilities utilities) {
    foundProperty = utilities.owner().filter((owner -> owner.equals(player))).isPresent();
  }
}
