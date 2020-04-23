package com.watson.propert.tycoon.gui;
/**
 * Property Group enum
 *
 * @author Lee Richards
 * @version Sprint4
 */
public enum PropertyGroup {
  BLUE("PROPERTY_GROUP_BLUE", 50),
  BROWN("PROPERTY_GROUP_BROWN", 50),
  DEEP_BLUE("PROPERTY_GROUP_DEEP_BLUE", 200),
  GREEN("PROPERTY_GROUP_GREEN", 200),
  ORANGE("PROPERTY_GROUP_ORANGE", 100),
  PURPLE("PROPERTY_GROUP_PURPLE", 100),
  RED("PROPERTY_GROUP_RED", 150),
  YELLOW("PROPERTY_GROUP_YELLOW", 150),
  STATION("PROPERTY_GROUP_STATION"),
  UTILITIES("PROPERTY_GROUP_UTILITIES"),
  TAKE_CARD("PROPERTY_GROUP_TAKE_CARD");
  private String cssClass;
  private int housePrice;

  PropertyGroup(String cssClass) {
    this.cssClass = cssClass;
    this.housePrice = -1; // indicates that group is not upgradable
  }

  PropertyGroup(String cssClass, int housePrice) {
    this.cssClass = cssClass;
    this.housePrice = housePrice;
  }

  public String getCssClass() {
    return this.cssClass;
  }

  public int getHousePrice() {
    return housePrice;
  }
}
