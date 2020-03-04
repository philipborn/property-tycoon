package com.watson.propert.tycoon.control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.watson.propert.tycoon.gui.App;
import com.watson.propert.tycoon.gui.GuiPlayer;

public class NewGame {
  ArrayList<GuiPlayer> players;
  Stage newGameStage;
  FXMLLoader loader;
  Logger logger = LoggerFactory.getLogger(App.class);

  public NewGame() throws IOException {
    players = new ArrayList<GuiPlayer>();
    newGameStage = new Stage();
    newGameStage.initModality(Modality.APPLICATION_MODAL);
    newGameStage.initStyle(StageStyle.UTILITY);
    // getting URL of fxml file
    URL fxmlUrl = ClassLoader.getSystemResource("ptNewGameDialog.fxml");
    loader = new FXMLLoader(fxmlUrl);
    Parent root = loader.load();

    // Create Scene
    Scene scene = new Scene(root, 640, 480);

    // Load CSS stylesheet
    URL cssUrl = ClassLoader.getSystemResource("pt_dialog.css");
    scene.getStylesheets().clear();
    scene.getStylesheets().add(cssUrl.toExternalForm());

    PtNewGameDialogCtrl controller = loader.getController();
    controller.setNewPlayers(players);
    logger.debug("Controller: " + (controller != null));
    // Set up stage to fill primary screen
    newGameStage.setTitle("Property Tycoon New Game");
    newGameStage.setScene(scene);
  }

  public void showDialog() {
    newGameStage.showAndWait();
  }

  public ArrayList<GuiPlayer> getNewPlayers() {
    return players;
  }
}
