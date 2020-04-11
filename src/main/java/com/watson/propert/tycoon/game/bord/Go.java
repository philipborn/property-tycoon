package com.watson.propert.tycoon.game.bord;

public class Go extends SquareAbstract {

  public Go(String name) {
    super(name);
  }

  @Override
  public void visitBy(SquareVisitor visitor) {
    visitor.areAt(this);
  }
}
