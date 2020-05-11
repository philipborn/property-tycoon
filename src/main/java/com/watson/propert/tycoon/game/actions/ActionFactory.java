package com.watson.propert.tycoon.game.actions;

import com.google.common.eventbus.EventBus;
import com.watson.propert.tycoon.game.bord.Action;
import com.watson.propert.tycoon.game.entitys.Bank;
import com.watson.propert.tycoon.game.entitys.CashUser;

public class ActionFactory {

  private CashUser freePark;
  private EventBus channel;

  public ActionFactory(CashUser freePark, EventBus channel) {
    this.freePark = freePark;
    this.channel = channel;
  }

  public Action backwardTo(int destination) {
    return new BackwardTo(destination, channel);
  }

  public Action forwardTo(int destination) {
    return new ForwardTo(destination, channel);
  }

  public Action playerToJail() {
    return new PlayerToJail();
  }

  public Action bankPayPlayer(int amount) {
    return new BankPayPlayer(amount);
  }

  public Action playerPayBank(int amount) {
    return new PlayerPay(Bank.instance(), amount);
  }

  public Action playerPayFreePark(int amount) {
    return new PlayerPay(freePark, amount);
  }

  public Action playerStep(int steps) {
    return new PlayerSteps(steps);
  }
}
