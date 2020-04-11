package com.watson.propert.tycoon.game;

public class BankAccount implements CashUser {

  private int cash = 0;

  public BankAccount() {}

  public BankAccount(int cash) {
    this.cash = cash;
  }

  @Override
  public int cash() {
    return cash;
  }

  @Override
  public void receiveCash(int amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("Amount most be postive");
    }
    cash += amount;
  }

  @Override
  public void payTo(CashUser receiver, int amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("Amount most be postive");
    }
    if (cash < amount) {
      throw new NoCashException(this, receiver, amount);
    }
    cash -= amount;
    receiver.receiveCash(amount);
  }
}
