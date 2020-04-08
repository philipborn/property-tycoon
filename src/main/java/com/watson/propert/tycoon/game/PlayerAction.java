package com.watson.propert.tycoon.game;

public interface PlayerAction {

  /**
   * Thrown the dices and moves the current player. The value of Dices will received by a DiceEvent
   */
  enum ThrowDices implements PlayerAction {
    INSTANCE
  }

  /** Player buy property the player is standing on */
  enum BuyProperty implements PlayerAction {
    INSTANCE
  }

  /** Player not buying and auction wil start */
  enum Auction implements PlayerAction {
    INSTANCE
  }

  enum DonePropertyUpgrade implements PlayerAction {
    INSTANCE
  }

  final class SellProperty implements PlayerAction {
    public final String squareName;

    public SellProperty(String squareName) {
      this.squareName = squareName;
    }
  }

  final class BuildHouse implements PlayerAction {
    public final String propertyName;

    public BuildHouse(String propertyName) {
      this.propertyName = propertyName;
    }
  }

  final class SellHouse implements PlayerAction {
    public final String streetName;

    public SellHouse(String streetName) {
      this.streetName = streetName;
    }
  }

  final class Mortgaged implements PlayerAction {
    public final String propertyName;

    public Mortgaged(String propertyName) {
      this.propertyName = propertyName;
    }
  }

  final class RemoveMortgage implements PlayerAction {
    public final String propertyName;

    public RemoveMortgage(String propertyName) {
      this.propertyName = propertyName;
    }
  }
}
