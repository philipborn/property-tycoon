package com.watson.propert.tycoon.game.bord;

public class Utilities extends Property {

  private UtilitiesGroup group;

  public Utilities(String name, int amount, UtilitiesGroup group) {
    super(name, amount);
    this.group = group;
    group.add(this);
  }

  @Override
  public int getRent() {
    int owned = owner().map((owner) -> group.numOfOwned(owner)).orElse(0);
    int factor = owned > 1 ? 10 : 4;
    return factor * group.getLastDices();
  }

  @Override
  public void visitBy(SquareVisitor visitor) {
    visitor.areAt(this);
  }
}
