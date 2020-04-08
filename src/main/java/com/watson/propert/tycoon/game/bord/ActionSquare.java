package com.watson.propert.tycoon.game.bord;

import com.watson.propert.tycoon.game.actions.Action;
import com.watson.propert.tycoon.game.actions.DoNothingAction;

public class ActionSquare extends SquareAbstract {

  private Action action;

  public ActionSquare(String name, Action action) {
    super(name);
    this.action = action;
  }

  public ActionSquare(String name) {
    super(name);
    this.action = new DoNothingAction();
  }

  public void run() {
    action.run();
  }
}
