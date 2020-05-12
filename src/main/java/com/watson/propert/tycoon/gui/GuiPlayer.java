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

  /**
   * Construct a new GuiPlayer
   *
   * @param name Name of player
   * @param token GuiToken
   * @param ai true if AI, false if human
   * @param info Extned Player information
   */
  public GuiPlayer(String name, GuiToken token, Boolean ai, PlayerInfo info) {
    this.name = name;
    this.token = token;
    this.ai = ai;
    this.info = info;
    portfolio = new ArrayList<>();
  }

  /**
   * Construct a new GuiPlayer
   *
   * @param name Name of player
   * @param token GuiToken
   */
  public GuiPlayer(String name, GuiToken token) {
    this.name = name;
    this.token = token;
    this.ai = false;
  }

  /**
   * Get the name of the player
   *
   * @return name of the player
   */
  public String getName() {
    return name;
  }

  /**
   * Set the name of the player
   *
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get the GuiToken assigned to this player
   *
   * @return GuiToken
   */
  public GuiToken getToken() {
    return token;
  }

  /**
   * Set this player's token
   *
   * @param token
   */
  public void setToken(GuiToken token) {
    this.token = token;
  }

  /**
   * Is this an artificial intelligence or a real player
   *
   * @return true if AI, false if human
   */
  public Boolean isAi() {
    return ai;
  }

  /**
   * Make player an AI
   *
   * @param ai
   */
  public void setAi(Boolean ai) {
    this.ai = ai;
  }

  /**
   * Get extended player information
   *
   * @return
   */
  public PlayerInfo getInfo() {
    return info;
  }

  /**
   * Add a purchased property to player's portfolio
   *
   * @param property
   */
  public void addProperty(GuiProperty property) {
    portfolio.add(property);
  }

  /**
   * Remove a property from a player's portfolio
   *
   * @param pos
   * @return GuiProperty removed
   */
  public GuiProperty removeProperty(int pos) {
    for (GuiProperty prop : portfolio) {
      if (prop.getBoardPosition() == pos) {
        portfolio.remove(prop);
        return prop;
      }
    }
    return null;
  }

  /**
   * Calculate the player's net worth
   *
   * @return net worth of the player
   */
  public int calculateNetWorth() {
    int total = Integer.parseInt(info.getMoney().getText());
    for (GuiProperty gp : portfolio) {
      total += gp.getCurrentValue();
    }
    return total;
  }

  /**
   * Get the player's property portfolio
   *
   * @return ArrayList of GuiProperty
   */
  public ArrayList<GuiProperty> getPortfolio() {
    return portfolio;
  }

  /**
   * Does the player own the given property?
   *
   * @param square property to check ownership
   * @return true if player owns property
   */
  public boolean owns(GuiSquare square) {
    boolean found = false;
    for (GuiProperty gp : portfolio) {
      if (gp.getSquare() == square) {
        found = true;
      }
    }
    return found;
  }
}
