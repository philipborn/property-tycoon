package com.watson.propert.tycoon.gui;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

/**
 * Represents a GUI Square on the game board
 *
 * @author Lee Richards
 * @version Sprint4
 */
public class GuiSquare {
  Pane pane;
  GuiCoords centre;
  int numHouses;
  PropertyGroup group;

  public GuiSquare(Pane p) {
    this.pane = p;
    this.centre = new GuiCoords(0.0, 0.0);
    this.numHouses = 0;
    this.group = PropertyGroup.UTILITIES;
  }

  public GuiSquare(Pane p, GuiCoords xy) {
    this.pane = p;
    this.centre = xy;
  }

  public PropertyGroup getGroup() {
    return group;
  }

  public void setGroup(PropertyGroup group) {
    this.group = group;
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

  /**
   * Get Property Name
   *
   * @return name
   */
  public String getName() {
    Node name = pane.lookup("#PROPERTY_NAME");
    if (name instanceof Label) {
      return ((Label) name).getText();
    }
    return "* Not Found *";
  }

  /**
   * Get Property Price
   *
   * @return price
   */
  public String getPrice() {
    Node name = pane.lookup("#PROPERTY_PRICE");
    if (name instanceof Label) {
      return ((Label) name).getText();
    }
    return "* Not Found *";
  }

  /** Adds an house or hotel to the Property Group */
  public void addHouse() {
    Node group = pane.lookup("#PROPERTY_GROUP");
    if (group != null) {
      ImageView iv = new ImageView();
      iv.setFitWidth(((HBox) group).getPrefWidth() / 4.0);
      iv.setFitHeight(((HBox) group).getPrefHeight());
      numHouses++;
      if (numHouses > 4) {
        // add hotel
        iv.setImage(
            new Image(ClassLoader.getSystemResource("board/hotelLarge.png").toExternalForm()));
        ((Pane) group).getChildren().clear();
      } else {
        // add house
        iv.setImage(
            new Image(ClassLoader.getSystemResource("board/houseLarge.png").toExternalForm()));
      }
      ((Pane) group).getChildren().add(iv);
    }
  }

  /**
   * Get the number of houses on a Property Square 5 represents a hotel
   *
   * @return int number of houses
   */
  public int numberOfHouses() {
    return numHouses;
  }

  /**
   * Set the number of houses on a Property Square
   *
   * @param n number of houses
   */
  public void setNumHouses(int n) {
    numHouses = n;
  }
}
