package com.watson.propert.tycoon.control;

import java.util.LinkedList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
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
public class ptRaiseFundsPopupCtrl {

  @FXML private ImageView IMG_PLAYER_TOKEN;

  @FXML private HBox SCROLLER;

  @FXML private Label funds_label;

  private List<SellProperty> propertiesToSell = new LinkedList<>();
  private int cashBack = 0;
  private Stage thisWindow = new Stage();

  // Add a GuiProperty to the scroll container
  public void addProperty(GuiProperty property) {

    SellProperty record = new SellProperty(property);
    propertiesToSell.add(record);
    LinkedList<CheckBox> boxes = new LinkedList<>();

    CheckBox sellProperty = new CheckBox("Sell");
    boxes.add(sellProperty);
    sellProperty.allowIndeterminateProperty();
    sellProperty
        .selectedProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              if (newValue) {
                // checkbox is ticked
                record.setSellingSquare(true);
                addToFundLabel(Integer.parseInt(property.getPrice()));
              } else if (oldValue) {
                // checkbox unticked
                record.setSellingSquare(false);
                subtractFundLabel(Integer.parseInt(property.getPrice()));
              }
            });
    VBox propertyPane = (VBox) property.getPane(140);

    // handle house check boxes
    HBox houses = (HBox) propertyPane.getChildren().get(0);

    for (int i = 0; i < houses.getChildren().size(); i++) {
      CheckBox sellHouse = new CheckBox();
      boxes.add(sellHouse);
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
                  addToFundLabel(property.getGroup().getHousePrice());
                } else if (oldValue) {
                  // checkbox unticked
                  record.setHousesToRemove(record.getHousesToRemove() - 1);
                  subtractFundLabel(property.getGroup().getHousePrice());
                }
              });
      ((StackPane) houses.getChildren().get(i)).getChildren().add(sellHouse);
    }

    // mortgage property check box
    CheckBox mortgageProperty = new CheckBox("Mortgage");
    mortgageProperty.allowIndeterminateProperty();
    mortgageProperty
        .selectedProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              if (newValue) {
                // checkbox is ticked
                record.setMortgageProperty(true);
                property.mortgage();
                addToFundLabel(Integer.parseInt(property.getPrice()) / 2);
                for (CheckBox cb : boxes) {
                  cb.setSelected(false);
                  cb.setDisable(true);
                }
              } else if (oldValue) {
                // checkbox unticked
                record.setMortgageProperty(false);
                property.unmortgage();
                subtractFundLabel(Integer.parseInt(property.getPrice()) / 2);
                for (CheckBox cb : boxes) {
                  cb.setDisable(false);
                }
              }
            });
    // account for properties that are already mortgaged
    if (property.isMortgaged()) {
      // select mortgaged checkbox & subtract amount added to debt label
      mortgageProperty.setSelected(true);
      subtractFundLabel(Integer.parseInt(property.getPrice()) / 2);
    }
    propertyPane.getChildren().addAll(sellProperty, mortgageProperty);
    propertyPane.setAlignment(Pos.CENTER);
    SCROLLER.getChildren().add(propertyPane);
  }

  private void addToFundLabel(int amount) {
    int sum = Integer.parseInt(funds_label.getText());
    funds_label.setText(String.valueOf(sum += amount));
  }

  private void subtractFundLabel(int amount) {
    int sum = Integer.parseInt(funds_label.getText());
    funds_label.setText(String.valueOf(sum -= amount));
  }

  // Remove all properties from scroll container
  public void clearProperties() {
    SCROLLER.getChildren().clear();
  }

  public void setData(GuiPlayer player, Stage stage) {
    funds_label.setText(String.valueOf(0));
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
    if (isLegalState()) {
      cashBack = Integer.parseInt(funds_label.getText());
      thisWindow.fireEvent(new WindowEvent(thisWindow, WindowEvent.WINDOW_CLOSE_REQUEST));
      thisWindow.close();
    } else {
      // do not close window & display warning window
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Sell Property Warning");
      alert.setHeaderText("Can't Sell Property");
      alert.setContentText("Have to sell every house/hotel on a property to sell the property!");
      alert.show();
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
