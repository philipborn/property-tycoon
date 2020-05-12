package com.watson.propert.tycoon.gui;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.RowConstraints;

/**
 * Create a New Player, for use with the New Game Dialog
 *
 * @author Lee Richards
 */
public class GuiNewPlayer {
  TextField name;
  CheckBox ai;
  ImageView token;
  RowConstraints row;

  /**
   * Construct a new GuiNewPlayer
   *
   * @param name Name of player
   * @param ai true if AI, false if human
   * @param token ImageView of token
   * @param row RowConstraint on New Game Dialog grid
   */
  public GuiNewPlayer(TextField name, CheckBox ai, ImageView token, RowConstraints row) {
    this.name = name;
    this.ai = ai;
    this.token = token;
    this.row = row;
  }

  /**
   * Get the name of the player
   *
   * @return
   */
  public TextField getName() {
    return name;
  }

  /**
   * Set the name of player
   *
   * @param name
   */
  public void setName(TextField name) {
    this.name = name;
  }

  /**
   * Is this a real player or an AI player?
   *
   * @return true if AI, false if real
   */
  public CheckBox getAi() {
    return ai;
  }

  /**
   * Set player as an artificial intelligence
   *
   * @param ai
   */
  public void setAi(CheckBox ai) {
    this.ai = ai;
  }

  /**
   * Get a player row from New Player dialog
   *
   * @return
   */
  public RowConstraints getRow() {
    return row;
  }

  /**
   * Get the token assigned to this player
   *
   * @return
   */
  public ImageView getToken() {
    return token;
  }

  /**
   * Set the token for this player
   *
   * @param token
   */
  public void setToken(ImageView token) {
    this.token = token;
  }

  /**
   * Add a player row to New Game Dialog
   *
   * @param row
   */
  public void setRow(RowConstraints row) {
    this.row = row;
  }
}
