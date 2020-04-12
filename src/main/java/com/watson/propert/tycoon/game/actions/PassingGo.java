package com.watson.propert.tycoon.game.actions;

import com.watson.propert.tycoon.game.entitys.Bank;
import com.watson.propert.tycoon.game.entitys.GameMaster;
import com.watson.propert.tycoon.game.entitys.Player;

public class PassingGo implements PassingAction {

  private static final int CASH_RECEIVED = 200;

  GameMaster master;

  public PassingGo(GameMaster master) {
    this.master = master;
  }

  @Override
  public void run() {
    Player currentPlayer = master.currentPlayer();
    Bank.instance().payTo(currentPlayer, CASH_RECEIVED);
    currentPlayer.receiveBuyRights();
  }
}
