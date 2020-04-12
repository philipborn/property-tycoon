package com.watson.propert.tycoon.gui;
/**
 * Property Group enum
 *
 * @author Lee Richards
 * @version Sprint4
 */
public enum PropertyGroup {
  BLUE("PROPERTY_GROUP_BLUE"),
  BROWN("PROPERTY_GROUP_BROWN"),
  DEEP_BLUE("PROPERTY_GROUP_DEEP_BLUE"),
  GREEN("PROPERTY_GROUP_GREEN"),
  ORANGE("PROPERTY_GROUP_ORANGE"),
  PURPLE("PROPERTY_GROUP_PURPLE"),
  RED("PROPERTY_GROUP_RED"),
  YELLOW("PROPERTY_GROUP_YELLOW"),
  STATION("PROPERTY_GROUP_STATION"),
  UTILITIES("PROPERTY_GROUP_UTILITIES"),
  TAKE_CARD("PROPERTY_GROUP_TAKE_CARD");
  private String cssClass;

  PropertyGroup(String cssClass) {
    this.cssClass = cssClass;
  }

  public String getCssClass() {
    return this.cssClass;
  }
}
