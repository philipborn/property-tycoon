package com.watson.propert.tycoon.game;

/** The interface for GUI to take action for the players */
public interface PropertTycoon {

  /**
   * Thrown the dices and moves the current player. The value of Dices will received by a DiceEvent
   */
  void throwDicesAndMove();
}
