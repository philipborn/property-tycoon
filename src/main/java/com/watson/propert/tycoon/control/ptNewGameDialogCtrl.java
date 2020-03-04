package com.watson.propert.tycoon.control;
/**
 * New Game Dialog Box controller
 * @author Lee Richards
 * @version Sprint3b
 */

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.watson.propert.tycoon.game.Player;
import com.watson.propert.tycoon.gui.App;
import com.watson.propert.tycoon.gui.GuiNewPlayer;

public class ptNewGameDialogCtrl {

  @FXML private ResourceBundle resources;

  @FXML private URL location;

  @FXML private Button PLAYERS_DOWN;

  @FXML private TextField NO_PLAYERS;

  @FXML private Button PLAYERS_UP;

  @FXML private RowConstraints ROW_PLAYER_1;

  @FXML private RowConstraints ROW_PLAYER_2;

  @FXML private RowConstraints ROW_PLAYER_3;

  @FXML private RowConstraints ROW_PLAYER_4;

  @FXML private RowConstraints ROW_PLAYER_5;

  @FXML private RowConstraints ROW_PLAYER_6;

  @FXML private ImageView TOKEN_PLAYER_1;

  @FXML private TextField NAME_PLAYER_1;

  @FXML private CheckBox AI_PLAYER_1;

  @FXML private ImageView TOKEN_PLAYER_2;

  @FXML private TextField NAME_PLAYER_2;

  @FXML private TextField NAME_PLAYER_3;

  @FXML private TextField NAME_PLAYER_5;

  @FXML private TextField NAME_PLAYER_4;

  @FXML private TextField NAME_PLAYER_6;

  @FXML private CheckBox AI_PLAYER_2;

  @FXML private CheckBox AI_PLAYER_3;

  @FXML private CheckBox AI_PLAYER_4;

  @FXML private CheckBox AI_PLAYER_5;

  @FXML private CheckBox AI_PLAYER_6;

  @FXML private ImageView TOKEN_PLAYER_3;

  @FXML private ImageView TOKEN_PLAYER_4;

  @FXML private ImageView TOKEN_PLAYER_5;

  @FXML private ImageView TOKEN_PLAYER_6;

  @FXML private Button QUIT_BUTTON;

  @FXML private Button NEW_GAME_BUTTON;

  @FXML private GuiNewPlayer[] newPlayers;
  private List<Player> enteredPlayers;
  Stage newGameStage;
  Logger logger = LoggerFactory.getLogger(App.class);

  @FXML
  void NEW_GAME(ActionEvent event) {
    ((Stage) NEW_GAME_BUTTON.getScene().getWindow()).close();
  }

  @FXML
  void QUIT_GAME(ActionEvent event) {
    ((Stage) NEW_GAME_BUTTON.getScene().getWindow()).close();
    Platform.exit();
  }

  // Decrease number of players in game by one. Minimum is two players
  @FXML
  void playersDown(ActionEvent event) {
    changeNumberOfPlayers(-1);
  }

  // Increase number of players in game by one. Max players is six.
  @FXML
  void playersUp(ActionEvent event) {
    changeNumberOfPlayers(1);
  }

  // Change number of players in game by adding value d
  void changeNumberOfPlayers(int d) {
    int numPlayers = Integer.parseInt(NO_PLAYERS.getText());
    numPlayers += d;
    if (numPlayers > 6) numPlayers = 6;
    if (numPlayers < 3) numPlayers = 2;
    NO_PLAYERS.setText(String.valueOf(numPlayers));
    this.updateVisibility();
  }

  // Hide player fields  > number of players, otherwise make visible.
  private void updateVisibility() {
    for (int i = 2; i <= 6; i++) {
      if (i <= Integer.parseInt(NO_PLAYERS.getText())) {
        logger.debug("Value: " + i);
        makeRowVisible(i - 1);
      } else {
        logger.debug("Value2: " + i);
        makeRowInvisible(i - 1);
      }
    }
  }

