package com.watson.propert.tycoon.game.rules;

import com.watson.propert.tycoon.game.bord.*;
import com.watson.propert.tycoon.game.entitys.Bank;
import com.watson.propert.tycoon.game.entitys.Player;

public class Passing implements SquareVisitor {
  private Player currentPlayer;
  private static final int CASH_BY_PASSING = 200;

  private Passing(Player current) {
    currentPlayer = current;
  }

  @Override
  public void areAt(Go go) {
    currentPlayer.receiveBuyRights();
    Bank.instance().payTo(currentPlayer, CASH_BY_PASSING);
  }

  public static Passing rulesFor(Player current) {
    return new Passing(current);
  }
}
