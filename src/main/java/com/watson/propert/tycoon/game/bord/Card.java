package com.watson.propert.tycoon.game.bord;

import com.watson.propert.tycoon.game.actions.Action;

public class Card implements Action {

  private String name;
  private String description;
  private Action action;

  public Card(String name, String description, Action action) {
    this.name = name;
    this.description = description;
    this.action = action;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public void run() {
    action.run();
  }
}
