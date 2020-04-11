package com.watson.propert.tycoon.game;

import com.google.common.collect.Streams;
import com.google.common.eventbus.EventBus;
import com.watson.propert.tycoon.game.bord.Property;
import com.watson.propert.tycoon.game.bord.Square;
import com.watson.propert.tycoon.game.bord.SquareVisitor;
import com.watson.propert.tycoon.game.events.CashEvent;
import com.watson.propert.tycoon.game.rules.Passing;

import java.util.Optional;

public class Player implements Owner, Comparable<Player> {


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

  public Player(Id id, BankAccount account ,Square startLocation, EventBus channel) {
    this.id = id;
    location = startLocation;
    this.channel = channel;
    this.account = account;
  }

  public Square move(int steps) {
    SquareVisitor passingRulse = Passing.rulesFor(this);
    if (steps > 0) {
      location = location.forward(steps, passingRulse);
    } else {
      location = location.backward(Math.abs(steps), passingRulse);
    }
    return location;
  }

  public Square postion() {
    return location;
  }

  public int totalValue() {
    return account.cash() + Streams.stream(location.iterator())
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
    int cash  =account.cash();
    if(cash != oldCash) {
      channel.post(CashEvent.write(id, oldCash, cash));
    }
  }

  @Override
  public void payTo(CashUser cashUser, int amount) {
    int oldCash = account.cash();
    account.payTo(cashUser,amount);
    int cash  =account.cash();
    if(cash != oldCash) {
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
