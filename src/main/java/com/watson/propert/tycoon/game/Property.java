package com.watson.propert.tycoon.game;

public abstract class Property extends SquareImp {

  private int value;
  private Player owner;
  private boolean mortgaged;

  public Property(String name, int amount) {
    super(name);
    mortgaged = false;
    value = amount;
    owner = null;
  }

  public abstract int getRent();

  public Player owner() {
    return owner;
  }

  // value is the property cost not rent
  public int value() {
    return value;
  }

  public void mortgage() {
    mortgaged = true;
  }

  public boolean isMortgage() {
    return mortgaged;
  }

  public int sell() {
    owner = null;
    return value;
  }

  public void newOwner(Player player) {
    owner = player;
  }
}
