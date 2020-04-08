package com.watson.propert.tycoon.game.bord;

import java.util.Iterator;
import java.util.List;

import com.watson.propert.tycoon.game.Bank;
import com.watson.propert.tycoon.game.Owner;

public class Street extends Property {

  public enum Colour {
    RED,
    BLUE,
    GREEN,
    BROWN,
    PURPLE,
    ORANGE,
    YELLOW,
    DEEP_BLUE
  }

  private int houseLevel = 0;
  private Colour colour;
  private List<Integer> rent;

  public Street(String name, int value, Colour colour, List<Integer> rent) {
    super(name, value);
    this.colour = colour;
    this.rent = rent;
  }

  public Iterator<Street> SameColourIter() {
    return new ColorIterator(this.colour, this);
  }

  @Override
  public int getRent() {
    int factor = ifOwensSameColor() ? 2 : 1;
    return factor * rent.get(houseLevel);
  }

  private boolean ifOwensSameColor() {
    for (Iterator<Street> it = SameColourIter(); it.hasNext(); ) {
      Street street = it.next();
      if (!street.owner().equals(this.owner())) {
        return false;
      }
    }
    return true;
  }

  public int getNumHouse() {
    return houseLevel;
  }

  public void buyHouses(int housesToBuy) {
    if (housesToBuy < 0) {
      throw new IllegalArgumentException("HouseToBuy most not be negative");
    }
    if (houseLevel + housesToBuy >= rent.size()) {
      throw new IllegalArgumentException("Can't buy more houses then Street support");
    }
    if (housesToBuy > 0) {
      Owner owner =
          owner().orElseThrow(() -> new RuntimeException("Street need owner to buy houses"));
      owner.payTo(Bank.instance(), priceForHouse() * housesToBuy);
      houseLevel += housesToBuy;
    }
  }

  public void sellHouses(int housesToSell) {
    if (housesToSell < 0) {
      throw new IllegalArgumentException("HousesToSell most not be negative");
    }
    if (houseLevel - housesToSell < 0) {
      throw new IllegalArgumentException("Can't have negative number of houses");
    }
    if (housesToSell > 0) {
      Owner owner =
          owner().orElseThrow(() -> new RuntimeException("Street need owner to sell houses"));
      Bank.instance().payTo(owner, priceForHouse() * housesToSell);
      houseLevel -= housesToSell;
    }
  }

  private int priceForHouse() {
    switch (colour) {
      case BROWN:
      case BLUE:
        return 50;
      case PURPLE:
      case ORANGE:
        return 100;
      case RED:
      case YELLOW:
        return 150;
      case GREEN:
      case DEEP_BLUE:
        return 200;
    }
    return 0;
  }

  public Colour getColour() {
    return colour;
  }

  @Override
  public void vist(SquareVisitor visitor) {
    visitor.street(this);
  }

  @Override
  public int sell() {
    houseLevel = 0;
    return super.sell();
  }
}
