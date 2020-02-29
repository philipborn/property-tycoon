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

import static java.lang.System.*;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.stage.Screen;
import javafx.util.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.Subscribe;
import com.watson.propert.tycoon.game.DiceEvent;
import com.watson.propert.tycoon.game.Game;
import com.watson.propert.tycoon.game.PropertTycoon;
import com.watson.propert.tycoon.gui.App;
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

  @FXML private HBox DICE;

  @FXML private HBox STREET_1;

  @FXML private HBox STREET_2;

  @FXML private HBox STREET_3;

  @FXML private HBox STREET_4;

  @FXML private Label DICE_1;

  @FXML private Label DICE_2;

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

  @FXML private Circle PATH_0;
  @FXML private Circle PATH_1;
  @FXML private Circle PATH_2;
  @FXML private Circle PATH_3;
  @FXML private Circle PATH_4;
  @FXML private Circle PATH_5;
  @FXML private Circle PATH_6;
  @FXML private Circle PATH_7;
  @FXML private Circle PATH_8;
  @FXML private Circle PATH_9;
  @FXML private CubicCurve PATH_10;
  @FXML private Circle PATH_11;
  @FXML private Circle PATH_12;
  @FXML private Circle PATH_13;
  @FXML private Circle PATH_14;
  @FXML private Circle PATH_15;
  @FXML private Circle PATH_16;
  @FXML private Circle PATH_17;
  @FXML private Circle PATH_18;
  @FXML private Circle PATH_19;
  @FXML private Circle PATH_20;
  @FXML private Circle PATH_21;
  @FXML private Circle PATH_22;
  @FXML private Circle PATH_23;
  @FXML private Circle PATH_24;
  @FXML private Circle PATH_25;
  @FXML private Circle PATH_26;
  @FXML private Circle PATH_27;
  @FXML private Circle PATH_28;
  @FXML private Circle PATH_29;
  @FXML private Circle PATH_30;
  @FXML private Circle PATH_31;
  @FXML private Circle PATH_32;
  @FXML private Circle PATH_33;
  @FXML private Circle PATH_34;
  @FXML private Circle PATH_35;
  @FXML private Circle PATH_36;
  @FXML private Circle PATH_37;
  @FXML private Circle PATH_38;
  @FXML private Circle PATH_39;

  @FXML private Circle PATH_JAIL;

  @FXML private StackPane[] squares;

  private HashMap<StackPane, Double[]> squareCentres;

  private Shape[] path;

  private int PLAYER_1_position = 0;

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
    goToJail();
  }

  @FXML
  void throwDice(MouseEvent event) {
    game.throwDicesAndMove();
  }

  @Subscribe
  void diceHandler(DiceEvent event) {
    int i = event.firstDice() + event.secondDice();
    MESSAGE_AREA.setText("Move: " + i + " spaces");
    DICE_1.setText(Integer.toString(event.firstDice()));
    DICE_2.setText(Integer.toString(event.secondDice()));
    move(i);
  }

  void goToJail() {

    // head directly to jail from wherever the player is on the board

    PathTransition pt = new PathTransition();
    Path p =
        new Path(
            new MoveTo(TOKEN_PLAYER_1.getTranslateX() + 25, TOKEN_PLAYER_1.getTranslateY() + 25));
    p.getElements().add(new LineTo(PATH_JAIL.getCenterX(), PATH_JAIL.getCenterY()));
    pt.setNode(TOKEN_PLAYER_1);
    pt.setDuration(Duration.seconds(1));
    pt.setPath(p);
    pt.play();

    // set player position to just visiting square for when they leave jail
    PLAYER_1_position = 10;
  }

  void move(int spaces) { // FIX BACKWARDS BUG

    PathTransition pt = new PathTransition();
    Path p =
        new Path(
            new MoveTo(TOKEN_PLAYER_1.getTranslateX() + 25, TOKEN_PLAYER_1.getTranslateY() + 25));

    int destNum = (PLAYER_1_position + spaces) % path.length;

    // while player has not reached the destination
    while (PLAYER_1_position != destNum) {
      // if next path is not the jail path
      if (path[PLAYER_1_position] instanceof Circle) {
        Circle pathBlock = (Circle) path[PLAYER_1_position];
        p.getElements().add(new LineTo(pathBlock.getCenterX(), pathBlock.getCenterY()));
      } else {
        // otherwise we are on the jail square, but just visiting
        CubicCurve jailPath = (CubicCurve) path[PLAYER_1_position];
        p.getElements()
            .add(
                new CubicCurveTo(
                    jailPath.getControlX1(),
                    jailPath.getControlY1(),
                    jailPath.getControlX2(),
                    jailPath.getControlY2(),
                    jailPath.getEndX(),
                    jailPath.getEndY()));
      }
      // iterate over the board in desired direction
      PLAYER_1_position = (PLAYER_1_position + 1) % path.length;
    }

    // add final part of path (to destination)
    if (path[destNum] instanceof Circle) {
      Circle pathBlock = (Circle) path[destNum];
      p.getElements().add(new LineTo(pathBlock.getCenterX(), pathBlock.getCenterY()));
    } else {
      CubicCurve jailPath = (CubicCurve) path[destNum];
      p.getElements()
          .add(
              new CubicCurveTo(
                  jailPath.getControlX1(),
                  jailPath.getControlY1(),
                  jailPath.getControlX2(),
                  jailPath.getControlY2(),
                  jailPath.getEndX(),
                  jailPath.getEndY()));
    }

    pt.setNode(TOKEN_PLAYER_1);
    pt.setDuration(Duration.seconds(1));
    pt.setPath(p);
    pt.play();
    // show on flow pane
    /*
    FlowPane fp = (FlowPane) squares[PLAYER_1_position].getParent();
    fp.getChildren().add(TOKEN_PLAYER_1);
     */
    // execute square functionality
  }

  // Move coordinates by distance and in direction given
  GuiCoords moveCoords(GuiCoords xy, Double distance, Direction direction) {
    switch (direction) {
      case DOWN:
        return new GuiCoords(xy.getX(), xy.getY() + distance);
      case LEFT:
        return new GuiCoords(xy.getX() - distance, xy.getY());
      case UP:
        return new GuiCoords(xy.getX(), xy.getY() - distance);
      case RIGHT:
        return new GuiCoords(xy.getX() + distance, xy.getY());
    }
    return xy;
  }

  // Calculate the centre point of each square relative to gameboard Pane
  void calculateSquareCentres(GuiCoords xy, Double tileHeight, Double tileWidth) {
    Logger logger = LoggerFactory.getLogger(App.class);
    Direction dir = Direction.DOWN;

    for (StackPane sq : this.squares) {
      if (sq.getChildren().get(0) instanceof VBox) {
        //GuiCoords newPos = new GuiCoords(xy.getX(), xy.getY());

        GAME_BOARD_CONTAINER.getChildren().add(new Circle(xy.getX(), xy.getY(), 4));

        logger.debug(
            sq.getId().toString() + " : " + xy.getX().toString() + "," + xy.getY().toString());
        xy = moveCoords(xy, tileWidth, dir);
      } else {
        //GuiCoords newPos = new GuiCoords(xy.getX(), xy.getY());
        //GuiCoords newPos = moveCoords(xy, tileHeight, dir);
        xy = moveCoords(xy, tileHeight, dir);
        logger.debug(
            sq.getId().toString() + " : " + xy.getX().toString() + "," + xy.getY().toString());
        GAME_BOARD_CONTAINER.getChildren().add(new Circle(xy.getX(), xy.getY(), 4));
        dir = dir.turnRight();
        xy = moveCoords(xy, tileHeight, dir);
      }
    }
  }

  // Rescales the game board for different screen resolutions/DPI combinations
  void rescaleGameBoard(Double scaleFactor) {
    Double default_border = 2.0; //4.0
    Double default_board_size = 960.0 * scaleFactor; //800.0
    Double default_street_height = 130.0 * scaleFactor; //103.0
    Double default_street_width = default_board_size - (2.0 * default_street_height);
    Double default_tile_width = default_street_width / 9.0;
    Double default_tile_height = default_street_height;
    Double default_corner_inner_size = default_street_height - default_border;
    Double default_tile_inner_height = default_street_height - default_border;
    Double default_tile_inner_width = default_tile_width - 2;
    Double default_house_block_height = 0.22 * default_street_height * scaleFactor;
    Double default_property_name_height = 0.35 * default_street_height * scaleFactor;
    Double default_price_height = 0.22 * default_street_height * scaleFactor;

    // Resize game board containers
    GAME_BOARD_CONTAINER.setPrefSize(default_board_size, default_board_size);
    GAME_GRID.getColumnConstraints().get(0).setPrefWidth(default_street_height);
    GAME_GRID.getColumnConstraints().get(1).setPrefWidth(default_street_width);
    GAME_GRID.getColumnConstraints().get(2).setPrefWidth(default_street_height);
    GAME_GRID.getRowConstraints().get(0).setPrefHeight(default_street_height);
    GAME_GRID.getRowConstraints().get(1).setPrefHeight(default_street_width);
    GAME_GRID.getRowConstraints().get(2).setPrefHeight(default_street_height);

    // Resize Street of properties
    STREET_1.setPrefSize(default_street_width, default_street_height);
    STREET_2.setPrefSize(default_street_width, default_street_height);
    STREET_3.setPrefSize(default_street_width, default_street_height);
    STREET_4.setPrefSize(default_street_width, default_street_height);
    JAIL.setHeight(default_corner_inner_size * 0.7);
    JAIL.setWidth(default_corner_inner_size * 0.7);

    // Resize squares
    for (StackPane sq : this.squares) {
      if (sq.getChildren().get(0) instanceof VBox) {
        sq.setPrefHeight(default_tile_height);
        sq.setPrefWidth(default_tile_width);
        ((Pane) sq.lookup("#PANEL"))
            .setPrefSize(default_tile_inner_width, default_tile_inner_height);
        ((Pane) sq.lookup("#PROPERTY_GROUP"))
            .setPrefSize(default_tile_inner_width, default_house_block_height);
        ((Label) sq.lookup("#PROPERTY_NAME"))
            .setPrefSize(default_tile_inner_width, default_property_name_height);
        ((Label) sq.lookup("#PROPERTY_PRICE"))
            .setPrefSize(default_tile_inner_width, default_price_height);
      } else {
        sq.setPrefSize(default_street_height, default_street_height);
        ((HBox) sq.getChildren().get(0))
            .setPrefSize(default_corner_inner_size, default_corner_inner_size);
      }
    }
    calculateSquareCentres(
        new GuiCoords(default_board_size, default_board_size - default_tile_height),
        default_tile_height,
        default_tile_width);
  }

  @FXML
  void initialize() {
    checkNotNull();
    this.squares =
        new StackPane[] {
          SQUARE_0, SQUARE_1, SQUARE_2, SQUARE_3, SQUARE_4, SQUARE_5, SQUARE_6, SQUARE_7, SQUARE_8,
          SQUARE_9, SQUARE_10, SQUARE_11, SQUARE_12, SQUARE_13, SQUARE_14, SQUARE_15, SQUARE_16,
          SQUARE_17, SQUARE_18, SQUARE_19, SQUARE_20, SQUARE_21, SQUARE_22, SQUARE_23, SQUARE_24,
          SQUARE_25, SQUARE_26, SQUARE_27, SQUARE_28, SQUARE_29, SQUARE_30, SQUARE_31, SQUARE_32,
          SQUARE_33, SQUARE_34, SQUARE_35, SQUARE_36, SQUARE_37, SQUARE_38, SQUARE_39
        };

    path =
        new Shape[] {
          PATH_0, PATH_1, PATH_2, PATH_3, PATH_4, PATH_5, PATH_6, PATH_7, PATH_8, PATH_9, PATH_10,
          PATH_11, PATH_12, PATH_13, PATH_14, PATH_15, PATH_16, PATH_17, PATH_18, PATH_19, PATH_20,
          PATH_21, PATH_22, PATH_23, PATH_24, PATH_25, PATH_26, PATH_27, PATH_28, PATH_29, PATH_30,
          PATH_31, PATH_32, PATH_33, PATH_34, PATH_35, PATH_36, PATH_37, PATH_38, PATH_39
        };

    // Scale game board based on screen DPI
    rescaleGameBoard(1 / Screen.getPrimary().getOutputScaleX());

    // read JSON file
    BoardReaderJson boardReader = new BoardReaderJson();
    boardReader.readFile("src/main/resources/boardDataJSON.json");

    game = Game.newGame();
    game.registerListener(this);

    // for every square on the board
    for (StackPane sq : squares) {
      boardReader.nextObject();

      // if square is a non-corner square
      if (sq.getChildren().get(0) instanceof VBox) {

        // access elements of square
        VBox v = (VBox) sq.getChildren().get(0);
        HBox group = (HBox) v.getChildren().get(0);
        Label name = (Label) v.getChildren().get(1);
        Label price = (Label) v.getChildren().get(2);

        // set group names (if square is in a group)
        if (boardReader.getProperties().get("group") != null) {
          group.setId("PROPERTY_GROUP_" + boardReader.getProperties().get("group").toUpperCase());
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
