package com.watson.propert.tycoon.game;

public class NoCashException extends RuntimeException {

  private CashUser payTo;
  private int needToPay;

  public NoCashException(String msg, CashUser payTo, int needToPay) {
    super(msg);
    this.payTo = payTo;
    this.needToPay = needToPay;
  }

  public int needToPay() {
    return needToPay;
  }

  public CashUser payTo() {
    return payTo;
  }
}
