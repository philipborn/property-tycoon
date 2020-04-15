package com.watson.propert.tycoon.game.bord;

import java.util.*;

import com.watson.propert.tycoon.game.actions.DoNothingAction;

public class Deck implements SquareTyp {

  private static final Card NULL_CARD =
      new Card("Null", "This card do not exist", new DoNothingAction());

  String deckName;
  Deque<Card> cards = new ArrayDeque<>();

  public Deck(String deckName) {
    this.deckName = deckName;
  }

  public String getDeckName() {
    return deckName;
  }

  public Card draw() {
    if (cards.isEmpty()) {
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

  @Override
  public void visitBy(SquareVisitor visitor) {
    visitor.areAt(this);
  }
}
