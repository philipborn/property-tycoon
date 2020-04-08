package com.watson.propert.tycoon.game.bord;

public interface Square extends Iterable<Square> {

  /**
   * @param numStep Many Square to move
   * @param actionOnTheWay The visitor will visit all pasting Squares
   * @return The new Square if moved numSteps from current one
   */
  Square step(int numStep, SquareVisitor actionOnTheWay);

  /**
   * Return the Square that are number of step ahead
   *
   * @param steps Number of steps, negativ moves backwards
   * @return The Sqaure iit will land on
   */
  Square step(int steps);

  Square find(String propertyName);

  Square find(String propertyName, SquareVisitor visitor);

  String name();

  void visitBy(SquareVisitor visitor);
}
