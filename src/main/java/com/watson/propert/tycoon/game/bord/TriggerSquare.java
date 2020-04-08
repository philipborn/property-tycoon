package com.watson.propert.tycoon.game.bord;

import com.watson.propert.tycoon.game.actions.Action;
import com.watson.propert.tycoon.game.actions.DoNothingAction;

public class TriggerSquare extends SquareAbstract {

  private Action action;

  public TriggerSquare(String name, Action action) {
    super(name);
    this.action = action;
  }

  public TriggerSquare(String name) {
    super(name);
    this.action = new DoNothingAction();
  }

  public void run() {
    action.run();
  }
}
