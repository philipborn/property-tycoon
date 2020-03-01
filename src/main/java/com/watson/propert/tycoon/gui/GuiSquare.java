package com.watson.propert.tycoon.gui;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

/**
 * Represents a GUI Square on the game board
 *
 * @author Lee Richards
 * @version Sprint3
 */
public class GuiSquare {
  Pane pane;
  GuiCoords centre;

  public GuiSquare(Pane p) {
    this.pane = p;
    this.centre = new GuiCoords(0.0, 0.0);
  }

  public GuiSquare(Pane p, GuiCoords xy) {
    this.pane = p;
    this.centre = xy;
  }

  /**
   * Get square's Pane on GUI
   *
   * @return Pane
   */
  public Pane getPane() {
    return pane;
  }

  /**
   * Set square Pane on GUI.
   *
   * @param pane
   */
  public void setPane(Pane pane) {
    this.pane = pane;
  }

  /**
   * Get square's centre coordinates relative to game board.
   *
   * @return GuiCoords
   */
  public GuiCoords getCentre() {
    return centre;
  }

  /**
   * Set square's centre coordinates relative to game board.
   *
   * @param centre
   */
  public void setCentre(GuiCoords centre) {
    this.centre = centre;
  }

  /**
   * Draw a small circle on Pane indicating centre position of this square.
   *
   * @param p
   */
  public void drawCentre(Pane p) {
    p.getChildren().add(new Circle(centre.getX(), centre.getY(), 4));
  }
}
