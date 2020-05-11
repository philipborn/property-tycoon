package com.watson.propert.tycoon.control;
/**
 * Player Extended Details Dialog Box controller
 *
 * @author Lee Richards
 * @version Sprint4
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import com.watson.propert.tycoon.gui.GuiPlayer;
import com.watson.propert.tycoon.gui.GuiProperty;

public class ptPlayerPopupCtrl {

  @FXML private ResourceBundle resources;

  @FXML private URL location;

  @FXML private ImageView IMG_PLAYER_TOKEN;

  @FXML private Label PLAYER_NAME;

  @FXML private Label OPTION_1;

  @FXML private Label OPTION_2;

  @FXML private Label OPTION_3;

  @FXML private ScrollPane SCROLL_PANE;

  @FXML private HBox SCROLLER;

  @FXML private VBox PROPERTY_CARD_1;

  @FXML private HBox PROPERTY_POPUP_GROUP;

  @FXML private Label PROPERTY_NAME;

  @FXML private Label PROPERTY_PRICE;

  public void setPlayerName(String name) {
    PLAYER_NAME.setText(name);
  }

  public void setAccountBalance(String bal) {
    OPTION_1.setText(bal);
  }

  public void setNetWorth(String worth) {
    OPTION_2.setText(worth);
  }

  public void setNoProps(String props) {
    OPTION_3.setText(props);
  }

  public void setPlayerToken(ImageView iv) {
    IMG_PLAYER_TOKEN = iv;
  }

  // Add a GuiProperty to the scroll container
  public void addProperty(GuiProperty property) {
    SCROLLER.getChildren().add(property.getPane(140));
  }

  // Remove all properties from scroll container
  public void clearProperties() {
    SCROLLER.getChildren().clear();
  }

  public void setData(GuiPlayer player) {
    PLAYER_NAME.setText(player.getName());
    OPTION_1.setText("" + player.getInfo().getMoney().getText());
    OPTION_2.setText(String.valueOf(player.calculateNetWorth()));
    OPTION_3.setText(String.valueOf(player.getPortfolio().size()));

    // Add properties from Player's portfolio
    clearProperties();
    for (GuiProperty p : player.getPortfolio()) {
      addProperty(p);
    }
    IMG_PLAYER_TOKEN.setImage(player.getToken().getImage());
  }

  @FXML
  void initialize() {
    //clearProperties();
    asserts();
  }

  private void asserts() {
    assert IMG_PLAYER_TOKEN != null
        : "fx:id=\"IMG_PLAYER_TOKEN\" was not injected: check your FXML file 'ptPlayerPopup.fxml'.";
    assert PLAYER_NAME != null
        : "fx:id=\"PLAYER_NAME\" was not injected: check your FXML file 'ptPlayerPopup.fxml'.";
    assert OPTION_1 != null
        : "fx:id=\"OPTION_1\" was not injected: check your FXML file 'ptPlayerPopup.fxml'.";
    assert OPTION_2 != null
        : "fx:id=\"OPTION_2\" was not injected: check your FXML file 'ptPlayerPopup.fxml'.";
    assert OPTION_3 != null
        : "fx:id=\"OPTION_3\" was not injected: check your FXML file 'ptPlayerPopup.fxml'.";
    assert SCROLL_PANE != null
        : "fx:id=\"SCROLL_PANE\" was not injected: check your FXML file 'ptPlayerPopup.fxml'.";
    assert SCROLLER != null
        : "fx:id=\"SCROLLER\" was not injected: check your FXML file 'ptPlayerPopup.fxml'.";
    assert PROPERTY_CARD_1 != null
        : "fx:id=\"PROPERTY_CARD_1\" was not injected: check your FXML file 'ptPlayerPopup.fxml'.";
    assert PROPERTY_POPUP_GROUP != null
        : "fx:id=\"PROPERTY_POPUP_GROUP\" was not injected: check your FXML file 'ptPlayerPopup.fxml'.";
    assert PROPERTY_NAME != null
        : "fx:id=\"PROPERTY_NAME\" was not injected: check your FXML file 'ptPlayerPopup.fxml'.";
    assert PROPERTY_PRICE != null
        : "fx:id=\"PROPERTY_PRICE\" was not injected: check your FXML file 'ptPlayerPopup.fxml'.";
  }
}
