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

  final class BuildHouses implements PlayerAction {
    public final String propertyName;
    public final int housesBuilt;

    public BuildHouses(String propertyName, int housesBuilt) {
      this.propertyName = propertyName;
      this.housesBuilt = housesBuilt;
    }
  }

  final class RemoveHouse {
    public final String streetName;
    public final int houseRemoved;

    public RemoveHouse(String streetName, int houseRemoved) {
      this.streetName = streetName;
      this.houseRemoved = houseRemoved;
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
