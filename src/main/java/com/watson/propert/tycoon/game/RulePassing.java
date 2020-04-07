package com.watson.propert.tycoon.game;

public class RulePassing implements SquareVisitor {
  Player currentPlayer;

  private RulePassing(Player current) {
    currentPlayer = current;
  }

  @Override
  public void SquareImp(SquareImp square) {
    if (square.name().equals("Go")) {
      int cashToReceive = 200;
      currentPlayer.receiveCash(cashToReceive);
    }
  }

  @Override
  public void street(Street street) {}

  @Override
  public void station(Station station) {}

  @Override
  public void utilities(Utilities utilities) {}

  public static RulePassing rulesFor(Player current) {
    return new RulePassing(current);
  }
}
