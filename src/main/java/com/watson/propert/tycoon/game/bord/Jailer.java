package com.watson.propert.tycoon.game.bord;

public interface Jailer {

  void toJail(Prisonable prison);

  boolean isInJail(Prisonable prisonable);

  void decrementJailTime(Prisonable prison);
}
