package com.watson.propert.tycoon.game.bord;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.watson.propert.tycoon.game.actions.DoNothingAction;

public class Deck implements SquareTyp {

  private static final Card NULL_CARD =
      new Card("Null", "This card do not exist", new DoNothingAction());

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  private String deckName;
  private Deque<Card> cards = new ArrayDeque<>();

  public Deck(String deckName) {
    this.deckName = deckName;
  }

  public String getDeckName() {
    return deckName;
  }

  public Card draw() {
    if (cards.isEmpty()) {
      logger.warn(deckName + " has no cards!");
      return NULL_CARD;
    }
    return cards.removeFirst();
  }

  public void put(Card card) {
    cards.addLast(card);
  }

  public void shuffle() {
    List<Card> tmp = new ArrayList<>(cards);
    Collections.shuffle(tmp);
    cards = new ArrayDeque<>(tmp);
  }

  public boolean isEmpty() {
    return cards.isEmpty();
  }

  @Override
  public void visitBy(SquareVisitor visitor) {
    visitor.areAt(this);
  }
}
