package com.watson.propert.tycoon.game.rules;

import com.watson.propert.tycoon.game.*;
import com.watson.propert.tycoon.game.bord.*;

public class BuyProperty implements SquareVisitor {

  Player buyer;

  public BuyProperty(Player player) {
    buyer = player;
  }

  private void buy(Property property) {
    property
        .owner()
        .ifPresent(
            (owner) -> {
              throw new RuntimeException("Can only buy not owned property");
            });

    int price = property.price();
    if (buyer.cash() > price) {
      buyer.payTo(Bank.instance(), price);
      property.newOwner(buyer);
    }
  }

  @Override
  public void areAt(Street street) {
    buy(street);
  }

  @Override
  public void areAt(Station station) {
    buy(station);
  }

  @Override
  public void areAt(Utilities utilities) {
    buy(utilities);
  }
}
