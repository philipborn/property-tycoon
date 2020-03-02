package com.watson.propert.tycoon.game;

public interface GameState {

  default void entry() {
    /* Do nothing */
  }

  default void exit() {
    /* Do nothing */
  }

  default GameState throwDicesAndMove() {
    return this;
  }

  default GameState buyProperty() {
    return this;
  }

  default GameState notBuyingProperty() {
    return this;
  }

  default GameState switchTo(GameState nextState) {
    this.exit();
    nextState.entry();
    return nextState;
  }
}
