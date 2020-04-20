package com.watson.propert.tycoon.control;

import java.util.LinkedList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import com.watson.propert.tycoon.gui.GuiPlayer;
import com.watson.propert.tycoon.gui.GuiProperty;

/**
 * Controller class for player in debt popup.
 *
 * @author Tom Doran
 */
public class ptPlayerInDebtPopupCtrl {

  @FXML private ImageView IMG_PLAYER_TOKEN;

  @FXML private Label PLAYER_NAME;

  @FXML private Label PLAYER_NAME111;

  @FXML private ScrollPane SCROLL_PANE;

  @FXML private HBox SCROLLER;

  @FXML private Label debt_label;

  @FXML private Button done_button;

  private List<GuiProperty> propertiesToSell = new LinkedList<>();
  private int cashBack = 0;
  private Stage thisWindow = new Stage();

  // Add a GuiProperty to the scroll container
  public void addProperty(GuiProperty property) {
    CheckBox sell = new CheckBox("Sell");
    sell.allowIndeterminateProperty();
    sell.selectedProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              if (newValue) {
                // checkbox is ticked
                propertiesToSell.add(property);
                subtractDebtLabel(property);
              } else if (oldValue) {
                // checkbox unticked
                propertiesToSell.remove(property);
                addToDebtLabel(property);
              }
            });
    VBox propertyPane = (VBox) property.getPane(140);
    propertyPane.getChildren().add(sell);
    propertyPane.setAlignment(Pos.CENTER);
    SCROLLER.getChildren().add(propertyPane);
  }

  private void addToDebtLabel(GuiProperty property) {
    int sum = Integer.parseInt(debt_label.getText());
    sum += Integer.parseInt(property.getPrice());
    // houses
    debt_label.setText(String.valueOf(sum));
  }

  private void subtractDebtLabel(GuiProperty property) {
    int sum = Integer.parseInt(debt_label.getText());
    sum -= Integer.parseInt(property.getPrice());
    // houses
    debt_label.setText(String.valueOf(sum));
  }

  // Remove all properties from scroll container
  public void clearProperties() {
    SCROLLER.getChildren().clear();
  }

  public void setData(GuiPlayer player, int amount, Stage stage) {
    PLAYER_NAME.setText(player.getName());
    debt_label.setText(String.valueOf(amount));
    thisWindow = stage;
    // Add properties from Player's portfolio
    clearProperties();
    for (GuiProperty p : player.getPortfolio()) {
      addProperty(p);
    }
    IMG_PLAYER_TOKEN.setImage(player.getToken().getImage());
  }

  @FXML
  void setDone_button(ActionEvent event) {
    if (Integer.parseInt(debt_label.getText()) <= 0) {
      cashBack = Integer.parseInt(debt_label.getText());
      thisWindow.close();
    } else {
      // do not close window
    }
  }

  public List<GuiProperty> getPropertiesToSell() {
    return propertiesToSell;
  }

  public int getCashBack() {
    return cashBack;
  }
}
