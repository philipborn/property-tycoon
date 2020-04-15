package com.watson.propert.tycoon.gui;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.RowConstraints;

public class GuiNewPlayer {
  TextField name;
  CheckBox ai;
  ImageView token;
  RowConstraints row;

  public GuiNewPlayer(TextField name, CheckBox ai, ImageView token, RowConstraints row) {
    this.name = name;
    this.ai = ai;
    this.token = token;
    this.row = row;
  }

  public TextField getName() {
    return name;
  }

  public void setName(TextField name) {
    this.name = name;
  }

  public CheckBox getAi() {
    return ai;
  }

  public void setAi(CheckBox ai) {
    this.ai = ai;
  }

  public RowConstraints getRow() {
    return row;
  }

  public ImageView getToken() {
    return token;
  }

  public void setToken(ImageView token) {
    this.token = token;
  }

  public void setRow(RowConstraints row) {
    this.row = row;
  }
}
