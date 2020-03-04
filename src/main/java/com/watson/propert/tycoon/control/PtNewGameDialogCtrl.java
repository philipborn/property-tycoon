package com.watson.propert.tycoon.control;
/**
 * New Game Dialog Box controller
 *
 * @author Lee Richards
 * @version Sprint3b
 */
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.watson.propert.tycoon.gui.*;

public class PtNewGameDialogCtrl {

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
  @FXML private ArrayList<GuiPlayer> enteredPlayers;

  private Logger logger = LoggerFactory.getLogger(App.class);

  public void setNewPlayers(ArrayList<GuiPlayer> players) {
    enteredPlayers = players;
  }

  @FXML
  void NEW_GAME(ActionEvent event) {
    int numPlayers = Integer.parseInt(NO_PLAYERS.getText());
    logger.debug("Num players entered: " + numPlayers);
    this.enteredPlayers.clear();
    for (int i = 0; i < numPlayers; i++) {
      // Only add a new player if name field is completed or AI is selected
      String name = newPlayers[i].getName().getText();
      Boolean ai = newPlayers[i].getAi().isSelected();
      if (name.length() == 0) {
        name = "Player " + i;
      }
      this.enteredPlayers.add(
          new GuiPlayer(name, new GuiToken(new HBox()), ai, new PlayerInfo(new VBox())));
    }
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
        makeRowVisible(i - 1);
      } else {
        makeRowInvisible(i - 1);
      }
    }
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

  @FXML
  void initialize() {
    asserts();
    enteredPlayers = new ArrayList<GuiPlayer>();
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
