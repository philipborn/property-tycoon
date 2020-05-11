package com.watson.propert.tycoon.game;

import java.util.Optional;

import com.google.common.collect.ImmutableList;
import com.watson.propert.tycoon.game.bord.*;
import com.watson.propert.tycoon.game.entitys.Player;

public class PropertyInfo {

  private final String name;
  private final Player.Id owner;
  private final int numHouse;
  private final int rent;
  private final boolean isMorged;
  private final ImmutableList<Integer> rents;
  private int price;

  private PropertyInfo(
      String name,
      Player.Id owner,
      int numHouse,
      int currentRent,
      boolean morged,
      ImmutableList<Integer> rents,
      int price) {
    this.name = name;
    this.owner = owner;
    this.numHouse = numHouse;
    this.rent = currentRent;
    this.isMorged = morged;
    this.rents = rents;
    this.price = price;
  }

  /** @return Name of the Property */
  public String getName() {
    return name;
  }

  /** @return The current owner; */
  public Player.Id getOwner() {
    return owner;
  }

  /** @return The number of house there is on the property */
  public int getNumHouse() {
    return numHouse;
  }

  /**
   * Current rent player need to pay if land on the Property and is not the owner
   *
   * @return Rent that need to be payed
   */
  public int getRent() {
    return rent;
  }

  /** @return Ture if property is Morged */
  public boolean isMorged() {
    return isMorged;
  }

  public int price() {
    return price;
  }

  /**
   * Rent for number of houses in the Street. Index is the number house the value is for.
   *
   * @return ImmutableList or Empty if not a Street
   */
  public Optional<ImmutableList<Integer>> rentsPerHouse() {
    return Optional.ofNullable(rents);
  }

  /**
   * Used to collect data from a Property
   *
   * @param square the property to creata Info objeckt from
   * @return A PropertyInfo if square is a property
   */
  public static Optional<PropertyInfo> getInfo(Square square) {
    InfoGather gather = new InfoGather(square);
    square.visitBy(gather);
    return Optional.ofNullable(gather.getInfo());
  }

  private static ImmutableList<Integer> getRents(Property p) {
    if (p instanceof Street) {
      Street s = (Street) p;
      return s.getRentByHouses();
    } else {
      return null;
    }
  }

  static class InfoGather implements SquareVisitor {

    private PropertyInfo info;
    private Square square;

    public InfoGather(Square square) {
      this.square = square;
    }

    public PropertyInfo getInfo() {
      return info;
    }

    private PropertyInfo info(Property p) {
      int numHouses = getHouses(p);
      Player.Id id =
          p.owner()
              .filter(Player.class::isInstance)
              .map(Player.class::cast)
              .map(Player::getId)
              .orElse(null);
      return new PropertyInfo(
          square.getName(), id, numHouses, p.getRent(), p.isMortgage(), getRents(p), p.price());
    }

    private static int getHouses(Property p) {
      if (p instanceof Street) {
        Street s = (Street) p;
        return s.getNumHouse();
      } else {
        return 0;
      }
    }

    @Override
    public void areAt(Street street) {
      info = info(street);
    }

    @Override
    public void areAt(Station station) {
      info = info(station);
    }

    @Override
    public void areAt(Utilities utilities) {
      info = info(utilities);
    }
  }
}
