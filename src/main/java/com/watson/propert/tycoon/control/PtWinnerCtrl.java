package com.watson.propert.tycoon.control;

import com.watson.propert.tycoon.game.Game;
import com.watson.propert.tycoon.game.GameSetting;
import com.watson.propert.tycoon.game.entitys.Player;
import com.watson.propert.tycoon.gui.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;

public class PtWinnerCtrl {

  @FXML private Label WINNING_PLAYER;

  @FXML private Button BTN_NEW;

  @FXML private Button BTN_QUIT;

  void setWinner(GuiPlayer winner) {
    WINNING_PLAYER.setText(winner.getName());
  }

  @FXML
  void newGame(ActionEvent event) throws IOException {
    // get controller for main window
    URL fxmlUrl = ClassLoader.getSystemResource("ptGui.fxml");
    FXMLLoader loader = new FXMLLoader(fxmlUrl);
    loader.load();
    PtController controller = loader.getController();

    // call new game method in controller
    controller.newGame(event);
  }

  @FXML
  void quitGame(ActionEvent event) {
    System.exit(1);
  }
}
