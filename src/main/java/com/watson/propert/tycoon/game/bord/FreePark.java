package com.watson.propert.tycoon.game.bord;

import com.watson.propert.tycoon.game.CashUser;

public class FreePark  implements CashUser {

  private int cash = 0;

  @Override
  public int cash() {
    return 0;
  }

  @Override
  public void receiveCash(int amount) {
    cash += amount;
  }

  @Override
  public void payTo(CashUser cashUser, int amount) {
    cashUser.receiveCash(amount);
  }
}
