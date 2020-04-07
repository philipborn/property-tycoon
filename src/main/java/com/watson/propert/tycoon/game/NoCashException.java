package com.watson.propert.tycoon.game;

public class NoCashException extends RuntimeException {

  private int needToPay;

  public NoCashException(String msg, int needToPay) {
    super(msg);
    this.needToPay = needToPay;
  }

  public int needToPay() {
    return needToPay;
  }
}
