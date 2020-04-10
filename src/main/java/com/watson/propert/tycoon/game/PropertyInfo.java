package com.watson.propert.tycoon.game;

import java.util.Optional;

import com.watson.propert.tycoon.game.bord.*;

public class PropertyInfo {

  private String name;
  private Player.Id owner;
  private int numHouse;
  private int rent;
  private boolean isMorged;

  private PropertyInfo(
      String name, Player.Id owner, int numHouse, int currentRent, boolean morged) {
    this.name = name;
    this.owner = owner;
    this.numHouse = numHouse;
    this.rent = currentRent;
    isMorged = morged;
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

  /**
   * Used to collect data from a Property
   *
   * @param square the property to creata Info objeckt from
   * @return A PropertyInfo if square is a property
   */
  public static Optional<PropertyInfo> getInfo(Square square) {
    InfoGather gather = new InfoGather();
    square.visitBy(gather);
    return Optional.ofNullable(gather.getInfo());
  }

  private static PropertyInfo info(Property p) {
    int numHouses = getHouses(p);
    Player.Id id =
        p.owner()
            .filter(Player.class::isInstance)
            .map(Player.class::cast)
            .map(Player::getId)
            .orElse(null);
    return new PropertyInfo(p.getName(), id, numHouses, p.getRent(), p.isMortgage());
  }

  private static int getHouses(Property p) {
    if (p instanceof Street) {
      Street s = (Street) p;
      return s.getNumHouse();
    } else {
      return 0;
    }
  }

  static class InfoGather implements SquareVisitor {

    private PropertyInfo info;

    public PropertyInfo getInfo() {
      return info;
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
