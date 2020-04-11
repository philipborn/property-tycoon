package com.watson.propert.tycoon.game.bord;

public interface Square extends Iterable<Square> {

  String getName();

  /**
   * @param steps Many Square to move
   * @param visitor The visitor will visit all pasting Squares
   * @return The new Square if moved numSteps from current one
   */
  Square forward(int steps, SquareVisitor visitor);

  /**
   * Return the Square that are number of step ahead
   *
   * @param steps Number of steps, negativ moves backwards
   * @return The Sqaure iit will land on
   */
  Square forward(int steps);

  Square forwardTo(String name);

  Square forwardTo(String name, SquareVisitor visitor);

  Square backward(int steps);

  Square backward(int steps, SquareVisitor visitor);

  Square backwardTo(String name);

  Square backwardTo(String name, SquareVisitor visitor);

  void visitBy(SquareVisitor visitor);
}
