package com.watson.propert.tycoon.game;

public interface CashUser {
  int cash();

  void receiveCash(int amount);

  void payTo(CashUser cashUser, int amount);
}
