package com.watson.propert.tycoon.game.actions;

import com.watson.propert.tycoon.game.bord.Action;
import com.watson.propert.tycoon.game.entitys.CashUser;
import com.watson.propert.tycoon.game.entitys.Player;

public class PlayerPay implements Action {

  private CashUser receiver;
  private int amount;

  public PlayerPay(CashUser receiver, int amount) {
    this.receiver = receiver;
    this.amount = amount;
  }

  @Override
  public void run(Player player) {
    player.payTo(receiver, amount);
  }
}
