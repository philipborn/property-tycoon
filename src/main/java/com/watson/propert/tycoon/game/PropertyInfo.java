package com.watson.propert.tycoon.game;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;

public class PropertyInfo {

  private final String name;
  private final PlayerId owner;
  private final int numHouse;
  private final int rent;
  private final boolean isMorged;
  private final ImmutableList<Integer> rents;

  private PropertyInfo(
      String name,
      PlayerId owner,
      int numHouse,
      int currentRent,
      boolean morged,
      List<Integer> rents) {
    this.name = name;
    this.owner = owner;
    this.numHouse = numHouse;
    this.rent = currentRent;
    this.isMorged = morged;
    this.rents = ImmutableList.copyOf(rents);
  }

  /** @return Name of the Property */
  public String getName() {
    return name;
  }

  /** @return The current owner; */
  public PlayerId getOwner() {
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

  /**
   * Rent for number of houses in the Street.
   * Index is the number house the value is for.
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
    InfoGather gather = new InfoGather();
    square.vist(gather);
    return Optional.ofNullable(gather.getInfo());
  }

  private static PropertyInfo info(Property p) {
    int numHouses = getHouses(p);
    PlayerId id = p.owner().map((d) -> d.getId()).orElse(null);
    ImmutableList<Integer> rents = getRents(p);
    return new PropertyInfo(p.name(), id, numHouses, p.getRent(), p.isMortgage(), rents);
  }

  private static int getHouses(Property p) {
    if (p instanceof Street) {
      Street s = (Street) p;
      return s.getNumHouse();
    } else {
      return 0;
    }
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

    public PropertyInfo getInfo() {
      return info;
    }

    @Override
    public void SquareImp(SquareImp square) {
      // Do nothing
    }

    @Override
    public void street(Street street) {
      info = info(street);
    }

    @Override
    public void station(Station station) {
      info = info(station);
    }

    @Override
    public void utilities(Utilities utilities) {
      info = info(utilities);
    }
  }
}
