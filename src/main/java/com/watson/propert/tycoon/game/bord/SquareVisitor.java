package com.watson.propert.tycoon.game.bord;

public interface SquareVisitor {

  default void street(Street street) {}

  default void station(Station station) {}

  default void utilities(Utilities utilities) {}

  default void actionSquare(ActionSquare actionSquare) {}
}
