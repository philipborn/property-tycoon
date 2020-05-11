package com.watson.propert.tycoon.game.rules;

import java.util.Optional;

import com.google.common.eventbus.EventBus;
import com.watson.propert.tycoon.game.bord.Owner;
import com.watson.propert.tycoon.game.bord.Square;
import com.watson.propert.tycoon.game.bord.SquareVisitor;
import com.watson.propert.tycoon.game.bord.Street;
import com.watson.propert.tycoon.game.events.HouseChangeEvent;

public class RuleHouse implements SquareVisitor {

  private final Square square;
  private Owner player;
  private EventBus channel;
  private Optional<Street> street = Optional.empty();

  public RuleHouse(Owner player, Square square, EventBus channel) {
    this.player = player;
    this.channel = channel;
    this.square = square;
  }

  public boolean canBuyHouse() {
    if (street.isEmpty()) {
      square.visitBy(this);
    }
    return street.map(Street::canBuildHouse).orElse(false);
  }

  public void buildHouse() {
    if (street.isEmpty()) {
      square.visitBy(this);
    }
    street.ifPresent((this::buildHouse));
    street.ifPresent(
        (street1 -> channel.post(new HouseChangeEvent(square.getNumber(), street1.getNumHouse()))));
  }

  private void buildHouse(Street theStreet) {
    if (theStreet.canBuildHouse()) {
      theStreet
          .owner()
          .filter((owner -> owner.equals(player)))
          .ifPresent((owner) -> theStreet.buyHouses());
      channel.post(new HouseChangeEvent(square.getNumber(), theStreet.getNumHouse()));
    }
  }

  public boolean canSellHouse() {
    if (street.isEmpty()) {
      square.visitBy(this);
    }
    return street.map(Street::canSellHouse).orElse(false);
  }

  public void sellHouse() {
    if (street.isEmpty()) {
      square.visitBy(this);
    }
    street.ifPresent((this::sellHouse));
  }

  private void sellHouse(Street theStreet) {
    if (theStreet.canBuildHouse()) {
      theStreet
          .owner()
          .filter((owner -> owner.equals(player)))
          .ifPresent((owner) -> theStreet.buyHouses());
      channel.post(new HouseChangeEvent(square.getNumber(), theStreet.getNumHouse()));
    }
  }

  @Override
  public void areAt(Street street) {
    this.street = Optional.of(street);
  }
}
