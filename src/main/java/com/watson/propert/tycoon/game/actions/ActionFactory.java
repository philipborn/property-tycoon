package com.watson.propert.tycoon.game.actions;

import com.watson.propert.tycoon.game.bord.Action;
import com.watson.propert.tycoon.game.entitys.Bank;
import com.watson.propert.tycoon.game.entitys.CashUser;

public class ActionFactory {

  private CashUser freePark;

  public Action BackwardTo(String destination) {
    return new BackwardTo(destination);
  }

  public Action ForwardTo(String destination) {
    return new ForwardTo(destination);
  }

  public Action BankPayPlayer(int amount) {
    return new BankPayPlayer(amount);
  }

  public Action PlayerPayBank(int amount) {
    return new PlayerPay(Bank.instance(), amount);
  }

  public Action PlayerPayFreePark(int amount) {
    return new PlayerPay(freePark, amount);
  }

  public Action playerStep(int steps) {
    return new PlayerSteps(steps);
  }
}
