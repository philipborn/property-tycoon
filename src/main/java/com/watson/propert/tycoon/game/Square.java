package com.watson.propert.tycoon.game;

public interface Square extends Iterable<Square> {

  /**
   * @param numStep Many Square to move
   * @param actionOnTheWay The visitor will visit all pasting Squares
   * @return The new Square if moved numSteps from current one
   */
  Square move(int numStep, SquareVisitor actionOnTheWay);

  Square nextSquare();

  Square backSquare();

  String name();

  void vist(SquareVisitor visitor);
}
