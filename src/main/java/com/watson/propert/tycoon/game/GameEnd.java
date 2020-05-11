package com.watson.propert.tycoon.game;

interface GameEnd {
  default boolean isGameDone() {
    return false;
  }
}
