package com.watson.propert.tycoon.gui;

public enum Card {
  POT_LUCK("backgroundPotLuck"),
  OP_KNOCKS("backgroundOpKnock");
  private String cssClass;

  Card(String cssClass) {
    this.cssClass = cssClass;
  }

  public String getCssClass() {
    return this.cssClass;
  }

  public static Card getCardByName(String deckName) {
    if (deckName.equalsIgnoreCase("pot luck")) {
      return POT_LUCK;
    } else {
      return OP_KNOCKS;
    }
  }
}
