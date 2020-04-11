package com.watson.propert.tycoon.game.bord;

import com.watson.propert.tycoon.game.Owner;

public class Station extends Property {

  protected static final int BASE_RENT = 25;

  private StationGroup group;

  public Station(int amount, StationGroup group) {
    super(amount);
    this.group = group;
    group.add(this);
  }

  @Override
  public int getRent() {
    if (owner().isEmpty()) {
      return BASE_RENT;
    }
    Owner owner = owner().get();
    int exponent = group.numOfOwned(owner) - 1;
    return (int) (BASE_RENT * Math.pow(2, exponent));
  }

  @Override
  public void visitBy(SquareVisitor visitor) {
    visitor.areAt(this);
  }
}
