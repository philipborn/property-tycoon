package com.watson.propert.tycoon.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Extend Player Information class
 *
 * @author Tom Doran
 */
public class PlayerInfo {

  private VBox info;
  private HBox container;

  /**
   * Construct extend player information
   *
   * @param container
   */
  public PlayerInfo(HBox container) {
    this.container = container;
    info = (VBox) container.lookup("#PlayerDetail");
  }

  /**
   * Get player name as a Label
   *
   * @return
   */
  public Label getName() {

    //return (Label) info.getChildren().get(0);
    return (Label) info.lookup("#PlayerName");
  }

  /**
   * Get player's bank balance as a Label
   *
   * @return
   */
  public Label getMoney() {

    //return (Label) info.getChildren().get(1);
    return (Label) info.lookup("#PlayerBalance");
  }

  /**
   * Get player's information box on GUI
   *
   * @return
   */
  public VBox getInfo() {
    return info;
  }

  /**
   * Get main container of player information on GUI
   *
   * @return
   */
  public HBox getContainer() {
    return container;
  }
}
