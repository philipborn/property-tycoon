package com.watson.propert.tycoon.game.rules;

import com.google.common.eventbus.EventBus;
import com.watson.propert.tycoon.game.bord.*;
import com.watson.propert.tycoon.game.entitys.Player;
import com.watson.propert.tycoon.game.events.PropertyEvent;

import java.util.Optional;

public class RuleSellProperty implements SquareVisitor {

  private Owner player;
  private EventBus channel;
  private  Square square;
  private Optional<Property> property = Optional.empty();

  public RuleSellProperty(Owner owner, Square square ,EventBus channel) {
    this.player = owner;
    this.square = square;
    this.channel = channel;
  }

  public boolean canSellProperty() {
    if (property.isEmpty()) {
      square.visitBy(this);
    }
    if (property.isEmpty()) {
      return false;
    }
    Boolean playerIsOwner = property
            .flatMap(Property::owner)
            .filter((owner) -> owner.equals(player))
            .isPresent();

    Boolean propCanBeSold = property.filter((Street.class::isInstance))
            .map(Street.class::cast)
            .map(Street::getNumHouse)
            .map((x)-> x == 0)
            .orElse(true);

    return playerIsOwner && propCanBeSold;
  }

  public void sellProperty() {
    if (canSellProperty()) {
      property.ifPresent(Property::sell);
      channel.post(new PropertyEvent(square.getNumber(), Player.Id.NULL));
    }
  }

  @Override
  public void areAt(Street street) {
    property = Optional.of(street);
  }

  @Override
  public void areAt(Station station) {
    property = Optional.of(station);
  }

  @Override
  public void areAt(Utilities utilities) {
    property = Optional.of(utilities);
  }
}
