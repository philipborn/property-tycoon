package com.watson.propert.tycoon.game;

import java.util.Optional;

import com.google.common.collect.Streams;
import com.google.common.eventbus.EventBus;
import com.watson.propert.tycoon.game.bord.Prisonable;
import com.watson.propert.tycoon.game.bord.Property;
import com.watson.propert.tycoon.game.bord.Square;
import com.watson.propert.tycoon.game.bord.SquareVisitor;
import com.watson.propert.tycoon.game.events.CashEvent;
import com.watson.propert.tycoon.game.events.PlayerMovedEvent;
import com.watson.propert.tycoon.game.rules.Passing;

public class Player implements Owner, Prisonable, Comparable<Player> {

  public enum Id {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX
  }

  public final Id id;

  private Square location;
  private BankAccount account;
  private boolean buyRights = false;

  private EventBus channel;

  public Player(Id id, BankAccount account, Square startLocation, EventBus channel) {
    this.id = id;
    location = startLocation;
    this.channel = channel;
    this.account = account;
  }

  public Square move(int steps) {
    int oldPosition = location.getNumber();
    SquareVisitor passingRule = Passing.rulesFor(this);
    if (steps > 0) {
      location = location.forward(steps, passingRule);
    } else {
      location = location.backward(Math.abs(steps), passingRule);
    }
    int newPosition = location.getNumber();
    if (newPosition != oldPosition) {
      channel.post(new PlayerMovedEvent(newPosition,oldPosition));
    }
    return location;
  }

  public void moveTo(Square newPosition) {
    int oldPost = location.getNumber();
    location = newPosition;
    channel.post(new PlayerMovedEvent(location.getNumber(), oldPost));
  }

  public Square postion() {
    return location;
  }

  public int totalValue() {
    return account.cash()
        + Streams.stream(location.iterator())
            .filter(Property.class::isInstance)
            .map(Property.class::cast)
            .filter((prop) -> prop.owner().equals(Optional.of(this)))
            .mapToInt(Property::totalValue)
            .sum();
  }

  public Id getId() {
    return id;
  }

  @Override
  public int cash() {
    return account.cash();
  }

  @Override
  public void receiveCash(int amount) {
    int oldCash = account.cash();
    account.receiveCash(amount);
    int cash = account.cash();
    if (cash != oldCash) {
      channel.post(CashEvent.write(id, oldCash, cash));
    }
  }

  @Override
  public void payTo(CashUser cashUser, int amount) {
    int oldCash = account.cash();
    account.payTo(cashUser, amount);
    int cash = account.cash();
    if (cash != oldCash) {
      channel.post(CashEvent.write(id, oldCash, cash));
    }
  }

  @Override
  public int compareTo(Player player) {
    return id.compareTo(player.id);
  }

  public boolean hasBuyRights() {
    return buyRights;
  }

  public void receiveBuyRights() {
    buyRights = true;
  }
}
