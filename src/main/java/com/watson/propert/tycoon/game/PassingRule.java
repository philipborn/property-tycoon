package com.watson.propert.tycoon.game;

public class PassingRule implements SquareVisitor {
  Player currentPlayer;

  private PassingRule(Player current) {
    currentPlayer = current;
  }

  @Override
  public void SquareImp(SquareImp square) {
    if (square.name().equals("Go")) {
      int cashToReceive = 200;
      currentPlayer.receiveCash(cashToReceive);
    }
  }

  public static PassingRule rulesFor(Player current) {
    return new PassingRule(current);
  }
}
