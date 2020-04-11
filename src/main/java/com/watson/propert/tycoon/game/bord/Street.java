package com.watson.propert.tycoon.game.bord;

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
  private StreetGroup group;
  private List<Integer> rent;

  public Street(String name, int value, StreetGroup group, List<Integer> rent) {
    super(name, value);
    this.group = group;
    this.rent = List.copyOf(rent);
    group.add(this);
  }

  @Override
  public int getRent() {
    Boolean owensTheGroup = owner().map(group::owensTheGroup).orElse(false);
    int factor = (houseLevel == 0 && owensTheGroup) ? 2 : 1;
    return factor * rent.get(houseLevel);
  }

  public int getNumHouse() {
    return houseLevel;
  }

  public boolean canBuildHouse() {
    Boolean owensTheGroup = owner().map(group::owensTheGroup).orElse(false);
    if (owner().isEmpty() || (houseLevel == rent.size() - 1) || !owensTheGroup) {
      return false;
    }
    return houseLevel == group.smallestHouseSize();
  }

  public boolean canSellHouse() {
    Boolean owensTheGroup = owner().map(group::owensTheGroup).orElse(false);
    if (owner().isEmpty() || houseLevel == 0 || !owensTheGroup) {
      return false;
    }
    return houseLevel == group.largesHouseSize();
  }

  public void buyHouses() {
    if (!canBuildHouse()) {
      throw new IllegalConstructionException("Forbidden to build houses right now");
    }
    Owner owner =
        owner().orElseThrow(() -> new RuntimeException("Street need owner to buy houses"));
    owner.payTo(Bank.instance(), group.getHousePrice());
    ++houseLevel;
  }

  public void sellHouse() {
    if (!canSellHouse()) {
      throw new IllegalConstructionException("Forbidden destroy houses right now");
    }
    Owner owner =
        owner().orElseThrow(() -> new RuntimeException("Street need owner to sell houses"));
    Bank.instance().payTo(owner, group.getHousePrice());
    --houseLevel;
  }

  public Colour getColour() {
    return group.getColor();
  }

  @Override
  public int totalValue() {
    return super.totalValue() + houseLevel * group.getHousePrice();
  }

  @Override
  public void visitBy(SquareVisitor visitor) {
    visitor.areAt(this);
  }

  @Override
  public int sell() {
    if (houseLevel != 0) {
      throw new CantSellException("Can only sell street if there is no houses on it");
    }
    return super.sell();
  }
}
