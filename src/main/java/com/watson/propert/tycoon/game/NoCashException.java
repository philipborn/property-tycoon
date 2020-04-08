package com.watson.propert.tycoon.game;

public class NoCashException extends RuntimeException {

  private CashUser needToPay;
  private CashUser payTo;
  private int amount;

  public NoCashException(CashUser needToPay, CashUser payTo, int amount) {
    super();
    this.needToPay = needToPay;
    this.payTo = payTo;
    this.amount = amount;
  }

  public CashUser needToPay() {
    return needToPay;
  }

  public CashUser payTo() {
    return payTo;
  }

  public int amount() {
    return amount;
  }
}
