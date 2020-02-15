package com.watson.propert.tycoon.game;

public class DiceNotThrownError extends RuntimeException {

  DiceNotThrownError(String msg) {
    super(msg);
  }
}
