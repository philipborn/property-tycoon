package com.watson.propert.tycoon.game.bord;

import java.util.*;

import com.watson.propert.tycoon.game.actions.ActionFactory;

public class CardMaker {

  private Map<String, CardFactory> factors = new HashMap<>();
  private ActionFactory actionFactory;

  public CardMaker(ActionFactory actionFactory) {
    this.actionFactory = actionFactory;
  }

  public List<Card> make(CardReader reader) {
    List<CardOrder> orders = reader.getCardOrders();
    List<Card> cards = new ArrayList<>(orders.size());
    for (CardOrder order : orders) {
      CardFactory factory = getFactory(order);
      cards.add(makeCard(order, factory));
    }
    return cards;
  }

  private CardFactory getFactory(CardOrder order) {
    String deckName = order.getDeckName();
    factors.computeIfAbsent(deckName, (name) -> new CardFactory(actionFactory, name));
    return factors.get(deckName);
  }

  private Card makeCard(CardOrder order, CardFactory factory) {
    String typ = order.getTyp();
    String description = order.getDescription();
    switch (typ) {
      case "player-step-forward":
        return factory.playerStepsForward(description, order.getSteps());
      case "player-step-backward":
        return factory.playerStepsBackwards(description, order.getSteps());
      case "player-forward-to":
        return factory.forwardTo(description, order.getPosition());
      case "player-backward-to":
        return factory.BackwardTo(description, order.getPosition());
      case "player-to-jail":
        return factory.playerToJail(description);
      case "player-pay-bank":
        return factory.playerPayBank(description, order.getPay());
      case "player-pay-freepark":
        return factory.playerPayFreePark(description, order.getPay());
      case "bank-pay-player":
        return factory.bankPayPlayer(description, order.getPay());
      default:
        throw new RuntimeException(String.format("Unknown card type: %s", typ));
    }
  }

  public static void combine(Collection<Card> cards, Collection<Deck> decks) {
    Map<String, Deck> deckMap = new HashMap<>();
    decks.forEach((deck) -> deckMap.put(deck.deckName, deck));
    cards
        .stream()
        .filter((card) -> deckMap.keySet().contains(card.getDeckName()))
        .forEach((card) -> deckMap.get(card.getDeckName()).put(card));
    decks.forEach(Deck::shuffle);
  }
}
