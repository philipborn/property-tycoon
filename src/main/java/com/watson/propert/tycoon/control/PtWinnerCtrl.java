package com.watson.propert.tycoon.control;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import com.watson.propert.tycoon.gui.*;

public class PtWinnerCtrl {

  @FXML private Label WINNING_PLAYER;

  @FXML private Button BTN_NEW;

  @FXML private Button BTN_QUIT;

  void setWinner(GuiPlayer winner) {
    WINNING_PLAYER.setText(winner.getName());
  }

  private boolean newGame = false;

  @FXML
  void newGame(ActionEvent event) throws IOException {
    // New Game
    newGame = true;
    ((Stage) BTN_NEW.getScene().getWindow()).close();
  }

  @FXML
  void quitGame(ActionEvent event) {
    // Exit Game
    newGame = false;
    ((Stage) BTN_NEW.getScene().getWindow()).close();
  }

  public boolean doNewGame() {
    return newGame;
  }

  @FXML
  void initialize() {
    newGame = false;
  }
}
