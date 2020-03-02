package com.watson.propert.tycoon.game;

/** The interface for GUI to take action for the players */
public interface PropertTycoon {

  /**
   * Thrown the dices and moves the current player. The value of Dices will received by a DiceEvent
   */
  void throwDicesAndMove();

  /** Call when player whants to buy a Property. Only works on ownless properys. */
  void buyProperty();

  /** Call when player don't whant to buy, and to began a auction */
  void notBuyingProperty();

  /** Register to the event bus used by the game to inform of change state */
  void registerListener(Object listener);

  /** Remove listener of the event bus */
  void unregisterListener(Object listener);
}
