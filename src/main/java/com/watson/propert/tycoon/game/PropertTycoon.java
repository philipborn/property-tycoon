package com.watson.propert.tycoon.game;

import java.util.List;
import java.util.Optional;

/** The interface for GUI to take action for the players */
public interface PropertTycoon {

  /**
   * Thrown the dices and moves the current player
   *
   * @return Dice pairs value
   */
  List<Integer> throwDices();

  /**
   * Return last dice throw if exist.
   *
   * @return Dice pare last value
   */
  Optional<List<Integer>> getDices();

  /**
   * Get all squares names in the bord
   *
   * @return List with index is same order the square are from go. First square names is at index 0
   *     and second at index 1.
   */
  List<String> getSquaresNames();

  /**
   * Get the player position. Example: int = 0 is first square, int = 1 is the second square.
   *
   * @return position as the index of the square.
   */
  int GetPlayerPostion();
}
