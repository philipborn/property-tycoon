/*
 * MIT License
 *
 * Copyright (c) 2020 Philip Borndalen
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.watson.propert.tycoon.control;

import static java.lang.StrictMath.abs;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.util.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.Subscribe;
import com.watson.propert.tycoon.game.*;
import com.watson.propert.tycoon.gui.*;
import com.watson.propert.tycoon.io.BoardReaderJson;

public class PtController {

  @FXML private ResourceBundle resources;

  @FXML private URL location;

  @FXML private BorderPane MAIN_WINDOW;

  @FXML private Pane GAME_BOARD_CONTAINER;

  @FXML private GridPane GAME_GRID;

  @FXML private StackPane SQUARE_9;

  @FXML private StackPane SQUARE_8;

  @FXML private StackPane SQUARE_7;

  @FXML private StackPane SQUARE_6;

  @FXML private StackPane SQUARE_5;

  @FXML private StackPane SQUARE_4;

  @FXML private StackPane SQUARE_3;

  @FXML private StackPane SQUARE_2;

  @FXML private StackPane SQUARE_1;

  @FXML private StackPane SQUARE_0;

  @FXML private HBox TOKEN_PLAYER_1;
  @FXML private HBox TOKEN_PLAYER_2;
  @FXML private HBox TOKEN_PLAYER_3;
  @FXML private HBox TOKEN_PLAYER_4;
  @FXML private HBox TOKEN_PLAYER_5;
  @FXML private HBox TOKEN_PLAYER_6;

  @FXML private StackPane SQUARE_10;

  @FXML private StackPane SQUARE_30;

  @FXML private StackPane SQUARE_20;

  @FXML private StackPane SQUARE_19;

  @FXML private StackPane SQUARE_18;

  @FXML private StackPane SQUARE_17;

  @FXML private StackPane SQUARE_16;

  @FXML private StackPane SQUARE_15;

  @FXML private StackPane SQUARE_14;

  @FXML private StackPane SQUARE_13;

  @FXML private StackPane SQUARE_12;

  @FXML private StackPane SQUARE_11;

  @FXML private StackPane SQUARE_29;

  @FXML private StackPane SQUARE_28;

  @FXML private StackPane SQUARE_27;

  @FXML private StackPane SQUARE_26;

  @FXML private StackPane SQUARE_25;

  @FXML private StackPane SQUARE_24;

  @FXML private StackPane SQUARE_23;

  @FXML private StackPane SQUARE_22;

  @FXML private StackPane SQUARE_21;

  @FXML private StackPane SQUARE_39;

  @FXML private StackPane SQUARE_38;

  @FXML private StackPane SQUARE_37;

  @FXML private StackPane SQUARE_36;

  @FXML private StackPane SQUARE_35;

  @FXML private StackPane SQUARE_34;

  @FXML private StackPane SQUARE_33;

  @FXML private StackPane SQUARE_32;

  @FXML private StackPane SQUARE_31;

  @FXML private HBox STREET_1;

  @FXML private HBox STREET_2;

  @FXML private HBox STREET_3;

  @FXML private HBox STREET_4;

  @FXML private HBox DICE;

  @FXML private Label DICE_1;

  @FXML private Label DICE_2;

  @FXML private ImageView DICE_IMG_1;

  @FXML private ImageView DICE_IMG_2;

  @FXML private VBox PLAYER_1;

  @FXML private VBox PLAYER_3;

  @FXML private VBox PLAYER_5;

  @FXML private VBox BUTTON_CONTAINER;

  @FXML private Button NEW_GAME;

  @FXML private Button END_GAME;

  @FXML private VBox PLAYER_2;

  @FXML private VBox PLAYER_4;

  @FXML private VBox PLAYER_6;

  @FXML private VBox BUTTON_CONTAINER1;

  @FXML private TextArea MESSAGE_AREA;

  @FXML private Rectangle JAIL;

  private GuiGameBoard gameBoard;

  private PropertTycoon game;

  @FXML
  void endGame(ActionEvent event) {
    /*
      save game functionality
    */

    Platform.exit();
  }

  @FXML
  void newGame(ActionEvent event) {
    //goToJail();
    //moveBackThreeSpaces();
    yes();
  }

  private void yes() {
    game.buyProperty();
  }

  @FXML
  void throwDice(MouseEvent event) {
    game.throwDicesAndMove();
  }

  @Subscribe
  void diceHandler(DiceEvent event) {
    // move functionality
    int i = event.firstDice() + event.secondDice();

    MESSAGE_AREA.setText(gameBoard.getCurrentPlayer().getName() + " move: " + i + " spaces");
    //DICE_1.setText(Integer.toString(event.firstDice()));
    //DICE_2.setText(Integer.toString(event.secondDice()));
    DICE_IMG_1.setImage(gameBoard.diceFace(event.firstDice()));
    DICE_IMG_2.setImage(gameBoard.diceFace(event.secondDice()));
    move(i);
    gameBoard.getNextPlayer();
  }

  void moveBackThreeSpaces() {
    move(-3);
    changeTurn();
  }

  private void displayMessage(String message) {
    MESSAGE_AREA.setText(message);
  }

  void changeTurn() {
    // can utilise style sheets, white & transparent is just to show the functionality
    // inactiveplayer.setStyle("-fx-background-color:TRANSPARENT");
    // activeplayer.setStyle("-fx-background-color:WHITE");
  }

  @Subscribe
  void updateMoney(CashEvent event) {
    Label l = (Label) gameBoard.getCurrentPlayer().getInfo().getChildren().get(1);
    l.setText("" + event.getNewCash());
  }

  void goToJail() {}

  void move(int spaces) {
    GuiToken tok = gameBoard.getCurrentPlayer().getToken();
    PathTransition pt = new PathTransition();
    Path p =
        new Path(
            new MoveTo(tok.getToken().getTranslateX() + 25, tok.getToken().getTranslateY() + 25));
    GuiCoords newPos = gameBoard.getSquare(tok.getPosition()).getCentre();
    p.getElements().add(new LineTo(newPos.getX(), newPos.getY()));

    for (int i = 0; i < abs(spaces); i++) {
      if (spaces < 0) {
        tok.moveBackwards();
      } else tok.moveForwards();
      newPos = gameBoard.getSquare(tok.getPosition()).getCentre();
      p.getElements().add(new LineTo(newPos.getX(), newPos.getY()));
    }
    pt.setNode(tok.getToken());
    pt.setDuration(Duration.millis(abs(spaces) * 300));
    pt.setPath(p);
    pt.play();
  }

  @Subscribe
  void propertyFunctionality(BuyOrNotMsg msg) {
    displayMessage("Would you like to buy " + msg.propName + " for " + msg.price + "?");
  }

  // Calculate the centre point of each square relative to game board Pane
  void calculateSquareCentres() {
    Logger logger = LoggerFactory.getLogger(App.class);
    Direction dir = Direction.DOWN;
    Double tileHeight = SQUARE_1.getPrefHeight();
    Double tileWidth = SQUARE_1.getPrefWidth();
    Double halfHeight = tileHeight / 2;
    Double halfWidth = tileWidth / 2;
    Double board_size = GAME_BOARD_CONTAINER.getPrefHeight();
    GuiCoords xy = new GuiCoords(board_size - halfHeight, board_size - tileHeight + halfWidth);

    for (GuiSquare sq : gameBoard.getSquares()) {
      if (sq.getPane().getChildren().get(0) instanceof VBox) {
        sq.setCentre(xy);
        //sq.drawCentre(GAME_BOARD_CONTAINER);
        xy = xy.moveCoords(tileWidth, dir);
      } else {
        // Turn a corner
        xy = xy.moveCoords(halfHeight - halfWidth, dir);
        sq.setCentre(xy);
        //sq.drawCentre(GAME_BOARD_CONTAINER);
        dir = dir.turnRight();
        xy = xy.moveCoords(halfHeight + halfWidth, dir);
      }
    }
  }

  // Rescales the game board for different screen resolutions/DPI combinations
  void rescaleGameBoard(Double scaleFactor) {
    Double default_border = 2.0; //4.0
    Double default_board_size = gameBoard.getBoardWidth() * scaleFactor; //800.0
    Double default_street_height = gameBoard.getCornerSize() * scaleFactor; //103.0
    Double default_street_width = default_board_size - (2.0 * default_street_height);
    Double default_tile_width = default_street_width / gameBoard.getPropertiesPerStreet();
    Double default_tile_height = default_street_height;
    Double default_corner_inner_size = default_street_height - default_border;
    Double default_tile_inner_height = default_street_height - default_border;
    Double default_tile_inner_width = default_tile_width - 2;
    Double default_house_block_height = 0.22 * default_street_height * scaleFactor;
    Double default_property_name_height = 0.55 * default_street_height * scaleFactor;
    Double default_price_height = 0.22 * default_street_height * scaleFactor;

    // Resize game board containers
    GAME_BOARD_CONTAINER.setPrefSize(default_board_size, default_board_size);
    GAME_GRID.getColumnConstraints().get(0).setPrefWidth(default_street_height);
    GAME_GRID.getColumnConstraints().get(1).setPrefWidth(default_street_width);
    GAME_GRID.getColumnConstraints().get(2).setPrefWidth(default_street_height);
    GAME_GRID.getRowConstraints().get(0).setPrefHeight(default_street_height);
    GAME_GRID.getRowConstraints().get(1).setPrefHeight(default_street_width);
    GAME_GRID.getRowConstraints().get(2).setPrefHeight(default_street_height);

    TOKEN_PLAYER_1.setTranslateX(default_board_size - default_tile_height);
    TOKEN_PLAYER_2.setTranslateX(default_board_size - default_tile_height);
    TOKEN_PLAYER_3.setTranslateX(default_board_size - default_tile_height);
    TOKEN_PLAYER_4.setTranslateX(default_board_size - default_tile_height);
    TOKEN_PLAYER_5.setTranslateX(default_board_size - default_tile_height);
    TOKEN_PLAYER_6.setTranslateX(default_board_size - default_tile_height);

    TOKEN_PLAYER_1.setTranslateY(default_board_size - default_tile_height);
    TOKEN_PLAYER_2.setTranslateY(default_board_size - default_tile_height);
    TOKEN_PLAYER_3.setTranslateY(default_board_size - default_tile_height);
    TOKEN_PLAYER_4.setTranslateY(default_board_size - default_tile_height);
    TOKEN_PLAYER_5.setTranslateY(default_board_size - default_tile_height);
    TOKEN_PLAYER_6.setTranslateY(default_board_size - default_tile_height);

    // Resize Street of properties
    STREET_1.setPrefSize(default_street_width, default_street_height);
    STREET_2.setPrefSize(default_street_width, default_street_height);
    STREET_3.setPrefSize(default_street_width, default_street_height);
    STREET_4.setPrefSize(default_street_width, default_street_height);
    JAIL.setHeight(default_corner_inner_size * 0.7);
    JAIL.setWidth(default_corner_inner_size * 0.7);

    // Resize squares
    for (GuiSquare sq : gameBoard.getSquares()) {
      if (sq.getPane().getChildren().get(0) instanceof VBox) {
        sq.getPane().setPrefHeight(default_tile_height);
        sq.getPane().setPrefWidth(default_tile_width);
        ((Pane) sq.getPane().lookup("#PANEL"))
            .setPrefSize(default_tile_inner_width, default_tile_inner_height);
        ((Pane) sq.getPane().lookup("#PROPERTY_GROUP"))
            .setPrefSize(default_tile_inner_width, default_house_block_height);
        ((Label) sq.getPane().lookup("#PROPERTY_NAME"))
            .setPrefSize(default_tile_inner_width, default_property_name_height);
        ((Label) sq.getPane().lookup("#PROPERTY_PRICE"))
            .setPrefSize(default_tile_inner_width, default_price_height);
      } else {
        sq.getPane().setPrefSize(default_street_height, default_street_height);
        ((HBox) sq.getPane().getChildren().get(0))
            .setPrefSize(default_corner_inner_size, default_corner_inner_size);
      }
    }
    calculateSquareCentres();
  }

  @FXML
  void initialize() {
    Logger logger = LoggerFactory.getLogger(App.class);
    checkNotNull();
    // Build GuiSquare array
    GuiSquare[] guiSquares =
        new GuiSquare[] {
          new GuiSquare(SQUARE_0),
          new GuiSquare(SQUARE_1),
          new GuiSquare(SQUARE_2),
          new GuiSquare(SQUARE_3),
          new GuiSquare(SQUARE_4),
          new GuiSquare(SQUARE_5),
          new GuiSquare(SQUARE_6),
          new GuiSquare(SQUARE_7),
          new GuiSquare(SQUARE_8),
          new GuiSquare(SQUARE_9),
          new GuiSquare(SQUARE_10),
          new GuiSquare(SQUARE_11),
          new GuiSquare(SQUARE_12),
          new GuiSquare(SQUARE_13),
          new GuiSquare(SQUARE_14),
          new GuiSquare(SQUARE_15),
          new GuiSquare(SQUARE_16),
          new GuiSquare(SQUARE_17),
          new GuiSquare(SQUARE_18),
          new GuiSquare(SQUARE_19),
          new GuiSquare(SQUARE_20),
          new GuiSquare(SQUARE_21),
          new GuiSquare(SQUARE_22),
          new GuiSquare(SQUARE_23),
          new GuiSquare(SQUARE_24),
          new GuiSquare(SQUARE_25),
          new GuiSquare(SQUARE_26),
          new GuiSquare(SQUARE_27),
          new GuiSquare(SQUARE_28),
          new GuiSquare(SQUARE_29),
          new GuiSquare(SQUARE_30),
          new GuiSquare(SQUARE_31),
          new GuiSquare(SQUARE_32),
          new GuiSquare(SQUARE_33),
          new GuiSquare(SQUARE_34),
          new GuiSquare(SQUARE_35),
          new GuiSquare(SQUARE_36),
          new GuiSquare(SQUARE_37),
          new GuiSquare(SQUARE_38),
          new GuiSquare(SQUARE_39)
        };

    // Initialise players - FOR TESTING
    // To be set up by New Game Dialog box
    GuiPlayer[] players =
        new GuiPlayer[] {
          new GuiPlayer("Player 1", new GuiToken(TOKEN_PLAYER_1, 0), false, PLAYER_1),
          new GuiPlayer("Player 2", new GuiToken(TOKEN_PLAYER_2, 0), false, PLAYER_2),
          new GuiPlayer("Player 3", new GuiToken(TOKEN_PLAYER_3, 0), true, PLAYER_3),
          new GuiPlayer("Player 4", new GuiToken(TOKEN_PLAYER_4, 0), true, PLAYER_4),
          new GuiPlayer("Player 5", new GuiToken(TOKEN_PLAYER_5, 0), true, PLAYER_5),
          new GuiPlayer("Player 6", new GuiToken(TOKEN_PLAYER_6, 0), true, PLAYER_6)
        };

    gameBoard = new GuiGameBoard(GAME_BOARD_CONTAINER);
    gameBoard.setSquares(guiSquares);
    gameBoard.setPlayers(players);
    // Scale game board based on screen DPI
    rescaleGameBoard(1 / Screen.getPrimary().getOutputScaleX());

    // read JSON file
    BoardReaderJson boardReader = new BoardReaderJson();
    boardReader.readFile("src/main/resources/boardDataJSON.json");

    game = Game.newGame();
    game.registerListener(this);

    // for every square on the board
    for (GuiSquare sq : gameBoard.getSquares()) {
      boardReader.nextObject();

      // if square is a non-corner square
      if (sq.getPane().getChildren().get(0) instanceof VBox) {

        // access elements of square
        VBox v = (VBox) sq.getPane().getChildren().get(0);
        HBox group = (HBox) v.getChildren().get(0);
        Label name = (Label) v.getChildren().get(1);
        Label price = (Label) v.getChildren().get(2);

        // set group names (if square is in a group)
        if (boardReader.getProperties().get("group") != null) {
          group.setId(
              "PROPERTY_GROUP_"
                  + boardReader.getProperties().get("group").toUpperCase().replace(' ', '_'));
          logger.debug(group.getId());
        }

        // set name/price values
        name.setText(boardReader.getProperties().get("name"));
        price.setText(boardReader.getProperties().get("cost"));
      }
      // get next square

    }
  }

  private void checkNotNull() {
    assert SQUARE_9 != null
        : "fx:id=\"SQUARE_9\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_8 != null
        : "fx:id=\"SQUARE_8\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_7 != null
        : "fx:id=\"SQUARE_7\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_6 != null
        : "fx:id=\"SQUARE_6\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_5 != null
        : "fx:id=\"SQUARE_5\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_4 != null
        : "fx:id=\"SQUARE_4\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_3 != null
        : "fx:id=\"SQUARE_3\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_2 != null
        : "fx:id=\"SQUARE_2\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_1 != null
        : "fx:id=\"SQUARE_1\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_0 != null
        : "fx:id=\"SQUARE_0\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert TOKEN_PLAYER_1 != null
        : "fx:id=\"TOKEN_PLAYER_1\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_10 != null
        : "fx:id=\"SQUARE_10\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_30 != null
        : "fx:id=\"SQUARE_30\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_20 != null
        : "fx:id=\"SQUARE_20\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_19 != null
        : "fx:id=\"SQUARE_19\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_18 != null
        : "fx:id=\"SQUARE_18\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_17 != null
        : "fx:id=\"SQUARE_17\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_16 != null
        : "fx:id=\"SQUARE_16\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_15 != null
        : "fx:id=\"SQUARE_15\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_14 != null
        : "fx:id=\"SQUARE_14\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_13 != null
        : "fx:id=\"SQUARE_13\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_12 != null
        : "fx:id=\"SQUARE_12\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_11 != null
        : "fx:id=\"SQUARE_11\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_29 != null
        : "fx:id=\"SQUARE_29\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_28 != null
        : "fx:id=\"SQUARE_28\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_27 != null
        : "fx:id=\"SQUARE_27\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_26 != null
        : "fx:id=\"SQUARE_26\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_25 != null
        : "fx:id=\"SQUARE_25\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_24 != null
        : "fx:id=\"SQUARE_24\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_23 != null
        : "fx:id=\"SQUARE_23\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_22 != null
        : "fx:id=\"SQUARE_22\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_21 != null
        : "fx:id=\"SQUARE_21\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_39 != null
        : "fx:id=\"SQUARE_39\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_38 != null
        : "fx:id=\"SQUARE_38\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_37 != null
        : "fx:id=\"SQUARE_37\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_36 != null
        : "fx:id=\"SQUARE_36\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_35 != null
        : "fx:id=\"SQUARE_35\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_34 != null
        : "fx:id=\"SQUARE_34\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_33 != null
        : "fx:id=\"SQUARE_33\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_32 != null
        : "fx:id=\"SQUARE_32\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert SQUARE_31 != null
        : "fx:id=\"SQUARE_31\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert DICE != null
        : "fx:id=\"DICE\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert DICE_1 != null
        : "fx:id=\"DICE_1\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert DICE_2 != null
        : "fx:id=\"DICE_2\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert PLAYER_1 != null
        : "fx:id=\"PLAYER_1\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert PLAYER_3 != null
        : "fx:id=\"PLAYER_3\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert PLAYER_5 != null
        : "fx:id=\"PLAYER_5\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert BUTTON_CONTAINER != null
        : "fx:id=\"BUTTON_CONTAINER\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert NEW_GAME != null
        : "fx:id=\"NEW_GAME\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert END_GAME != null
        : "fx:id=\"END_GAME\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert PLAYER_2 != null
        : "fx:id=\"PLAYER_2\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert PLAYER_4 != null
        : "fx:id=\"PLAYER_4\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert PLAYER_6 != null
        : "fx:id=\"PLAYER_6\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert BUTTON_CONTAINER1 != null
        : "fx:id=\"BUTTON_CONTAINER1\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
    assert MESSAGE_AREA != null
        : "fx:id=\"MESSAGE_AREA\" was not injected: check your FXML file 'propertyTycoonGui_v_0_1.fxml'.";
  }
}
