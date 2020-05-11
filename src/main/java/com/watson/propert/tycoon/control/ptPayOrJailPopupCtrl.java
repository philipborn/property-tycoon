package com.watson.propert.tycoon.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import com.watson.propert.tycoon.gui.GuiPlayer;

public class ptPayOrJailPopupCtrl {

  @FXML private ImageView IMG_PLAYER_TOKEN;

  @FXML private Label PLAYER_NAME;

  @FXML private Label AMOUNT;

  private Stage thisWindow;

  private boolean pay;

  @FXML
  void jail(ActionEvent event) {
    pay = false;
    thisWindow.close();
  }

  @FXML
  void pay(ActionEvent event) {
    pay = true;
    thisWindow.close();
  }

  public void setData(GuiPlayer player, int amount, Stage stage) {
    PLAYER_NAME.setText(player.getName());
    AMOUNT.setText(String.valueOf(amount));
    thisWindow = stage;
    IMG_PLAYER_TOKEN.setImage(player.getToken().getImage());
  }

  boolean isPaying() {
    return pay;
  }
}