  // Returns List of new players
  public GuiNewPlayer[] getNewPlayers() {
    return this.newPlayers;
  }

  // Make a previously hidden row visible again
  private void makeRowVisible(int i) {
    newPlayers[i].getAi().setVisible(true);
    newPlayers[i].getName().setVisible(true);
    newPlayers[i].getRow().setPrefHeight(60);
  }

  // Hide a row
  private void makeRowInvisible(int i) {
    newPlayers[i].getAi().setVisible(false);
    newPlayers[i].getAi().selectedProperty().setValue(false);
    newPlayers[i].getName().setText("");
    newPlayers[i].getName().setVisible(false);
    newPlayers[i].getRow().setPrefHeight(0);
  }

  // Show dialog box and wait for response
  public void showDialog() throws IOException {
    newGameStage = new Stage();
    newGameStage.initModality(Modality.APPLICATION_MODAL);
    newGameStage.initStyle(StageStyle.UTILITY);

    // getting URL of fxml file
    URL fxmlUrl = ClassLoader.getSystemResource("ptNewGameDialog.fxml");
    Parent root = FXMLLoader.load(fxmlUrl);

    // Create Scene
    Scene scene = new Scene(root, 640, 480);

    // Load CSS stylesheet
    URL cssUrl = ClassLoader.getSystemResource("pt_dialog.css");
    scene.getStylesheets().clear();
    scene.getStylesheets().add(cssUrl.toExternalForm());

    // Set up stage to fill primary screen
    newGameStage.setTitle("Property Tycoon New Game");
    newGameStage.setScene(scene);
    newGameStage.showAndWait();
  }

  @FXML
  void initialize() throws IOException {
    asserts();
    //newGameStage = new Stage();
    NO_PLAYERS.setText("4");

    newPlayers =
        new GuiNewPlayer[] {
          new GuiNewPlayer(NAME_PLAYER_1, AI_PLAYER_1, ROW_PLAYER_1),
          new GuiNewPlayer(NAME_PLAYER_2, AI_PLAYER_2, ROW_PLAYER_2),
          new GuiNewPlayer(NAME_PLAYER_3, AI_PLAYER_3, ROW_PLAYER_3),
          new GuiNewPlayer(NAME_PLAYER_4, AI_PLAYER_4, ROW_PLAYER_4),
          new GuiNewPlayer(NAME_PLAYER_5, AI_PLAYER_5, ROW_PLAYER_5),
          new GuiNewPlayer(NAME_PLAYER_6, AI_PLAYER_6, ROW_PLAYER_6)
        };
    makeRowInvisible(4);
    makeRowInvisible(5);
  }

