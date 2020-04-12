package com.watson.propert.tycoon.game.bord;

import com.watson.propert.tycoon.game.actions.Action;

public class Card implements Action {

  private String deckName;
  private String description;
  private Action action;

  public Card(String deckName, String description, Action action) {
    this.deckName = deckName;
    this.description = description;
    this.action = action;
  }

  public String getDeckName() {
    return deckName;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public void run() {
    action.run();
  }
}
