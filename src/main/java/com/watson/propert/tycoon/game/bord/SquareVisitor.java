package com.watson.propert.tycoon.game.bord;

public interface SquareVisitor {

  default void areAt(Street street) {}

  default void areAt(Station station) {}

  default void areAt(Utilities utilities) {}

  default void areAt(ActionTrigger actionTrigger) {}

  default void areAt(Deck deck) {}

  default void areAt(Jail jail) {}

  default void areAt(FreePark freePark) {}

  default void areAt(Go go) {}
}
