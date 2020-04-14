package com.watson.propert.tycoon.gui;

import java.util.ArrayList;

/**
 * GUI Player Class. Describes a Player in the game
 *
 * @author Lee Richards
 * @version Sprint3
 */
public class GuiPlayer {
  String name;
  GuiToken token;
  Boolean ai;
  PlayerInfo info;
  ArrayList<GuiProperty> portfolio;

  public GuiPlayer(String name, GuiToken token, Boolean ai, PlayerInfo info) {
    this.name = name;
    this.token = token;
    this.ai = ai;
    this.info = info;
    portfolio = new ArrayList<>();
  }

  public GuiPlayer(String name, GuiToken token) {
    this.name = name;
    this.token = token;
    this.ai = false;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public GuiToken getToken() {
    return token;
  }

  public void setToken(GuiToken token) {
    this.token = token;
  }

  public Boolean isAi() {
    return ai;
  }

  public void setAi(Boolean ai) {
    this.ai = ai;
  }

  public PlayerInfo getInfo() {
    return info;
  }

  // Add a purchased property to Player's portfolio
  public void addProperty(GuiProperty property) {
    portfolio.add(property);
  }

  // Remove a property from Player's portfolio
  public GuiProperty removeProperty(int pos) {
    for (GuiProperty prop : portfolio) {
      if (prop.getBoardPosition() == pos) {
        portfolio.remove(prop);
        return prop;
      }
    }
    return null;
  }

  // Return Player's Portfolio
  public ArrayList<GuiProperty> getPortfolio() {
    return portfolio;
  }
}
