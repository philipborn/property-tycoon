package com.watson.propert.tycoon.game;

public interface Square extends Iterable<Square> {

  Square nextSquare();

  Square backSquare();

  String name();

  void vist(SquareVisitor visitor);
}
