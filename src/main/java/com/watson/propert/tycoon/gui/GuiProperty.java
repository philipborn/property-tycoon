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
  PropertyGroup group;
  String name;
  int price;
  int numHouses;
  int boardPosition;

  public GuiProperty(String name, int price, PropertyGroup group) {
    this.name = name;
    this.price = price;
    this.group = group;
    this.numHouses = 0;
    this.boardPosition = 0;
  }

  public PropertyGroup getGroup() {
    return group;
  }

  public void setGroup(PropertyGroup group) {
    this.group = group;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getNumHouses() {
    return numHouses;
  }

  public void setNumHouses(int numHouses) {
    this.numHouses = numHouses;
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
    propGroup.getStyleClass().add(group.getCssClass());
    propGroup.setPadding(new Insets(3, 3, 3, 3));
    propGroup.setSpacing(3);
    propGroup.setPrefHeight(width / 3.8);
    propGroup.setAlignment(Pos.CENTER_LEFT);

    Label lName = new Label(name);
    lName.setTextAlignment(TextAlignment.CENTER);
    lName.setAlignment(Pos.TOP_CENTER);
    lName.setWrapText(true);
    lName.setPrefHeight(width / 2.0);
    lName.setPrefWidth(width);
    lName.getStyleClass().add("card_name");
    lName.setFont(Font.font(width / 7.5));

    Label lPrice = new Label("" + price);
    lPrice.setPrefWidth(width);
    lPrice.setAlignment(Pos.TOP_CENTER);
    lPrice.getStyleClass().add("card_sales_price");
    lPrice.setTextAlignment(TextAlignment.CENTER);
    lPrice.setFont(Font.font(width / 10.0));

    propertyContainer.getChildren().addAll(propGroup, lName, lPrice);
    return propertyContainer;
  }
}