  private void asserts() {
    assert PLAYERS_DOWN != null
        : "fx:id=\"PLAYERS_DOWN\" was not injected: check your FXML file 'ptNewGameDialog.fxml'.";
    assert NO_PLAYERS != null
        : "fx:id=\"NO_PLAYERS\" was not injected: check your FXML file 'ptNewGameDialog.fxml'.";
    assert PLAYERS_UP != null
        : "fx:id=\"PLAYERS_UP\" was not injected: check your FXML file 'ptNewGameDialog.fxml'.";
    assert ROW_PLAYER_1 != null
        : "fx:id=\"ROW_PLAYER_1\" was not injected: check your FXML file 'ptNewGameDialog.fxml'.";
    assert ROW_PLAYER_2 != null
        : "fx:id=\"ROW_PLAYER_2\" was not injected: check your FXML file 'ptNewGameDialog.fxml'.";
    assert ROW_PLAYER_3 != null
        : "fx:id=\"ROW_PLAYER_3\" was not injected: check your FXML file 'ptNewGameDialog.fxml'.";
    assert ROW_PLAYER_4 != null
        : "fx:id=\"ROW_PLAYER_4\" was not injected: check your FXML file 'ptNewGameDialog.fxml'.";
    assert ROW_PLAYER_5 != null
        : "fx:id=\"ROW_PLAYER_5\" was not injected: check your FXML file 'ptNewGameDialog.fxml'.";
    assert ROW_PLAYER_6 != null
        : "fx:id=\"ROW_PLAYER_6\" was not injected: check your FXML file 'ptNewGameDialog.fxml'.";
    assert TOKEN_PLAYER_1 != null
        : "fx:id=\"TOKEN_PLAYER_1\" was not injected: check your FXML file 'ptNewGameDialog.fxml'.";
    assert NAME_PLAYER_1 != null
        : "fx:id=\"NAME_PLAYER_1\" was not injected: check your FXML file 'ptNewGameDialog.fxml'.";
    assert AI_PLAYER_1 != null
        : "fx:id=\"AI_PLAYER_1\" was not injected: check your FXML file 'ptNewGameDialog.fxml'.";
    assert TOKEN_PLAYER_2 != null
        : "fx:id=\"TOKEN_PLAYER_2\" was not injected: check your FXML file 'ptNewGameDialog.fxml'.";
    assert NAME_PLAYER_2 != null
        : "fx:id=\"NAME_PLAYER_2\" was not injected: check your FXML file 'ptNewGameDialog.fxml'.";
    assert NAME_PLAYER_3 != null
        : "fx:id=\"NAME_PLAYER_3\" was not injected: check your FXML file 'ptNewGameDialog.fxml'.";
    assert NAME_PLAYER_5 != null
        : "fx:id=\"NAME_PLAYER_5\" was not injected: check your FXML file 'ptNewGameDialog.fxml'.";
    assert NAME_PLAYER_4 != null
        : "fx:id=\"NAME_PLAYER_4\" was not injected: check your FXML file 'ptNewGameDialog.fxml'.";
    assert NAME_PLAYER_6 != null
        : "fx:id=\"NAME_PLAYER_6\" was not injected: check your FXML file 'ptNewGameDialog.fxml'.";
    assert AI_PLAYER_2 != null
        : "fx:id=\"AI_PLAYER_2\" was not injected: check your FXML file 'ptNewGameDialog.fxml'.";
    assert AI_PLAYER_3 != null
        : "fx:id=\"AI_PLAYER_3\" was not injected: check your FXML file 'ptNewGameDialog.fxml'.";
    assert AI_PLAYER_4 != null
        : "fx:id=\"AI_PLAYER_4\" was not injected: check your FXML file 'ptNewGameDialog.fxml'.";
    assert AI_PLAYER_5 != null
        : "fx:id=\"AI_PLAYER_5\" was not injected: check your FXML file 'ptNewGameDialog.fxml'.";
    assert AI_PLAYER_6 != null
        : "fx:id=\"AI_PLAYER_6\" was not injected: check your FXML file 'ptNewGameDialog.fxml'.";
    assert TOKEN_PLAYER_3 != null
        : "fx:id=\"TOKEN_PLAYER_3\" was not injected: check your FXML file 'ptNewGameDialog.fxml'.";
    assert TOKEN_PLAYER_4 != null
        : "fx:id=\"TOKEN_PLAYER_4\" was not injected: check your FXML file 'ptNewGameDialog.fxml'.";
    assert TOKEN_PLAYER_5 != null
        : "fx:id=\"TOKEN_PLAYER_5\" was not injected: check your FXML file 'ptNewGameDialog.fxml'.";
    assert TOKEN_PLAYER_6 != null
        : "fx:id=\"TOKEN_PLAYER_6\" was not injected: check your FXML file 'ptNewGameDialog.fxml'.";
    assert QUIT_BUTTON != null
        : "fx:id=\"QUIT_BUTTON\" was not injected: check your FXML file 'ptNewGameDialog.fxml'.";
    assert NEW_GAME_BUTTON != null
        : "fx:id=\"NEW_GAME_BUTTON\" was not injected: check your FXML file 'ptNewGameDialog.fxml'.";
  }
}
