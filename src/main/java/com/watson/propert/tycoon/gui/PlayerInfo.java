package com.watson.propert.tycoon.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PlayerInfo {

  private VBox info;

  public PlayerInfo(VBox info) {
    this.info = info;
  }

  public Label getName() {
    return (Label) info.getChildren().get(0);
  }

  public Label getMoney() {
    return (Label) info.getChildren().get(1);
  }

  public VBox getInfo() {
    return info;
  }
}
