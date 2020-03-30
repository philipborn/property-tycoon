package com.watson.propert.tycoon.control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
