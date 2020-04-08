package com.watson.propert.tycoon.game.rules;

import com.watson.propert.tycoon.game.*;
import com.watson.propert.tycoon.game.actions.Action;
import com.watson.propert.tycoon.game.actions.PassingAction;
import com.watson.propert.tycoon.game.bord.*;

public class Passing implements SquareVisitor {
  Player currentPlayer;

  private Passing(Player current) {
    currentPlayer = current;
  }

  @Override
  public void actionSquare(ActionSquare square) {
    Action action = square.getAction();
    if (action instanceof PassingAction) {
      action.run();
    }
    if (square.name().equals("Go")) {
      int cashToReceive = 200;
      currentPlayer.receiveCash(cashToReceive);
      currentPlayer.receiveBuyRights();
    }
  }

  @Override
  public void street(Street street) {}

  @Override
  public void station(Station station) {}

  @Override
  public void utilities(Utilities utilities) {}

  public static Passing rulesFor(Player current) {
    return new Passing(current);
  }
}
