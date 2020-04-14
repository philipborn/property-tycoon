package com.watson.propert.tycoon.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * GUI Token Class. Describes a Token on the game board
 *
 * @author Lee Richards
 * @version Sprint3
 */
public class GuiToken {
  Pane token;
  int position;
  GuiCoords centre;

  public GuiToken(Pane token) {
    this.token = token;
    this.position = 0;
  }

  public GuiToken(Pane token, int position) {
    this.token = token;
    this.position = position;
  }

  public Pane getToken() {
    return token;
  }

  public void setToken(Pane token) {
    this.token = token;
  }

  public int getPosition() {
    return position;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  public void moveForwards() {
    this.position = (this.position + 1) % GuiGameBoard.length;
  }

  public void moveBackwards() {
    this.position = (this.position + GuiGameBoard.length - 1) % GuiGameBoard.length;
  }

  public Image getImage() {
    ImageView iv = (ImageView) ((HBox) token).getChildren().get(0);
    return iv.getImage();
  }
}
