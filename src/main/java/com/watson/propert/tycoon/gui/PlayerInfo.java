package com.watson.propert.tycoon.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PlayerInfo {

  private VBox info;
  private HBox container;

  public PlayerInfo(HBox container) {
    this.container = container;
    info = (VBox) container.lookup("#PlayerDetail");
  }

  public Label getName() {

    //return (Label) info.getChildren().get(0);
    return (Label) info.lookup("#PlayerName");
  }

  public Label getMoney() {

    //return (Label) info.getChildren().get(1);
    return (Label) info.lookup("#PlayerBalance");
  }

  public VBox getInfo() {
    return info;
  }

  public HBox getContainer() {
    return container;
  }
}
