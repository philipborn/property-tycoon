package com.watson.propert.tycoon.control;

import java.io.IOException;
import java.net.URL;
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
/**
 * Game Over Dialog Box Wrapper
 *
 * @author Lee Richards
 * @version Sprint5
 */
public class GameOver {
  Stage gameOverStage;
  FXMLLoader loader;
  Logger logger = LoggerFactory.getLogger(App.class);
  PtWinnerCtrl controller;

  public GameOver() throws IOException {
    gameOverStage = new Stage();
    gameOverStage.initModality(Modality.APPLICATION_MODAL);
    gameOverStage.initStyle(StageStyle.UTILITY);
    // getting URL of fxml file
    URL fxmlUrl = ClassLoader.getSystemResource("ptWinnerScreen.fxml");
    loader = new FXMLLoader(fxmlUrl);
    Parent root = loader.load();

    // Create Scene
    Scene scene = new Scene(root, 600, 400);

    // Load CSS stylesheet
    URL cssUrl = ClassLoader.getSystemResource("pt_dialog.css");
    scene.getStylesheets().clear();
    scene.getStylesheets().add(cssUrl.toExternalForm());

    controller = loader.getController();
    logger.debug("Controller: " + (controller != null));
    // Set up stage to fill primary screen
    gameOverStage.setTitle("Property Tycoon Game Over");
    gameOverStage.setScene(scene);
  }

  /**
   * Display Game Over Dialog
   */
  public void showDialog() {
    gameOverStage.showAndWait();
  }

  /**
   * Is a New Game requested
   * @return new game
   */
  public boolean doNewGame() {
    return controller.doNewGame();
  }

  /**
   * Initialise Winning Player
   * @param winner
   */
  public void setWinner(GuiPlayer winner) {
    controller.setWinner(winner);
  }
}
