package com.watson.propert.tycoon.game.entitys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.EventBus;
import com.watson.propert.tycoon.game.Movement;
import com.watson.propert.tycoon.game.bord.Owner;
import com.watson.propert.tycoon.game.bord.Square;
import com.watson.propert.tycoon.game.bord.SquareVisitor;
import com.watson.propert.tycoon.game.events.CashEvent;
import com.watson.propert.tycoon.game.events.PlayerMovedEvent;
import com.watson.propert.tycoon.game.rules.Passing;
import com.watson.propert.tycoon.game.rules.TotalValue;
import com.watson.propert.tycoon.gui.App;

public class Player implements Owner, Prisonable, Comparable<Player> {

  private static final Logger logger = LoggerFactory.getLogger(App.class);

  public enum Id {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX;

    public static Id fromInt(int x) {
      switch (x) {
        case 1:
          return Id.ONE;
        case 2:
          return Id.TWO;
        case 3:
          return Id.THREE;
        case 4:
          return Id.FOUR;
        case 5:
          return Id.FIVE;
        case 6:
          return Id.SIX;
        default:
          return null;
      }
    }
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
      Movement typ = steps > 0 ? Movement.FORWARD : Movement.BACKWARD;
      channel.post(new PlayerMovedEvent(newPosition, oldPosition, typ));
      logMove(newPosition, oldPosition);
    }
    return location;
  }

  public void moveTo(Square newPosition) {
    int oldPost = location.getNumber();
    location = newPosition;
    channel.post(new PlayerMovedEvent(location.getNumber(), oldPost, Movement.FORWARD));
    logMove(location.getNumber(), oldPost);
  }

  private void logMove(int newPosition, int oldPosition) {
    StringBuilder builder = new StringBuilder();
    builder.append("Player ");
    builder.append(id);
    builder.append(" move from ");
    builder.append(oldPosition);
    builder.append(" to ");
    builder.append(newPosition);
    logger.info(builder.toString());
  }

  public Square postion() {
    return location;
  }

  public int totalValue() {
    return TotalValue.calculateFor(this);
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
