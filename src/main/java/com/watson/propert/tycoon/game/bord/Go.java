package com.watson.propert.tycoon.game.bord;

public class Go implements SquareTyp {

  @Override
  public void visitBy(SquareVisitor visitor) {
    visitor.areAt(this);
  }
}
