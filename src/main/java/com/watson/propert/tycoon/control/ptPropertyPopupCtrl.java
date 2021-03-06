package com.watson.propert.tycoon.control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import com.watson.propert.tycoon.game.PropertyInfo;
import com.watson.propert.tycoon.gui.GuiGameBoard;
import com.watson.propert.tycoon.gui.GuiSquare;

/**
 * Class for controlling a single property popup window.
 *
 * @author Tom Doran
 */
public class ptPropertyPopupCtrl {

  @FXML private ResourceBundle resources;

  @FXML private URL location;

  @FXML private HBox PROPERTY_POPUP;

  @FXML private VBox PROPERTY_CARD;

  @FXML private HBox PROPERTY_POPUP_GROUP;

  @FXML private Label PROPERTY_NAME;

  @FXML private Label PROPERTY_PRICE;

  @FXML private ImageView OWNER_IMG;

  @FXML private Label PROPERTY_OWNER;

  @FXML private Label RENT_HOTEL;

  @FXML private Label RENT_BASIC;

  @FXML private Label RENT_1H;

  @FXML private Label RENT_2H;

  @FXML private Label RENT_3H;

  @FXML private Label RENT_4H;

  void setData(PropertyInfo info, GuiSquare guiSquare, GuiGameBoard gameBoard) {
    // fill in relevant data
    PROPERTY_POPUP_GROUP.getStyleClass().add(guiSquare.getGroup().getCssClass());
    PROPERTY_NAME.setText(info.getName());
    RENT_BASIC.setText(String.valueOf(info.getRent()));
    PROPERTY_PRICE.setText(String.valueOf(info.price()));
    if (info.getOwner() != null) {
      PROPERTY_OWNER.setText(gameBoard.getPlayers()[info.getOwner().ordinal()].getName());
    } else if (info.isMorged()) {
      PROPERTY_OWNER.setText("Mortgaged");
    } else {
      PROPERTY_OWNER.setText("For sale");
    }
    info.rentsPerHouse()
        .ifPresentOrElse(
            (rents) -> {
              RENT_1H.setText(String.valueOf(rents.get(1)));
              RENT_2H.setText(String.valueOf(rents.get(2)));
              RENT_3H.setText(String.valueOf(rents.get(3)));
              RENT_4H.setText(String.valueOf(rents.get(4)));
              RENT_HOTEL.setText(String.valueOf(rents.get(5)));
            },
            () -> { // for utility/station
              RENT_1H.setText("N/A");
              RENT_2H.setText("N/A");
              RENT_3H.setText("N/A");
              RENT_4H.setText("N/A");
              RENT_HOTEL.setText("N/A");
            });
  }

  @FXML
  void initialize() {
    assert PROPERTY_POPUP != null
        : "fx:id=\"PROPERTY_POPUP\" was not injected: check your FXML file 'ptPropertyPopup.fxml'.";
    assert PROPERTY_CARD != null
        : "fx:id=\"PROPERTY_CARD\" was not injected: check your FXML file 'ptPropertyPopup.fxml'.";
    assert PROPERTY_POPUP_GROUP != null
        : "fx:id=\"PROPERTY_POPUP_GROUP\" was not injected: check your FXML file 'ptPropertyPopup.fxml'.";
    assert PROPERTY_NAME != null
        : "fx:id=\"PROPERTY_NAME\" was not injected: check your FXML file 'ptPropertyPopup.fxml'.";
    assert PROPERTY_PRICE != null
        : "fx:id=\"PROPERTY_PRICE\" was not injected: check your FXML file 'ptPropertyPopup.fxml'.";
    assert OWNER_IMG != null
        : "fx:id=\"OWNER_IMG\" was not injected: check your FXML file 'ptPropertyPopup.fxml'.";
    assert PROPERTY_OWNER != null
        : "fx:id=\"PROPERTY_OWNER\" was not injected: check your FXML file 'ptPropertyPopup.fxml'.";
    assert RENT_HOTEL != null
        : "fx:id=\"RENT_HOTEL\" was not injected: check your FXML file 'ptPropertyPopup.fxml'.";
    assert RENT_BASIC != null
        : "fx:id=\"RENT_BASIC\" was not injected: check your FXML file 'ptPropertyPopup.fxml'.";
    assert RENT_1H != null
        : "fx:id=\"RENT_1H\" was not injected: check your FXML file 'ptPropertyPopup.fxml'.";
    assert RENT_2H != null
        : "fx:id=\"RENT_2H\" was not injected: check your FXML file 'ptPropertyPopup.fxml'.";
    assert RENT_3H != null
        : "fx:id=\"RENT_3H\" was not injected: check your FXML file 'ptPropertyPopup.fxml'.";
    assert RENT_4H != null
        : "fx:id=\"RENT_4H\" was not injected: check your FXML file 'ptPropertyPopup.fxml'.";
  }
}
