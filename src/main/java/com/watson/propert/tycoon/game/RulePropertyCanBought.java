package com.watson.propert.tycoon.game;

public class RulePropertyCanBought implements SquareVisitor {
  private Player player;
  private boolean canBuy = false;

  private RulePropertyCanBought(Player player) {
    this.player = player;
  }

  public static boolean by(Player player) {
    RulePropertyCanBought rule = new RulePropertyCanBought(player);
    player.postion().vist(rule);
    return rule.canBuy();
  }

  private boolean canBuy() {
    return canBuy;
  }

  private void canBuyProperty(Property prop) {
    canBuy = player.hasBuyRights() && prop.owner().isEmpty() && (player.cash() >= prop.value());
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
