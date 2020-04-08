package com.watson.propert.tycoon.game.bord;

import java.util.Map;

public interface BordReader {

  /**
   * Gives all Properties as a map of current object
   *
   * @return Properties
   */
  Map<String, String> getProperties();

  /**
   * Check if safe to call nextObject
   *
   * @return True if safe to call nextObject. False if not
   */
  Boolean hasNextObject();

  /** Move inner state. Calls to getProperties and getValue now is on a new Object */
  void nextObject();
}
