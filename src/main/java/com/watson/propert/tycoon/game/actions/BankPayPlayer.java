package com.watson.propert.tycoon.game.actions;

import com.watson.propert.tycoon.game.bord.Action;
import com.watson.propert.tycoon.game.entitys.Bank;
import com.watson.propert.tycoon.game.entitys.Player;

public class BankPayPlayer implements Action {

  private int amount;

  public BankPayPlayer(int amount) {
    this.amount = amount;
  }

  @Override
  public void run(Player player) {
    Bank.instance().payTo(player, amount);
  }
}
