package com.watson.propert.tycoon.game.bord;

import com.google.common.eventbus.EventBus;
import com.watson.propert.tycoon.game.entitys.BankAccount;
import com.watson.propert.tycoon.game.entitys.CashUser;
import com.watson.propert.tycoon.game.events.FreeParkChangeEvent;

public class FreePark implements CashUser, SquareTyp {

  private BankAccount account;
  private EventBus channel;

  public FreePark(EventBus channel, BankAccount account) {
    this.channel = channel;
    this.account = account;
  }

  @Override
  public int cash() {
    return account.cash();
  }

  @Override
  public void receiveCash(int amount) {
    int oldCash = account.cash();
    account.receiveCash(amount);
    int newCash = account.cash();
    if (newCash != oldCash) {
      channel.post(new FreeParkChangeEvent(newCash, oldCash));
    }
  }

  @Override
  public void payTo(CashUser cashUser, int amount) {
    int oldCash = account.cash();
    account.payTo(cashUser, amount);
    int newCash = account.cash();
    if (newCash != oldCash) {
      channel.post(new FreeParkChangeEvent(newCash, oldCash));
    }
  }

  @Override
  public void visitBy(SquareVisitor visitor) {
    visitor.areAt(this);
  }
}
