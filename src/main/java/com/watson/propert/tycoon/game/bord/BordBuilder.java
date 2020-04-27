package com.watson.propert.tycoon.game.bord;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.EventBus;
import com.watson.propert.tycoon.game.actions.ActionFactory;
import com.watson.propert.tycoon.game.entitys.BankAccount;

public class BordBuilder {

  public interface Source {
    void extractTo(BordBuilder builder);
  }

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private EventBus channel;
  private Board board = new Board();
  private Map<Street.Colour, StreetGroup> streetGroups = new HashMap<>();
  private StationGroup stationGroup = new StationGroup();
  private UtilitiesGroup utilitiesGroup = new UtilitiesGroup();
  private Map<String, Deck> decks = new HashMap<>();

  private SquareNode first;
  private SquareNode last;
  private Boolean doneLastLink = false;

  private int seqNumber = 1;

  private BordBuilder(EventBus channel) {
    this.channel = channel;
  }

  public Board getBoard() {
    if (doneLastLink == false) {
      linkLastAndFirst();
      insertAction();
    }
    return board;
  }

  public Square getFirstSquare() {
    if (doneLastLink == false) {
      linkLastAndFirst();
      insertAction();
    }
    return first;
  }

  private void insertAction() {
    ActionFactory actions = new ActionFactory(board.getFreePark(), channel);
    ActionPlaceHolder.replaces(actions, first.iterator());
  }

  public BordBuilder addStreet(String name, int value, Street.Colour color, List<Integer> rents) {
    checkIfCanAddSquare();
    logger.debug("Make street: " + seqNumber + ": " + name);
    streetGroups.computeIfAbsent(color, (key) -> new StreetGroup(color));
    SquareTyp typ = new Street(value, streetGroups.get(color), rents);
    SquareNode node = new SquareNode(seqNumber++, name, typ);
    addToLink(node);
    return this;
  }

  public BordBuilder addStation(String name, int value) {
    checkIfCanAddSquare();
    logger.debug("Make station: " + seqNumber + ": " + name);
    SquareTyp typ = new Station(value, stationGroup);
    SquareNode node = new SquareNode(seqNumber++, name, typ);
    addToLink(node);
    return this;
  }

  public BordBuilder addUtility(String name, int value) {
    checkIfCanAddSquare();
    logger.debug("Make Utility: " + seqNumber + ": " + name);
    SquareTyp typ = new Utilities(value, utilitiesGroup);
    SquareNode node = new SquareNode(seqNumber++, name, typ);
    addToLink(node);
    if (utilitiesGroup.size() == 1) {
      channel.register(utilitiesGroup);
    }
    return this;
  }

  public BordBuilder addJail(String name) {
    checkIfCanAddSquare();
    logger.debug("Make Jail: " + seqNumber + ": " + name);
    Jail jail = new Jail();
    SquareNode node = new SquareNode(seqNumber++, name, jail);
    addToLink(node);
    board.setJailer(jail);
    return this;
  }

  public BordBuilder addFreePark(String name) {
    checkIfCanAddSquare();
    logger.debug("Make FreePark: " + seqNumber + ": " + name);
    FreePark freePark = new FreePark(channel, new BankAccount());
    SquareNode node = new SquareNode(seqNumber++, name, freePark);
    addToLink(node);
    board.setFreePark(freePark);
    return this;
  }

  public BordBuilder addGo(String name) {
    checkIfCanAddSquare();
    logger.debug("Make Go: " + seqNumber + ": " + name);
    Go go = new Go();
    SquareNode node = new SquareNode(seqNumber++, name, go);
    addToLink(node);
    board.setStart(node);
    board.setGo(go);
    return this;
  }

  public BordBuilder addDeck(String squareName) {
    checkIfCanAddSquare();
    logger.debug("Make Deck: " + seqNumber + ": " + squareName);
    decks.computeIfAbsent(squareName, Deck::new);
    board.addDeck(decks.get(squareName));
    SquareNode node = new SquareNode(seqNumber++, squareName, decks.get(squareName));
    addToLink(node);
    return this;
  }

  public BordBuilder addSquare(String name) {
    checkIfCanAddSquare();
    logger.debug("Make Square: " + seqNumber + ": " + name);
    addToLink(new SquareNode(seqNumber++, name));
    return this;
  }

  public BordBuilder addActionSquare(String name, Action action) {
    checkIfCanAddSquare();
    logger.debug("Make Action: " + seqNumber + ": " + name);
    ActionTrigger typ = new ActionTrigger(action);
    SquareNode node = new SquareNode(seqNumber++, name, typ);
    addToLink(node);
    return this;
  }

  public BordBuilder addSquare(SquareNode square) {
    checkIfCanAddSquare();
    addToLink(square);
    return this;
  }

  public BordBuilder addFrom(Source source) {
    checkIfCanAddSquare();
    source.extractTo(this);
    return this;
  }

  private void checkIfCanAddSquare() {
    if (doneLastLink) {
      throw new RuntimeException("Can't add Square if the bord is build");
    }
  }

  private SquareNode addToLink(SquareNode current) {
    if (last != null) {
      current.setBack(last);
      last.setNext(current);
    } else {
      first = current;
    }
    last = current;
    return current;
  }

  private void linkLastAndFirst() {
    last.setNext(first);
    first.setBack(last);
  }

  public static BordBuilder with(EventBus channel) {
    return new BordBuilder(channel);
  }
}
