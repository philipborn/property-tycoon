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

  /**
   * Construct a new GuiToken
   *
   * @param token Pane on game board
   */
  public GuiToken(Pane token) {
    this.token = token;
    this.position = 0;
  }

  /**
   * Construct a new GuiToken
   *
   * @param token Pane on game board
   * @param position Position on game board
   */
  public GuiToken(Pane token, int position) {
    this.token = token;
    this.position = position;
  }

  /**
   * Get the token's Pane from game board
   *
   * @return
   */
  public Pane getToken() {
    return token;
  }

  /**
   * Set the token's Pane on game board
   *
   * @param token
   */
  public void setToken(Pane token) {
    this.token = token;
  }

  /**
   * Get the index position of the token on the game board
   *
   * @return
   */
  public int getPosition() {
    return position;
  }

  /**
   * Set the index position of the token on the game board
   *
   * @param position index position
   */
  public void setPosition(int position) {
    this.position = position;
  }

  /** Move the token one square forward */
  public void moveForwards() {
    this.position = (this.position + 1) % GuiGameBoard.length;
  }

  /** Move the token one square backwards */
  public void moveBackwards() {
    this.position = (this.position + GuiGameBoard.length - 1) % GuiGameBoard.length;
  }

  /**
   * Get the Image used for the token
   *
   * @return
   */
  public Image getImage() {
    ImageView iv = (ImageView) ((HBox) token).getChildren().get(0);
    return iv.getImage();
  }
}
