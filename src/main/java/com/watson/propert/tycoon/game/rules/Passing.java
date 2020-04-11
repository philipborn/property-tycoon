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
  public void areAt(ActionSquare square) {
    Action action = square.getAction();
    if (action instanceof PassingAction) {
      action.run();
    }
    if (square.getName().equals("Go")) {
      int cashToReceive = 200;
      currentPlayer.receiveCash(cashToReceive);
      currentPlayer.receiveBuyRights();
    }
  }

  @Override
  public void areAt(Street street) {}

  @Override
  public void areAt(Station station) {}

  @Override
  public void areAt(Utilities utilities) {}

  public static Passing rulesFor(Player current) {
    return new Passing(current);
  }
}
