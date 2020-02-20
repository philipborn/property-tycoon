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

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import com.watson.propert.tycoon.io.BoardReaderJson;

public class PtController {

  @FXML private ResourceBundle resources;

  @FXML private URL location;

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

  @FXML
  void endGame(ActionEvent event) {
    /*
      save game functionality
    */

    Platform.exit();
  }

  @FXML
  void newGame(ActionEvent event) {}

  @FXML
  void throwDice(MouseEvent event) {}

  @FXML
  void initialize() {

    // read JSON file
    BoardReaderJson boardReader = new BoardReaderJson("src/main/resources/boardDataJSON.json");

    StackPane[] firstSquares = {
      SQUARE_0, SQUARE_1, SQUARE_2, SQUARE_3, SQUARE_4, SQUARE_5, SQUARE_6, SQUARE_7, SQUARE_8,
      SQUARE_9, SQUARE_10, SQUARE_11, SQUARE_12, SQUARE_13, SQUARE_14, SQUARE_15, SQUARE_16,
      SQUARE_17, SQUARE_18, SQUARE_19, SQUARE_20, SQUARE_21, SQUARE_22, SQUARE_23, SQUARE_24,
      SQUARE_25, SQUARE_26, SQUARE_27, SQUARE_28, SQUARE_29, SQUARE_30, SQUARE_31, SQUARE_32,
      SQUARE_33, SQUARE_34, SQUARE_35, SQUARE_36, SQUARE_37, SQUARE_38, SQUARE_39
    };

    // for every square on the board
    for (StackPane sq : firstSquares) {

      // if square is a non-corner square
      if (sq.getChildren().get(0) instanceof VBox) {

        // access elements of square
        VBox v = (VBox) sq.getChildren().get(0);
        HBox group = (HBox) v.getChildren().get(0);
        Label name = (Label) v.getChildren().get(1);
        Label price = (Label) v.getChildren().get(2);

        // set group names (if square is in a group)
        if(boardReader.getObjectData().get("Group") != null) {
          group.setId("PROPERTY_GROUP_"+boardReader.getObjectData().get("Group").toUpperCase());
        }

        // set name/price values
        name.setText(boardReader.getObjectData().get("Name"));
        price.setText(boardReader.getObjectData().get("Cost"));
      }
      // get next square
      boardReader.iterate();
    }
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
