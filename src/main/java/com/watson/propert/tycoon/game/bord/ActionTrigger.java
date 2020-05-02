package com.watson.propert.tycoon.game.bord;

import com.watson.propert.tycoon.game.actions.DoNothingAction;

public class ActionTrigger implements SquareTyp {

  private Action action;

  public ActionTrigger(Action action) {
    this.action = action;
  }

  public ActionTrigger() {
    this.action = new DoNothingAction();
  }

  public Action getAction() {
    return action;
  }

  protected void setAction(Action action) {
    this.action = action;
  }

  @Override
  public void visitBy(SquareVisitor visitor) {
    visitor.areAt(this);
  }
}
