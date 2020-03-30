package com.watson.propert.tycoon.gui;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.RowConstraints;

public class GuiNewPlayer {
  TextField name;
  CheckBox ai;
  RowConstraints row;

  public GuiNewPlayer(TextField name, CheckBox ai, RowConstraints row) {
    this.name = name;
    this.ai = ai;
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

  public void setRow(RowConstraints row) {
    this.row = row;
  }
}
