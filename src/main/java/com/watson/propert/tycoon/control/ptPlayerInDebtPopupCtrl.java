package com.watson.propert.tycoon.control;

import java.util.LinkedList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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

  @FXML private HBox SCROLLER;

  @FXML private Label debt_label;

  private List<SellProperty> propertiesToSell = new LinkedList<>();
  private int cashBack = 0;
  private Stage thisWindow = new Stage();

  // Add a GuiProperty to the scroll container
  public void addProperty(GuiProperty property) {

    SellProperty record = new SellProperty(property);
    propertiesToSell.add(record);

    CheckBox sellProperty = new CheckBox("Sell");
    sellProperty.allowIndeterminateProperty();
    sellProperty
        .selectedProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              if (newValue) {
                // checkbox is ticked
                record.setSellingSquare(true);
                subtractDebtLabel(Integer.parseInt(property.getPrice()));
              } else if (oldValue) {
                // checkbox unticked
                record.setSellingSquare(false);
                addToDebtLabel(Integer.parseInt(property.getPrice()));
              }
            });
    VBox propertyPane = (VBox) property.getPane(140);

    // handle house check boxes
    HBox houses = (HBox) propertyPane.getChildren().get(0);

    for (int i = 0; i < houses.getChildren().size(); i++) {
      CheckBox sellHouse = new CheckBox();
      sellHouse.allowIndeterminateProperty();
      sellHouse.setScaleX(0.7);
      sellHouse.setScaleY(0.7);
      sellHouse
          .selectedProperty()
          .addListener(
              (observable, oldValue, newValue) -> {
                if (newValue) {
                  // checkbox is ticked
                  record.setHousesToRemove(record.getHousesToRemove() + 1);
                  subtractDebtLabel(property.getGroup().getHousePrice());
                } else if (oldValue) {
                  // checkbox unticked
                  record.setHousesToRemove(record.getHousesToRemove() - 1);
                  ;
                  addToDebtLabel(property.getGroup().getHousePrice());
                }
              });
      ((StackPane) houses.getChildren().get(i)).getChildren().add(sellHouse);
    }
    propertyPane.getChildren().add(sellProperty);
    propertyPane.setAlignment(Pos.CENTER);
    SCROLLER.getChildren().add(propertyPane);
  }

  private void addToDebtLabel(int amount) {
    int sum = Integer.parseInt(debt_label.getText());
    debt_label.setText(String.valueOf(sum += amount));
  }

  private void subtractDebtLabel(int amount) {
    int sum = Integer.parseInt(debt_label.getText());
    debt_label.setText(String.valueOf(sum -= amount));
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
    //removeRedundantInfo();
    if (Integer.parseInt(debt_label.getText()) <= 0 && isLegalState()) {
      cashBack = Integer.parseInt(debt_label.getText());
      thisWindow.fireEvent(new WindowEvent(thisWindow, WindowEvent.WINDOW_CLOSE_REQUEST));
      thisWindow.close();
    } else {
      // do not close window (add dialogues?)
    }
  }

  private void removeRedundantInfo() {
    for (SellProperty sp : propertiesToSell) {
      if (sp.sellingSquare() == false && sp.getHousesToRemove() == 0) {
        propertiesToSell.remove(sp);
      }
    }
  }

  private boolean isLegalState() {
    boolean legal = true;
    for (SellProperty sp : propertiesToSell) {
      // if trying to sell property without selling all houses
      if (sp.sellingSquare() && (sp.getProperty().getNumHouses() != sp.getHousesToRemove())) {
        legal = false;
      }
    }
    return legal;
  }

  public List<SellProperty> getPropertiesToSell() {
    return propertiesToSell;
  }

  public int getCashBack() {
    return cashBack;
  }
}
