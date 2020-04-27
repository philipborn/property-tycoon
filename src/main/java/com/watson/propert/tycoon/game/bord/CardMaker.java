package com.watson.propert.tycoon.game.bord;

import java.util.*;

import com.watson.propert.tycoon.game.actions.ActionFactory;
import com.watson.propert.tycoon.game.actions.ActionTyp;

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
    ActionTyp typ = ActionTyp.valueOf(order.getTyp().replace('-', '_').toUpperCase());
    String description = order.getDescription();
    switch (typ) {
      case PLAYER_STEP_FORWARD:
        return factory.playerStepsForward(description, order.getSteps());
      case PLAYER_STEP_BACKWARD:
        return factory.playerStepsBackwards(description, order.getSteps());
      case PLAYER_FORWARD_TO:
        return factory.forwardTo(description, order.getPosition());
      case PLAYER_BACKWARD_TO:
        return factory.BackwardTo(description, order.getPosition());
      case PLAYER_TO_JAIL:
        return factory.playerToJail(description);
      case PLAYER_PAY_BANK:
        return factory.playerPayBank(description, order.getPay());
      case PLAYER_PAY_FREEPARK:
        return factory.playerPayFreePark(description, order.getPay());
      case BANK_PAY_PLAYER:
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
