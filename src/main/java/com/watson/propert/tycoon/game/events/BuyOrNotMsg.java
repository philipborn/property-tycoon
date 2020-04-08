package com.watson.propert.tycoon.game.events;

public class BuyOrNotMsg {

  public final String propName;
  public final int price;

  public BuyOrNotMsg(String propName, int price) {
    this.propName = propName;
    this.price = price;
  }
}
