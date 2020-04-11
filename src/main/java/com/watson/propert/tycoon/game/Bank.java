package com.watson.propert.tycoon.game;

/** Bank is a CashUser with infinite mount of cash. It use as a cash sink or scoured. */
public class Bank implements CashUser, Owner {

  public static Bank instance() {
    return new Bank();
  }

  @Override
  public int cash() {
    return 0;
  }

  @Override
  public void receiveCash(int amount) {
    // Do nothing
  }

  @Override
  public void payTo(CashUser cashUser, int amount) {
    cashUser.receiveCash(amount);
  }
}
