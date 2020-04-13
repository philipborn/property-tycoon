package com.watson.propert.tycoon.game.bord;

import com.watson.propert.tycoon.game.actions.ActionFactory;

public class CardFactory {

  String deckName;
  ActionFactory factory;

  public CardFactory(String deckName) {
    this.deckName = deckName;
  }

  public Card BackwardTo(String description, String destination) {
    return new Card(deckName, description, factory.BackwardTo(destination));
  }

  public Card ForwardTo(String description, String destination) {
    return new Card(deckName, description, factory.ForwardTo(destination));
  }

  public Card BankPayPlayer(String description, int amount) {
    return new Card(deckName, description, factory.BankPayPlayer(amount));
  }

  public Card PlayerPayBank(String description, int amount) {
    return new Card(deckName, description, factory.PlayerPayBank(amount));
  }

  public Card PlayerPayFreePark(String description, int amount) {
    return new Card(deckName, description, factory.PlayerPayFreePark(amount));
  }

  public Card PlayerStepsForward(String description, int steps) {
    return new Card(deckName, description, factory.playerStep(steps));
  }

  public Card PlayerStepsBackwards(String description, int steps) {
    return new Card(deckName, description, factory.playerStep(-steps));
  }
}
