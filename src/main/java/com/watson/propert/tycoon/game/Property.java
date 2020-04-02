package com.watson.propert.tycoon.game;

import java.util.Optional;

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

  public Optional<Player> owner() {
    return Optional.ofNullable(owner);
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
