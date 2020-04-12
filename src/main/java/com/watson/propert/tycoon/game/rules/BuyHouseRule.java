package com.watson.propert.tycoon.game.rules;

import java.util.Optional;

import com.watson.propert.tycoon.game.entitys.Player;
import com.watson.propert.tycoon.game.bord.SquareVisitor;
import com.watson.propert.tycoon.game.bord.Street;

public class BuyHouseRule implements SquareVisitor {

  private Player player;
  private Optional<Street> street = Optional.empty();

  public BuyHouseRule(Player player) {
    this.player = player;
  }

  public boolean canBuyHouse() {
    return street.map(Street::canBuildHouse).orElse(false);
  }

  public void buildHouse() {
    street.ifPresent((this::buildHouse));
  }

  private void buildHouse(Street theStreet) {
    if (theStreet.canBuildHouse()) {
      theStreet
          .owner()
          .filter((owner -> owner.equals(player)))
          .ifPresent((owner) -> theStreet.buyHouses());
    }
  }

  @Override
  public void areAt(Street street) {
    this.street = Optional.of(street);
  }
}
