package com.watson.propert.tycoon.game;

public interface PlayerAction {

  /**
   * Thrown the dices and moves the current player. The value of Dices will received by a DiceEvent
   */
  enum ThrowDices implements PlayerAction {
    INSTANCE
  }

  /** Yes to a question form game */
  enum Yes implements PlayerAction {
    INSTANCE
  }

  /** No to a question from game */
  enum No implements PlayerAction {
    INSTANCE
  }

  /** Signal player is done fixing with properties */
  enum DonePropertyUpgrade implements PlayerAction {
    INSTANCE
  }

  final class SellProperty implements PlayerAction {
    public final int squareNum;

    public SellProperty(int squareNum) {
      this.squareNum = squareNum;
    }
  }

  final class BuildHouse implements PlayerAction {
    public final int squareNum;

    public BuildHouse(int squareNum) {
      this.squareNum = squareNum;
    }
  }

  final class SellHouse implements PlayerAction {
    public final int squareNum;

    public SellHouse(int squareNum) {
      this.squareNum = squareNum;
    }
  }

  final class Mortgaged implements PlayerAction {
    public final int squareNum;

    public Mortgaged(int squareNum) {
      this.squareNum = squareNum;
    }
  }

  final class RemoveMortgage implements PlayerAction {
    public final int squareNum;

    public RemoveMortgage(int squareNum) {
      this.squareNum = squareNum;
    }
  }
}
