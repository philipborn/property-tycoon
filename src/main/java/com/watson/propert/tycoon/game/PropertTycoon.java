package com.watson.propert.tycoon.game;

import java.util.Optional;

/** The interface for GUI to take action for the players */
public interface PropertTycoon {

  /** Sen player Actions to the game */
  void send(PlayerAction playerAction);

  /**
   * @param squareNum) The number of the square
   * @return Datastructer with all information of a Property
   */
  Optional<PropertyInfo> propertInfo(int squareNum);

  /**
   * Used to get start cash for player
   *
   * @return Players Cash at begin. All player start with same amount.
   */
  int startCash();

  /** Register to the event bus used by the game to inform of change state */
  void registerListener(Object listener);

  /** Remove listener of the event bus */
  void unregisterListener(Object listener);
}
