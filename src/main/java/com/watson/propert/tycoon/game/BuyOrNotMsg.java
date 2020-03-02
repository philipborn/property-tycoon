package com.watson.propert.tycoon.game;

public class BuyOrNotMsg {

  public final String propName;
  public final int price;

  BuyOrNotMsg(String propName, int price) {
    this.propName = propName;
    this.price = price;
  }
}
