package com.watson.propert.tycoon.game.bord;

import java.util.Optional;

import com.watson.propert.tycoon.game.Bank;
import com.watson.propert.tycoon.game.Owner;

public abstract class Property extends SquareNode implements SquareTyp {

  private int value;
  private Owner owner;
  private boolean mortgaged;

  public Property(String name, int amount) {
    super(name);
    mortgaged = false;
    value = amount;
    owner = null;
  }

  public abstract int getRent();

  public Optional<Owner> owner() {
    return Optional.ofNullable(owner);
  }

  // value is the property cost not rent
  public int price() {
    return value;
  }

  /**
   * This method is to override if the value of the Property need to be calculated differently
   *
   * @return value of the Property
   */
  public int totalValue() {
    return value;
  }

  public int mortgage() {
    mortgaged = true;
    return price() / 2;
  }

  public boolean isMortgage() {
    return mortgaged;
  }

  public int sell() {
    Bank.instance().payTo(owner, price());
    owner = null;
    mortgaged = false;
    return value;
  }

  public void newOwner(Owner newOwner) {
    this.owner = newOwner;
  }
}
