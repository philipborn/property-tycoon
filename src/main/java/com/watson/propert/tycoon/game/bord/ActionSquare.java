package com.watson.propert.tycoon.game.bord;

import com.watson.propert.tycoon.game.actions.DoNothingAction;

public class ActionSquare extends SquareNode {

  private final Action action;

  public ActionSquare(int seqNumber, String name, Action action) {
    super(seqNumber, name);
    this.action = action;
  }

  public ActionSquare(int seqNumber, String name) {
    super(seqNumber, name);
    this.action = new DoNothingAction();
  }

  public Action getAction() {
    return action;
  }

  @Override
  public void visitBy(SquareVisitor visitor) {
    visitor.areAt(this);
  }
}
