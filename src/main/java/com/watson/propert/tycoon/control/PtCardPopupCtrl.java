package com.watson.propert.tycoon.control;
/**
 * Card pop-up dialog box controller
 *
 * @author Lee Richards
 * @version Sprint4
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import com.watson.propert.tycoon.gui.Card;

public class PtCardPopupCtrl {

  @FXML private ResourceBundle resources;

  @FXML private URL location;

  @FXML private Label CARD_TITLE;

  @FXML private GridPane CARD_GRID;

  @FXML private Label CARD_TEXT;

  @FXML private ImageView CARD_IMG;

  @FXML private StackPane CARD_BG;

  public void setData(String title, String instructions, Image image, Card card) {
    CARD_TITLE.setText(title);
    CARD_TEXT.setText(instructions);
    CARD_IMG.setImage(image);
    CARD_BG.getStyleClass().clear();
    CARD_BG.getStyleClass().add(card.getCssClass());
  }

  public void setData(String instructions, Card card) {
    CARD_TEXT.setText(instructions);
    if (card.equals(Card.POT_LUCK)) {
      CARD_TITLE.setText("POT LUCK");
      CARD_IMG.setImage(
          new Image(ClassLoader.getSystemResource("board/potLuckCard.png").toExternalForm()));
    } else {
      CARD_TITLE.setText("OPPORTUNITY KNOCKS");
      CARD_IMG.setImage(
          new Image(ClassLoader.getSystemResource("board/opKnocksCard.png").toExternalForm()));
    }
  }

  @FXML
  void initialize() {
    assert CARD_TITLE != null
        : "fx:id=\"CARD_TITLE\" was not injected: check your FXML file 'ptCardPopup.fxml'.";
    assert CARD_GRID != null
        : "fx:id=\"CARD_GRID\" was not injected: check your FXML file 'ptCardPopup.fxml'.";
    assert CARD_TEXT != null
        : "fx:id=\"CARD_TEXT\" was not injected: check your FXML file 'ptCardPopup.fxml'.";
    assert CARD_IMG != null
        : "fx:id=\"CARD_IMG\" was not injected: check your FXML file 'ptCardPopup.fxml'.";
  }
}
