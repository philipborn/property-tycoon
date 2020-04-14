package com.watson.propert.tycoon.game;

public class BuyProperty implements SquareVisitor {

  Player buyer;

  BuyProperty(Player player) {
    buyer = player;
  }

  public void buy(Property property) {
    property
        .owner()
        .ifPresent(
            (owner) -> {
              throw new RuntimeException("Can only buy not owned property");
            });

    int price = property.value();
    if (buyer.cash() > price) {
      buyer.payCash(price);
      property.newOwner(buyer);
    }
  }

  @Override
  public void SquareImp(SquareImp square) {}

  @Override
  public void street(Street street) {
    buy(street);
  }

  @Override
  public void station(Station station) {
    buy(station);
  }

  @Override
  public void utilities(Utilities utilities) {
    buy(utilities);
  }
}
