package com.watson.propert.tycoon.gui;

/**
 * Card Enum Enum for Pot Luck and Opportunity Knocks cards
 *
 * @author Lee Richards
 * @version Sprint 5
 */
public enum Card {
  POT_LUCK("backgroundPotLuck"),
  OP_KNOCKS("backgroundOpKnock");
  private String cssClass;

  Card(String cssClass) {
    this.cssClass = cssClass;
  }

  /**
   * Get the CSS Class of Card
   *
   * @return Card style class
   */
  public String getCssClass() {
    return this.cssClass;
  }

  /**
   * Get Card Type by Name
   *
   * @param deckName
   * @return Generic name of card type
   */
  public static Card getCardByName(String deckName) {
    if (deckName.equalsIgnoreCase("pot luck")) {
      return POT_LUCK;
    } else {
      return OP_KNOCKS;
    }
  }
}
