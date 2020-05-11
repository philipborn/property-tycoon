package com.watson.propert.tycoon.game.events;

public class CardDrawEvent {

  public final String deckName;
  public final String description;

  public CardDrawEvent(String deckName, String description) {
    this.deckName = deckName;
    this.description = description;
  }
}
