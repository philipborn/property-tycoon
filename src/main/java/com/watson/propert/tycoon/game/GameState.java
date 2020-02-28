package com.watson.propert.tycoon.game;

public interface GameState {

  default GameState throwDicesAndMove() {
    return this;
  }

  default GameState buyProperty() {
    return this;
  }

  default GameState notBuyingProperty() {
    return this;
  }
}
