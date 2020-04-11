package com.watson.propert.tycoon.game.bord;

import java.util.*;

import com.google.common.eventbus.EventBus;
import com.watson.propert.tycoon.game.BankAccount;
import com.watson.propert.tycoon.game.actions.Action;

public class BordBuilder {

  public interface Source {
    void extractTo(BordBuilder builder);
  }

  private EventBus channel;
  private Board board = new Board();
  private BordReader source;
  private Map<Street.Colour, StreetGroup> streetGroups = new HashMap<>();
  private StationGroup stationGroup = new StationGroup();
  private UtilitiesGroup utilitiesGroup = new UtilitiesGroup();

  private SquareNode first;
  private SquareNode last;
  private Map<String, String> prop;
  private Boolean doneLastLink = false;

  private BordBuilder(EventBus channel) {
    this.channel = channel;
  }

  public Board getBoard() {
    if (doneLastLink == false) {
      linkLastAndFirst();
    }
    return board;
  }

  public Square getFirstSquare() {
    if (doneLastLink == false) {
      linkLastAndFirst();
    }
    return first;
  }

  public BordBuilder addStreet(String name, int value, Street.Colour color, List<Integer> rents) {
    checkIfCanAddSquare();
    streetGroups.computeIfAbsent(color, (key) -> new StreetGroup(color));
    SquareTyp typ = new Street(value, streetGroups.get(color), rents);
    SquareNode node = new SquareNode(name, typ);
    addToLink(node);
    return this;
  }

  public BordBuilder addStation(String name, int value) {
    checkIfCanAddSquare();
    SquareTyp typ = new Station(value, stationGroup);
    SquareNode node = new SquareNode(name, typ);
    addToLink(node);
    return this;
  }

  public BordBuilder addUtility(String name, int value) {
    checkIfCanAddSquare();
    SquareTyp typ = new Utilities(value, utilitiesGroup);
    SquareNode node = new SquareNode(name, typ);
    addToLink(node);
    if (utilitiesGroup.size() == 1) {
      channel.register(utilitiesGroup);
    }
    return this;
  }

  public BordBuilder addJail(String name) {
    checkIfCanAddSquare();
    Jail jail = new Jail();
    SquareNode node = new SquareNode(name, jail);
    addToLink(node);
    board.setJailer(jail);
    return this;
  }

  public BordBuilder addFreePark(String name) {
    checkIfCanAddSquare();
    FreePark freePark = new FreePark(name, channel, new BankAccount());
    addToLink(freePark);
    board.setFreePark(freePark);
    return this;
  }

  public BordBuilder addGo(String name) {
    checkIfCanAddSquare();
    Go go = new Go();
    SquareNode node = new SquareNode(name, go);
    addToLink(node);
    board.setStart(node);
    board.setGo(go);
    return this;
  }

  public BordBuilder addSquare(String name) {
    checkIfCanAddSquare();
    addToLink(new ActionSquare(name));
    return this;
  }

  public BordBuilder addActionSquare(String name, Action action) {
    checkIfCanAddSquare();
    addToLink(new ActionSquare(name, action));
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
