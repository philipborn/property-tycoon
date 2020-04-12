package com.watson.propert.tycoon.game.entitys;

public interface Jailer {

  void toJail(Prisonable prison);

  boolean isInJail(Prisonable prisonable);

  void decrementJailTime(Prisonable prison);
}
