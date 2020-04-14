package com.watson.propert.tycoon.game.bord;

import com.watson.propert.tycoon.game.actions.ActionFactory;

public class CardFactory {

  private String deckName;
  private ActionFactory factory;

  public CardFactory(ActionFactory factory, String deckName) {
    this.factory = factory;
    this.deckName = deckName;
  }

  public Card BackwardTo(String description, int destination) {
    return new Card(deckName, description, factory.backwardTo(destination));
  }

  public Card forwardTo(String description, int destination) {
    return new Card(deckName, description, factory.forwardTo(destination));
  }

  public Card bankPayPlayer(String description, int amount) {
    return new Card(deckName, description, factory.bankPayPlayer(amount));
  }

  public Card playerPayBank(String description, int amount) {
    return new Card(deckName, description, factory.playerPayBank(amount));
  }

  public Card playerPayFreePark(String description, int amount) {
    return new Card(deckName, description, factory.playerPayFreePark(amount));
  }

  public Card playerStepsForward(String description, int steps) {
    return new Card(deckName, description, factory.playerStep(steps));
  }

  public Card playerStepsBackwards(String description, int steps) {
    return new Card(deckName, description, factory.playerStep(-steps));
  }

  public Card playerToJail(String description) {
    return new Card(deckName, description, factory.playerToJail());
  }
}
