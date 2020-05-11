package com.watson.propert.tycoon.game.rules;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.EventBus;
import com.watson.propert.tycoon.game.bord.*;
import com.watson.propert.tycoon.game.bord.Owner;
import com.watson.propert.tycoon.game.events.MortgageChangedEvent;

public class RuleMortgage implements SquareVisitor {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  private Owner currentPlayer;
  private Square square;
  private Property property;
  private EventBus channel;

  public RuleMortgage(Owner currentPlayer, Square square, EventBus channel) {
    this.currentPlayer = currentPlayer;
    this.square = square;
    this.channel = channel;
  }

  public boolean canMortgage() {
    Boolean isMortgage = Optional.ofNullable(property).map(Property::isMortgage).orElse(true);
    return !isMortgage && isOwner();
  }

  public void tryMortgage() {
    if (canMortgage()) {
      property.mortgage();
      logger.debug(
          "MortgageChangedEvent: " + square.getNumber() + "isMortgage: " + property.isMortgage());
      channel.post(new MortgageChangedEvent(square.getNumber(), property.isMortgage()));
    }
  }

  public boolean canRemoveMortgage() {
    Optional<Property> propertyO = Optional.ofNullable(property);
    Boolean propIsMortgage = propertyO.map(Property::isMortgage).orElse(false);

    Boolean haveCash =
        propertyO
            .map((prop) -> prop.price() / 2)
            .filter((price) -> price <= currentPlayer.cash())
            .isPresent();

    return propIsMortgage && haveCash && isOwner();
  }

  private boolean isOwner() {
    return Optional.ofNullable(property)
        .flatMap(Property::owner)
        .filter((owner -> owner.equals(currentPlayer)))
        .isPresent();
  }

  public void tryRemoveMortgage() {
    if (canRemoveMortgage()) {
      property.RemoveMortgage();
      logger.debug(
          "MortgageChangedEvent: " + square.getNumber() + "isMortgage: " + property.isMortgage());
      channel.post(new MortgageChangedEvent(square.getNumber(), property.isMortgage()));
    }
  }

  @Override
  public void areAt(Street street) {
    property = street;
  }

  @Override
  public void areAt(Station station) {
    property = station;
  }

  @Override
  public void areAt(Utilities utilities) {
    property = utilities;
  }
}
