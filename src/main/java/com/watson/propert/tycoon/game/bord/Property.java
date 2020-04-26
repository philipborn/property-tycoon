package com.watson.propert.tycoon.game.bord;

import java.util.Optional;

import com.watson.propert.tycoon.game.entitys.Bank;

public abstract class Property implements SquareTyp {

  private int value;
  private Owner owner;
  private boolean mortgaged;

  public Property(int amount) {
    mortgaged = false;
    value = amount;
    owner = null;
  }

  public abstract int getRent();

  public Optional<Owner> owner() {
    return Optional.ofNullable(owner);
  }

  public void newOwner(Owner newOwner) {
    this.owner = newOwner;
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

  public int sell() {
    Bank.instance().payTo(owner, price());
    owner = null;
    mortgaged = false;
    return value;
  }

  public boolean isMortgage() {
    return mortgaged;
  }

  public int mortgage() {
    int pay = price() / 2;
    Bank.instance().payTo(owner, pay);
    mortgaged = true;
    return pay;
  }

  public int RemoveMortgage() {
    int received = price() / 2;
    owner.payTo(Bank.instance(), received);
    mortgaged = false;
    return received;
  }
}
