package com.watson.propert.tycoon.game.bord;

public interface Square extends Iterable<Square> {

  String getName();

  /**
   * @param numStep Many Square to move
   * @param actionOnTheWay The visitor will visit all pasting Squares
   * @return The new Square if moved numSteps from current one
   */
  Square forward(int numStep, SquareVisitor actionOnTheWay);

  /**
   * Return the Square that are number of step ahead
   *
   * @param steps Number of steps, negativ moves backwards
   * @return The Sqaure iit will land on
   */
  Square forward(int steps);

  Square forwardTo(String propertyName);

  Square forwardTo(String propertyName, SquareVisitor passingVisitor);

  Square backward(int step);

  Square backward(int step, SquareVisitor passingVisitor);

  Square backwardTo(String name);

  Square backwardTo(String name, SquareVisitor passingVisitor);

  void visitBy(SquareVisitor visitor);
}
