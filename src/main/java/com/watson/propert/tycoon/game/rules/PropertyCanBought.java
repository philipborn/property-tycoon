package com.watson.propert.tycoon.game.rules;

import com.watson.propert.tycoon.game.*;
import com.watson.propert.tycoon.game.bord.*;

public class PropertyCanBought implements SquareVisitor {
  private Player player;
  private boolean canBuy = false;

  private PropertyCanBought(Player player) {
    this.player = player;
  }

  public static boolean by(Player player) {
    PropertyCanBought rule = new PropertyCanBought(player);
    player.postion().visitBy(rule);
    return rule.canBuy();
  }

  private boolean canBuy() {
    return canBuy;
  }

  private void canBuyProperty(Property prop) {
    canBuy = player.hasBuyRights() && prop.owner().isEmpty() && (player.cash() >= prop.price());
  }

  @Override
  public void street(Street street) {
    canBuyProperty(street);
  }

  @Override
  public void station(Station station) {
    canBuyProperty(station);
  }

  @Override
  public void utilities(Utilities utilities) {
    canBuyProperty(utilities);
  }
}
