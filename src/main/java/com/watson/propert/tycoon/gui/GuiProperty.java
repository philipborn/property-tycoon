package com.watson.propert.tycoon.gui;
/**
 * GUI Property Class. Describes a purchasable Property in the game
 *
 * @author Lee Richards
 * @version Sprint4
 */
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import com.google.common.collect.ImmutableList;

public class GuiProperty {
  GuiSquare square;
  int boardPosition;
  Optional<ImmutableList<Integer>> housePrices;

  public GuiProperty(GuiSquare square, Optional<ImmutableList<Integer>> housePrices) {
    this.square = square;
    this.boardPosition = 0;
    this.housePrices = housePrices;
  }

  public int getCurrentValue() {
    AtomicInteger i = new AtomicInteger();
    housePrices.ifPresentOrElse(
        (prices) -> {
          // use number of houses to index prices
          i.set(prices.get(square.numHouses).intValue());
        },
        () -> {
          // otherwise use price
          i.set(Integer.parseInt(square.getPrice()));
        });
    final int i1 = i.get();
    return i1;
  }

  public Optional<ImmutableList<Integer>> getHousePrices() {
    return housePrices;
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
    propGroup.setPrefWidth(width);
    propGroup.setAlignment(Pos.CENTER_LEFT);

    // Add houses to Property Group
    addHouses(propGroup, getNumHouses());

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

  // Add Houses or hotel to Property Group
  private void addHouses(HBox pg, int numHouses) {
    // Add Houses
    if (numHouses > 0 && numHouses <= 4) {
      for (int i = 0; i < numHouses; i++) {
        ImageView iv = new ImageView();
        iv.setFitHeight(pg.getPrefHeight() - 8);
        iv.setPreserveRatio(true);
        iv.setImage(
            new Image(ClassLoader.getSystemResource("board/houseLarge.png").toExternalForm()));
        pg.getChildren().add(iv);
      }
    }

    // Add Hotel
    if (numHouses > 4) {
      ImageView iv = new ImageView();
      iv.setFitHeight(pg.getPrefHeight() - 8);
      iv.setPreserveRatio(true);
      iv.setImage(
          new Image(ClassLoader.getSystemResource("board/hotelLarge.png").toExternalForm()));
      pg.getChildren().add(iv);
    }
  }
}
