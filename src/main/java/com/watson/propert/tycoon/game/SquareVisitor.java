package com.watson.propert.tycoon.game;

public interface SquareVisitor {
  default void SquareImp(SquareImp square) {}

  default void street(Street street) {}

  default void station(Station station) {}

  default void utilities(Utilities utilities) {}
}
