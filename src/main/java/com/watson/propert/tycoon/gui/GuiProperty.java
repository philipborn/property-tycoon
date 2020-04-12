package com.watson.propert.tycoon.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * GUI Property Class. Describes a purchasable Property in the game
 *
 * @author Lee Richards
 * @version Sprint4
 */
public class GuiProperty {
  GuiSquare square;
  int boardPosition;

  public GuiProperty(GuiSquare square) {
    this.square = square;
    this.boardPosition = 0;
  }

  public PropertyGroup getGroup() {
    return square.getGroup();
  }

  public void setGroup(PropertyGroup group) {
    square.setGroup(group);
  }

  public String getName() {
    return square.getName();
  }

  /*
  public void setName(String name) {
    this.name = name;
  }

   */

  public String getPrice() {
    return square.getPrice();
  }

  /*
  public void setPrice(int price) {
    this.price = price;
  }
   */

  public int getNumHouses() {
    return square.numberOfHouses();
  }

  public void setNumHouses(int numHouses) {
    square.setNumHouses(numHouses);
  }

  public int getBoardPosition() {
    return boardPosition;
  }

  public void setBoardPosition(int boardPosition) {
    this.boardPosition = boardPosition;
  }

  /**
   * Generates a JavaFX Pane representation of the Property
   *
   * @param width Pixel width of Property card required
   * @return Pane
   */
  public Pane getPane(int width) {
    VBox propertyContainer = new VBox();
    propertyContainer.setId("PROPERTY_" + boardPosition);
    propertyContainer.setPrefWidth(width);
    propertyContainer.getStyleClass().add("card_bg");
    propertyContainer.setPadding(new Insets(4, 4, 4, 4));
    propertyContainer.setSpacing(width / 9.5);

    HBox propGroup = new HBox();
    propGroup.getStyleClass().add("card_group");
    propGroup.getStyleClass().add(getGroup().getCssClass());
    propGroup.setPadding(new Insets(3, 3, 3, 3));
    propGroup.setSpacing(3);
    propGroup.setPrefHeight(width / 3.8);
    propGroup.setAlignment(Pos.CENTER_LEFT);

    Label lName = new Label(getName());
    lName.setTextAlignment(TextAlignment.CENTER);
    lName.setAlignment(Pos.TOP_CENTER);
    lName.setWrapText(true);
    lName.setPrefHeight(width / 2.0);
    lName.setPrefWidth(width);
    lName.getStyleClass().add("card_name");
    lName.setFont(Font.font(width / 7.5));

    Label lPrice = new Label(getPrice());
    lPrice.setPrefWidth(width);
    lPrice.setAlignment(Pos.TOP_CENTER);
    lPrice.getStyleClass().add("card_sales_price");
    lPrice.setTextAlignment(TextAlignment.CENTER);
    lPrice.setFont(Font.font(width / 10.0));

    propertyContainer.getChildren().addAll(propGroup, lName, lPrice);
    return propertyContainer;
  }
}
