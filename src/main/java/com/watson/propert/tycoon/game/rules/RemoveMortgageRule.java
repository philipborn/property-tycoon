package com.watson.propert.tycoon.game.rules;

import java.util.Optional;

import com.watson.propert.tycoon.game.bord.*;
import com.watson.propert.tycoon.game.bord.Owner;

public class RemoveMortgageRule implements SquareVisitor {

  private Owner owner;
  private boolean lastStatus;

  public RemoveMortgageRule(Owner owner) {
    this.owner = owner;
  }

  public Optional<Boolean> lastStatus() {
    return Optional.ofNullable(lastStatus);
  }

  private void removeMortgage(Property property) {
    property
        .owner()
        .filter((owner -> owner.equals(this.owner)))
        .ifPresent((owner -> property.RemoveMortgage()));
  }

  @Override
  public void areAt(Street street) {
    removeMortgage(street);
  }

  @Override
  public void areAt(Station station) {
    removeMortgage(station);
  }

  @Override
  public void areAt(Utilities utilities) {
    removeMortgage(utilities);
  }
}
