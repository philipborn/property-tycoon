package com.watson.propert.tycoon.game.rules;

import com.watson.propert.tycoon.game.bord.*;
import com.watson.propert.tycoon.game.entitys.Player;

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
  public void areAt(Street street) {
    canBuyProperty(street);
  }

  @Override
  public void areAt(Station station) {
    canBuyProperty(station);
  }

  @Override
  public void areAt(Utilities utilities) {
    canBuyProperty(utilities);
  }
}
