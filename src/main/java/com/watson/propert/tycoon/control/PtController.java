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

import static java.lang.StrictMath.abs;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.Subscribe;
import com.watson.propert.tycoon.game.*;
import com.watson.propert.tycoon.game.entitys.Player;
import com.watson.propert.tycoon.game.events.*;
import com.watson.propert.tycoon.gui.*;
import com.watson.propert.tycoon.gui.PlayerInfo;
import com.watson.propert.tycoon.io.BoardReaderJson;

/**
 * Controller class for the main GUI.
 *
 * @author Tom Doran
 */
public class PtController {

  @FXML private ResourceBundle resources;

  @FXML private URL location;

  @FXML private BorderPane MAIN_WINDOW;

  @FXML private ImageView IMG_GO;

  @FXML private ImageView JAIL_IMG;

  @FXML private ImageView IMG_GOTO_JAIL;

  @FXML private ImageView IMG_FREE_PARKING;

  @FXML private ImageView IMG_TRAIN_STAT_1;

  @FXML private ImageView IMG_TRAIN_STAT_2;

  @FXML private ImageView IMG_TRAIN_STAT_3;

  @FXML private ImageView IMG_TRAIN_STAT_4;

  @FXML private ImageView IMG_POWER_STAT;

  @FXML private ImageView IMG_WATER_CO;

  @FXML private ImageView IMG_SUPER_TAX;

  @FXML private ImageView IMG_INCOME_TAX;

  @FXML private ImageView IMG_POT_LUCK_1;

  @FXML private ImageView IMG_POT_LUCK_2;

  @FXML private ImageView IMG_POT_LUCK_3;

  @FXML private ImageView IMG_OP_KNOCK_1;

  @FXML private ImageView IMG_OP_KNOCK_2;

  @FXML private ImageView IMG_OP_KNOCK_3;

  @FXML private HBox JAIL_CORNER;

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
  @FXML private HBox TOKEN_PLAYER_2;
  @FXML private HBox TOKEN_PLAYER_3;
  @FXML private HBox TOKEN_PLAYER_4;
  @FXML private HBox TOKEN_PLAYER_5;
  @FXML private HBox TOKEN_PLAYER_6;

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

  @FXML private HBox STREET_1;

  @FXML private HBox STREET_2;

  @FXML private HBox STREET_3;

  @FXML private HBox STREET_4;

  @FXML private HBox DICE;

  @FXML private Label DICE_1;

  @FXML private Label DICE_2;

  @FXML private ImageView DICE_IMG_1;

  @FXML private ImageView DICE_IMG_2;

  @FXML private VBox BUTTON_CONTAINER;

  @FXML private Button NEW_GAME;

  @FXML private Button END_GAME;

  @FXML private Label FREE_PARKING;

  @FXML private HBox PLAYER_1;
  @FXML private HBox PLAYER_2;
  @FXML private HBox PLAYER_3;
  @FXML private HBox PLAYER_4;
  @FXML private HBox PLAYER_5;
  @FXML private HBox PLAYER_6;

  @FXML private ImageView IMG_PLAYER_1;
  @FXML private ImageView IMG_PLAYER_2;
  @FXML private ImageView IMG_PLAYER_3;
  @FXML private ImageView IMG_PLAYER_4;
  @FXML private ImageView IMG_PLAYER_5;
  @FXML private ImageView IMG_PLAYER_6;

  @FXML private VBox BUTTON_CONTAINER1;

  @FXML private TextArea MESSAGE_AREA;

  @FXML private Rectangle JAIL;

  @FXML private VBox RIGHT_PANEL;

  @FXML private VBox LEFT_PANEL;

  @FXML private Button BUTTON_YES;

  @FXML private Button BUTTON_NO;

  @FXML private Label TIMER;

  private Logger logger = LoggerFactory.getLogger(getClass());

  private GuiGameBoard gameBoard;
  private Stage playerPopUp;
  private Stage currentPopup;
  private PropertTycoon game;

  private String message;
  private boolean upgrade_state;
  private boolean buy_state;
  private boolean move_state;

  // Audio clips
  private AudioClip throwDiceAudio;
  private AudioClip dropTokenAudio;
  private AudioClip takeCardAudio;
  private AudioClip tingAudio;

  /** Method for displaying extended Player Details. */
  @FXML
  void playerPopUp(MouseEvent event) throws IOException {
    Logger logger = LoggerFactory.getLogger(App.class);

    // getting URL of fxml file
    URL fxmlUrl = ClassLoader.getSystemResource("ptPlayerPopup.fxml");
    FXMLLoader loader = new FXMLLoader(fxmlUrl);
    Parent root = loader.load();
    GuiPlayer player = null;
    // get controller for popup
    ptPlayerPopupCtrl controller = loader.getController();

    logger.debug("Num players: " + gameBoard.getPlayers().length);
    // Get Player
    Object box = event.getSource();
    if (box instanceof HBox) {
      Node vb = ((HBox) box).lookup("#PlayerDetail");
      Node lName = ((VBox) vb).lookup("#PlayerName");
      if (lName instanceof Label) {

        // get GuiPlayer
        for (GuiPlayer p : gameBoard.getPlayers()) {
          if (p.getName().equals(((Label) lName).getText())) {
            player = p;
            break;
          }
        }
      }
    }
    controller.setData(player);

    // Create & show scene
    Scene scene = new Scene(root);
    playerPopUp.setScene(scene);
    playerPopUp.show();
  }

  /**
   * End game and exit
   *
   * @param event
   */
  @FXML
  void endGame(ActionEvent event) {
    /*
      save game functionality
    */
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Quit Dialog");
    alert.setHeaderText("Quit Game");
    alert.setContentText("Are you sure you want to quit the game?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK) {
      // Exit game
      Platform.exit();
    }
  }

  /**
   * Method for displaying the pay or jail window & receiving user response.
   *
   * @param event
   * @throws IOException
   */
  @Subscribe
  void payOrJail(PayOrJailEvent event) throws IOException {
    // getting URL of fxml file
    URL fxmlUrl = ClassLoader.getSystemResource("ptPayOrJailPopup.fxml");
    FXMLLoader loader = new FXMLLoader(fxmlUrl);
    Parent root = loader.load();
    Stage payOrJailWindow = new Stage();
    // get controller for popup
    ptPayOrJailPopupCtrl controller = loader.getController();
    controller.setData(gameBoard.getCurrentPlayer(), event.price, payOrJailWindow);

    // Create & show scene
    Scene scene = new Scene(root);
    payOrJailWindow.setScene(scene);
    payOrJailWindow.showAndWait();

    // get result
    boolean playerIsPaying = controller.isPaying();
    if (playerIsPaying) {
      yes(new ActionEvent());
    } else {
      no(new ActionEvent());
    }
  }

  /**
   * Method for displaying card window when a card is drawn.
   *
   * @param cardDrawEvent
   * @throws IOException
   */
  @Subscribe
  void drawCard(CardDrawEvent cardDrawEvent) throws IOException {
    // getting URL of fxml file
    URL fxmlUrl = ClassLoader.getSystemResource("ptCardPopup.fxml");
    FXMLLoader loader = new FXMLLoader(fxmlUrl);
    Parent root = loader.load();
    Card card = Card.getCardByName(cardDrawEvent.deckName);

    // get controller for popup & set data
    PtCardPopupCtrl controller = loader.getController();
    Stage cardWindow = new Stage(StageStyle.TRANSPARENT);
    controller.setData(cardDrawEvent.description, card, cardWindow);

    // Create & show scene
    cardWindow.setTitle(cardDrawEvent.deckName);
    Scene scene = new Scene(root);
    scene.setFill(Color.TRANSPARENT);
    cardWindow.setScene(scene);
    cardWindow.showAndWait();
  }

  /**
   * Method for handling MortgageChangedEvent & displaying message.
   *
   * @param event
   */
  @Subscribe
  void mortgageChange(MortgageChangedEvent event) {
    String name = gameBoard.getSquares()[event.squareNumber].getName();
    if (event.mortgageStatus) {
      message = name + ", is now mortgaged";
    } else {
      message = name + ", is now unmortgaged";
    }
  }

  /**
   * Method for handling MouseChangeEvent & updating GuiProperty object.
   *
   * @param event
   */
  @Subscribe
  void houseChange(HouseChangeEvent event) {
    // find property in current players portfolio
    int i = 0;
    while (gameBoard.getCurrentPlayer().getPortfolio().get(i).getBoardPosition()
        != event.seqNumber) {
      i++;
    }
    GuiProperty property = gameBoard.getCurrentPlayer().getPortfolio().get(i);
    property.setNumHouses(event.numHouses);
  }

  /**
   * Method for handling request for a player to raise their funds, opens raise funds window &
   * implements changes to player's portfolio.
   *
   * @param player
   * @throws IOException
   */
  private void raiseFunds(GuiPlayer player) throws IOException {
    // find fxml file
    URL fxmlUrl = ClassLoader.getSystemResource("ptRaiseFundsPopup.fxml");
    FXMLLoader loader = new FXMLLoader(fxmlUrl);
    Parent root = loader.load();

    // get controller
    ptRaiseFundsPopupCtrl controller = loader.getController();

    Stage raiseFundsWindow = new Stage();
    controller.setData(player, raiseFundsWindow);

    // Create & show scene
    Scene scene = new Scene(root);
    scene.setFill(Color.TRANSPARENT);
    raiseFundsWindow.setScene(scene);
    raiseFundsWindow.show();

    // remove properties from portfolio
    raiseFundsWindow.setOnCloseRequest(
        (WindowEvent windowEvent) -> {
          List<SellProperty> properties = controller.getPropertiesToSell();

          // for every property the player owns
          for (SellProperty sellProperty : properties) {
            // if property was not mortgaged & is now mortgaged
            if (!(sellProperty.getProperty().isMortgaged()) && sellProperty.isNowMortgaged()) {

              // update GuiProperty object & send player action
              sellProperty.getProperty().mortgage();
              game.send(
                  new PlayerAction.Mortgaged(
                      gameBoard.getIndexOf(sellProperty.getProperty().getSquare())));

              // if property was mortgaged & is now not mortgaged
            } else if (sellProperty.getProperty().isMortgaged()
                && !(sellProperty.isNowMortgaged())) {

              // update GuiProperty object & send player action
              sellProperty.getProperty().unmortgage();
              game.send(
                  new PlayerAction.RemoveMortgage(
                      gameBoard.getIndexOf(sellProperty.getProperty().getSquare())));

              // otherwise, player is selling/removing houses
            } else {
              // remove houses & post player action
              for (int i = 0; i < sellProperty.getHousesToRemove(); i++) {
                sellProperty.getProperty().getSquare().removeHouse();
                game.send(
                    new PlayerAction.SellHouse(
                        gameBoard.getIndexOf(sellProperty.getProperty().getSquare())));
              }

              // if selling the whole square
              if (sellProperty.sellingSquare()) {
                // remove property from portfolio & post player action
                player.getPortfolio().remove(sellProperty.getProperty());
                sellProperty.getProperty().sell();
                game.send(
                    new PlayerAction.SellProperty(
                        gameBoard.getIndexOf(sellProperty.getProperty().getSquare())));
              }
            }
          }
        });
  }

  /**
   * Method for handling PlayerInDebtEvent, displaying the window & making the relevant changes to
   * the player's portfolio.
   *
   * @param event
   * @throws IOException
   */
  @Subscribe
  void playerInDebt(PlayerInDebtEvent event) throws IOException {

    GuiPlayer player = gameBoard.getPlayers()[event.playerId.ordinal()];

    if (event.amount > player.calculateNetWorth()) {
      // player is out of game
    } else {
      // find fxml file
      URL fxmlUrl = ClassLoader.getSystemResource("ptPlayerInDebtPopup.fxml");
      FXMLLoader loader = new FXMLLoader(fxmlUrl);
      Parent root = loader.load();

      // get controller
      ptPlayerInDebtPopupCtrl controller = loader.getController();

      Stage playerInDebtWindow = new Stage();
      controller.setData(player, event.amount, playerInDebtWindow);

      // Create & show scene
      Scene scene = new Scene(root);
      scene.setFill(Color.TRANSPARENT);
      playerInDebtWindow.setScene(scene);
      playerInDebtWindow.show();

      // remove properties from portfolio
      playerInDebtWindow.setOnCloseRequest(
          (WindowEvent windowEvent) -> {
            List<SellProperty> properties = controller.getPropertiesToSell();
            for (SellProperty sellProperty : properties) {
              // remove houses & post player action
              for (int i = 0; i < sellProperty.getHousesToRemove(); i++) {
                sellProperty.getProperty().getSquare().removeHouse();
                game.send(
                    new PlayerAction.SellHouse(
                        gameBoard.getIndexOf(sellProperty.getProperty().getSquare())));
              }
              // if selling the whole square
              if (sellProperty.sellingSquare()) {
                // remove property from portfolio & post player action
                player.getPortfolio().remove(sellProperty.getProperty());
                game.send(
                    new PlayerAction.SellProperty(
                        gameBoard.getIndexOf(sellProperty.getProperty().getSquare())));
              }
            }
          });
    }
  }

  /**
   * Method for handling GameOverEvent, displaying the winner screen.
   *
   * @param event
   * @throws IOException
   */
  @Subscribe
  void gameOver(GameOverEvent event) throws IOException {
    // find fxml file
    URL fxmlUrl = ClassLoader.getSystemResource("ptWinnerScreen.fxml");
    FXMLLoader loader = new FXMLLoader(fxmlUrl);
    Parent root = loader.load();

    // get controller
    PtWinnerCtrl controller = loader.getController();

    // get winner
    Player.Id winnerId = event.ranking.get(0);
    GuiPlayer winningPlayer = gameBoard.getPlayers()[winnerId.ordinal()];

    controller.setWinner(winningPlayer);

    // Create & show scene
    Stage gameOverWindow = new Stage();
    Scene scene = new Scene(root);
    scene.setFill(Color.TRANSPARENT);
    gameOverWindow.setScene(scene);
    gameOverWindow.show();
  }

  /**
   * Creates a New Game dialog box and sets up a new game
   *
   * @param event
   * @throws IOException
   */
  @FXML
  void newGame(ActionEvent event) throws IOException {
    Logger logger = LoggerFactory.getLogger(App.class);
    // Launch a New Game Dialog Box
    NewGame newGame = new NewGame();
    newGame.showDialog();

    // Test
    logger.debug("New Game button clicked: " + newGame.isNewGame());
    logger.debug("Is Timed Game?: " + newGame.isTimedGame());
    logger.debug("Game Time: " + newGame.getGameTime());

    // Only if New Game button clicked create a new game
    if (newGame.isNewGame()) {
      GuiToken[] t =
          new GuiToken[] {
            new GuiToken(TOKEN_PLAYER_1, 0),
            new GuiToken(TOKEN_PLAYER_2, 0),
            new GuiToken(TOKEN_PLAYER_3, 0),
            new GuiToken(TOKEN_PLAYER_4, 0),
            new GuiToken(TOKEN_PLAYER_5, 0),
            new GuiToken(TOKEN_PLAYER_6, 0)
          };

      PlayerInfo[] pi = {
        new PlayerInfo(PLAYER_1),
        new PlayerInfo(PLAYER_2),
        new PlayerInfo(PLAYER_3),
        new PlayerInfo(PLAYER_4),
        new PlayerInfo(PLAYER_5),
        new PlayerInfo(PLAYER_6)
      };

      // Hide players and tokens
      for (int i = 0; i < 6; i++) {
        pi[i].getContainer().setVisible(false);
        t[i].getToken().setVisible(false);
      }

      // Set up new Players
      GuiPlayer[] players = new GuiPlayer[newGame.getNewPlayers().size()];
      for (int i = 0; i < newGame.getNewPlayers().size(); i++) {
        players[i] =
            new GuiPlayer(
                newGame.getNewPlayers().get(i).getName(),
                t[i],
                newGame.getNewPlayers().get(i).isAi(),
                pi[i]);
        pi[i].getName().setText(newGame.getNewPlayers().get(i).getName());
        pi[i].getMoney().setText("1500");
        pi[i].getContainer().setVisible(true);
        t[i].getToken().setVisible(true);
      }

      GameSetting setting = new GameSetting();

      // Set new players
      gameBoard.setPlayers(players);
      for (int i = 1; i <= players.length; i++) {
        setting.set(Player.Id.fromInt(i));
      }

      // If a timed game, show and set timer, otherwise hide
      if (newGame.isTimedGame()) {
        int timeLimitInSeconds = newGame.getGameTime() * 3600;
        setTimer(timeLimitInSeconds);
        setting.setSecondsToEnd(timeLimitInSeconds);
        TIMER.setVisible(true);
        gameBoard.setTimedGame(true);

      } else {
        TIMER.setVisible(false);
        gameBoard.setTimedGame(false);
      }

      // Reset Gameboard
      if (event != null) {
        gameBoard.reset();
      }

      // Start the game
      game.startGame(setting);
      game.registerListener(this);
    }
  }

  /**
   * Sets Timer Label.
   *
   * @param seconds value in seconds
   */
  private void setTimer(int seconds) {
    int hours = seconds / 3600;
    int minutes = (seconds % 3600) / 60;
    int secs = seconds % 60;
    TIMER.setText(
        String.format("%02d", hours)
            + ":"
            + String.format("%02d", minutes)
            + ":"
            + String.format("%02d", secs));
  }

  /**
   * Functionality for the yes/done button, changes depending on game state.
   *
   * @param event
   */
  @FXML
  public void yes(ActionEvent event) {
    if (buy_state) {
      buyProperty(gameBoard.getSquare(gameBoard.getCurrentPlayer().getToken().getPosition()));
      game.send(PlayerAction.Yes.INSTANCE);
    } else if (upgrade_state) {
      // done upgrading
      upgrade_state = false;
      game.send(PlayerAction.DonePropertyUpgrade.INSTANCE);
    } else {
      game.send(PlayerAction.Yes.INSTANCE);
    }
  }

  /**
   * Functionality for the no/raise funds button, changes depending on game state.
   *
   * @param event
   * @throws IOException
   */
  @FXML
  public void no(ActionEvent event) throws IOException {
    if (upgrade_state) {
      raiseFunds(gameBoard.getCurrentPlayer());
    } else {
      game.send(PlayerAction.No.INSTANCE);
    }
  }

  /**
   * Method for buying a given square.
   *
   * @param square
   */
  private void buyProperty(GuiSquare square) {
    // get square
    int squareNumber = gameBoard.getIndexOf(square);

    // print game progress to console
    System.out.println(
        gameBoard.getCurrentPlayer().getName()
            + " bought "
            + gameBoard.getSquare(squareNumber).getName());

    // create GuiProperty object & add to player's portfolio
    gameBoard.getCurrentPlayer().addProperty(new GuiProperty(square));
  }

  /**
   * Method for handling current player rolling the dice.
   *
   * @param event
   */
  @FXML
  void throwDice(MouseEvent event) {
    game.send(PlayerAction.ThrowDices.INSTANCE);
  }

  /**
   * Method for handling DiceEvent, playing audio & changing dice faces.
   *
   * @param event
   */
  @Subscribe
  void diceHandler(DiceEvent event) {
    throwDiceAudio.play();
    PauseTransition pause = new PauseTransition(Duration.seconds(0));
    pause.setOnFinished(
        ev -> {
          DICE_IMG_1.setImage(gameBoard.diceFace(event.firstDice()));
          DICE_IMG_2.setImage(gameBoard.diceFace(event.secondDice()));
        });
    pause.play();
  }

  /**
   * Method for handling CanFixPropertyEvent & changing the game state/buttons.
   *
   * @param event
   */
  @Subscribe
  void upgradePropertyState(CanFixPropertyEvent event) {
    // change game state
    buy_state = false;
    upgrade_state = true;

    // change button text
    BUTTON_YES.setText("Done");
    BUTTON_NO.setText("Raise Funds");

    // display message to players
    message =
        gameBoard.getPlayers()[event.player.ordinal()].getName()
            + ", you can now upgrade properties you own by clicking on them, or sell/mortgage them to raise funds. Click done when you've finished.";
    displayMessage();
  }

  /**
   * Method to give the player upgrade functionality if the game is in the upgrade state.
   *
   * @param event
   */
  @FXML
  void upgrade_property(MouseEvent event) {
    if (upgrade_state) {
      // find square
      int squareNumber = 0;
      GuiSquare square = gameBoard.getSquares()[squareNumber];
      while (square.getPane() != event.getSource()) {
        squareNumber++;
        square = gameBoard.getSquares()[squareNumber];
      }
      // if player owns square, it is improvable & a house can be bought
      if (gameBoard.getCurrentPlayer().owns(square)
          && square.getGroup().getHousePrice() > 0
          && game.canBuyHouse(squareNumber)) {
        // add house image & send player action
        square.addHouse();
        game.send(new PlayerAction.BuildHouse(squareNumber));

        // otherwise, if the player doesn't own all the properties
      } else if (gameBoard.getCurrentPlayer().owns(square)
          && square.getGroup().getHousePrice() > 0) {
        // display warning window
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Property Upgrade Warning");
        alert.setHeaderText("Can't Build House");
        alert.setContentText("Have to own all the properties in a set to build houses!");
        alert.show();
      }
    }
  }

  /**
   * Method for handling PlayerMovedEvent & moving players around the board.
   *
   * @param event
   */
  @Subscribe
  void movePlayer(PlayerMovedEvent event) {
    int numSquares = 40;
    int steps = 0;
    switch (event.typ) {
      case FORWARD:
        steps = event.newPost - event.oldPost;
        steps = event.newPost < event.oldPost ? steps + numSquares : steps;
        break;
      case BACKWARD:
        steps = event.oldPost - event.newPost;
        steps = event.oldPost < event.newPost ? steps + numSquares : steps;
        break;
    }
    logger.debug("Start movePlayer: from " + event.oldPost + " to " + event.newPost);
    move(steps, gameBoard.getCurrentPlayer(), event.typ);
    logger.debug("End movePlayer: from " + event.oldPost + " to " + event.newPost);
  }

  /**
   * Method for opening the property hover over window & displaying property information.
   *
   * @param event
   * @throws IOException
   */
  @FXML
  void openHoverWindow(MouseEvent event) throws IOException {
    // getting URL of fxml file
    URL fxmlUrl = ClassLoader.getSystemResource("ptPropertyPopup.fxml");
    FXMLLoader loader = new FXMLLoader(fxmlUrl);
    Parent root = loader.load();

    // get controller for popup
    ptPropertyPopupCtrl controller = loader.getController();

    // find square number
    int squareNumber = 0;
    while (gameBoard.getSquares()[squareNumber].getPane() != event.getSource()) {
      squareNumber++;
    }

    // if there is property info for square (ie. if square is a property square)
    if (game.propertyInfo(squareNumber + 1).isPresent()) {

      // load data to controller
      controller.setData(
          game.propertyInfo(squareNumber + 1).get(), gameBoard.getSquare(squareNumber), gameBoard);

      // Create & show scene
      Scene scene = new Scene(root);
      scene.setFill(Color.TRANSPARENT);
      currentPopup.setScene(scene);
      currentPopup.show();
    }
  }

  /**
   * Method for closing the current property popup if it is currently open.
   *
   * @param event
   */
  @FXML
  void closeHoverWindow(MouseEvent event) {
    if (currentPopup.isShowing()) {
      currentPopup.close();
    }
  }

  /** Method for displaying message to the players & printing to console */
  private void displayMessage() {
    MESSAGE_AREA.setText(message);
    System.out.println(message);
  }

  /**
   * Method for handling TimeTicEvent & updating the timer.
   *
   * @param event
   */
  @Subscribe
  void timeUpdate(TimeTicEvent event) {
    Platform.runLater(() -> setTimer(event.secondsLeft));
  }

  /**
   * Method for handling FreeParkChangeEvent, updating the free parking label & playing the cash
   * change audio.
   *
   * @param event
   */
  @Subscribe
  void freeParking(FreeParkChangeEvent event) {
    tingAudio.play();
    FREE_PARKING.setText("" + event.newCash);
  }

  @Subscribe
  void changeTurn(ChangePlayerEvent event) {

    // change buttons back to yes/no
    BUTTON_YES.setText("Yes");
    BUTTON_NO.setText("No");

    // switch active player & highlight new active player
    gameBoard.getCurrentPlayer().getInfo().getName().getStyleClass().clear();
    gameBoard.getCurrentPlayer().getInfo().getName().getStyleClass().add("playerName");
    gameBoard.setNextPlayer(event.activePlayer.ordinal());
    gameBoard.getCurrentPlayer().getInfo().getName().getStyleClass().clear();
    gameBoard.getCurrentPlayer().getInfo().getName().getStyleClass().add("playerNameHighlighted");

    // display message & update state
    message = gameBoard.getCurrentPlayer().getName() + ", roll dice!";
    displayMessage();
    move_state = true;
  }

  /**
   * Method for handling CashEvent & changing the relevant player's cash label to the updated total.
   *
   * @param event
   */
  @Subscribe
  void updateMoney(CashEvent event) {
    Label l = (Label) gameBoard.getPlayers()[event.id.ordinal()].getInfo().getMoney();
    l.setText("" + event.getNewCash());
    tingAudio.play();
  }

  /**
   * Method for handling PlayerToJailEvent & moving the player to jail.
   *
   * @param event
   */
  @Subscribe
  void goToJail(PlayerToJailEvent event) {
    // get player position
    GuiPlayer player = gameBoard.getPlayers()[event.playerId.ordinal()];
    int position = player.getToken().getPosition();

    // move to jail square (square 10)
    if ((10 - position) > 0) {
      move(10 - position, player, Movement.FORWARD);
    } else {
      move(10 - position, player, Movement.BACKWARD);
    }
  }

  /**
   * Method for moving tokens around the board.
   *
   * @param spaces
   * @param player
   * @param direction
   */
  private void move(int spaces, GuiPlayer player, Movement direction) {
    GuiToken tok = player.getToken();
    PathTransition pt = new PathTransition();
    Path p =
        new Path(
            new MoveTo(tok.getToken().getTranslateX() + 25, tok.getToken().getTranslateY() + 25));
    GuiCoords newPos = gameBoard.getSquare(tok.getPosition()).getCentre();
    p.getElements().add(new LineTo(newPos.getX(), newPos.getY()));

    for (int i = 0; i < abs(spaces); i++) {
      if ((direction == Movement.BACKWARD)) {
        tok.moveBackwards();
      } else tok.moveForwards();
      newPos = gameBoard.getSquare(tok.getPosition()).getCentre();
      p.getElements().add(new LineTo(newPos.getX(), newPos.getY()));
    }
    pt.setNode(tok.getToken());
    pt.setDuration(Duration.millis(abs(spaces) * 300));
    pt.setPath(p);
    pt.play();
    //displayMessage();
  }

  /**
   * Method for handling BuyOrNotMsg events & displaying the message.
   *
   * @param msg
   */
  @Subscribe
  void propertyFunctionality(BuyOrNotMsg msg) {
    move_state = false;
    message = "Would you like to buy " + msg.propName + " for " + msg.price + "?";
    displayMessage();
    buy_state = true;
  }

  /** Calculate the centre point of each square relative to game board Pane */
  void calculateSquareCentres() {
    Direction dir = Direction.DOWN;
    Double tileHeight = SQUARE_1.getPrefHeight();
    Double tileWidth = SQUARE_1.getPrefWidth();
    Double halfHeight = tileHeight / 2;
    Double halfWidth = tileWidth / 2;
    Double board_size = GAME_BOARD_CONTAINER.getPrefHeight();
    GuiCoords xy = new GuiCoords(board_size - halfHeight, board_size - tileHeight + halfWidth);

    for (GuiSquare sq : gameBoard.getSquares()) {
      if (sq.getPane().getChildren().get(0) instanceof VBox) {
        sq.setCentre(xy);
        //sq.drawCentre(GAME_BOARD_CONTAINER);
        xy = xy.moveCoords(tileWidth, dir);
      } else {
        // Turn a corner
        xy = xy.moveCoords(halfHeight - halfWidth, dir);
        sq.setCentre(xy);
        //sq.drawCentre(GAME_BOARD_CONTAINER);
        dir = dir.turnRight();
        xy = xy.moveCoords(halfHeight + halfWidth, dir);
      }
    }
  }

  /** Rescales the game board for different screen resolutions/DPI combinations */
  void rescaleGameBoard(Double scaleFactor) {
    Double default_border = 2.0; //4.0
    Double default_board_size = gameBoard.getBoardWidth() * scaleFactor; //800.0
    Double default_street_height = gameBoard.getCornerSize() * scaleFactor; //103.0
    Double default_street_width = default_board_size - (2.0 * default_street_height);
    Double default_tile_width = default_street_width / gameBoard.getPropertiesPerStreet();
    Double default_tile_height = default_street_height;
    Double default_corner_inner_size = default_street_height - default_border;
    Double default_tile_inner_height = default_street_height - default_border;
    Double default_tile_inner_width = default_tile_width - 2;
    Double default_house_block_height = 0.22 * default_street_height * scaleFactor;
    Double default_property_name_height = 0.55 * default_street_height * scaleFactor;
    Double default_price_height = 0.22 * default_street_height * scaleFactor;

    // Resize game board containers
    GAME_BOARD_CONTAINER.setPrefSize(default_board_size, default_board_size);
    GAME_GRID.getColumnConstraints().get(0).setPrefWidth(default_street_height);
    GAME_GRID.getColumnConstraints().get(1).setPrefWidth(default_street_width);
    GAME_GRID.getColumnConstraints().get(2).setPrefWidth(default_street_height);
    GAME_GRID.getRowConstraints().get(0).setPrefHeight(default_street_height);
    GAME_GRID.getRowConstraints().get(1).setPrefHeight(default_street_width);
    GAME_GRID.getRowConstraints().get(2).setPrefHeight(default_street_height);

    TOKEN_PLAYER_1.setTranslateX(default_board_size - default_tile_height);
    TOKEN_PLAYER_2.setTranslateX(default_board_size - default_tile_height);
    TOKEN_PLAYER_3.setTranslateX(default_board_size - default_tile_height);
    TOKEN_PLAYER_4.setTranslateX(default_board_size - default_tile_height);
    TOKEN_PLAYER_5.setTranslateX(default_board_size - default_tile_height);
    TOKEN_PLAYER_6.setTranslateX(default_board_size - default_tile_height);

    TOKEN_PLAYER_1.setTranslateY(default_board_size - default_tile_height);
    TOKEN_PLAYER_2.setTranslateY(default_board_size - default_tile_height);
    TOKEN_PLAYER_3.setTranslateY(default_board_size - default_tile_height);
    TOKEN_PLAYER_4.setTranslateY(default_board_size - default_tile_height);
    TOKEN_PLAYER_5.setTranslateY(default_board_size - default_tile_height);
    TOKEN_PLAYER_6.setTranslateY(default_board_size - default_tile_height);

    // Resize Street of properties
    STREET_1.setPrefSize(default_street_width, default_street_height);
    STREET_2.setPrefSize(default_street_width, default_street_height);
    STREET_3.setPrefSize(default_street_width, default_street_height);
    STREET_4.setPrefSize(default_street_width, default_street_height);

    // Resize corner images
    JAIL_IMG.setFitHeight(default_corner_inner_size * 0.7);
    JAIL_IMG.setFitWidth(default_corner_inner_size * 0.7);
    IMG_GO.setFitHeight(default_corner_inner_size);
    IMG_GO.setFitWidth(default_corner_inner_size);
    IMG_FREE_PARKING.setFitHeight(default_corner_inner_size);
    IMG_FREE_PARKING.setFitWidth(default_corner_inner_size);
    IMG_GOTO_JAIL.setFitHeight(default_corner_inner_size);
    IMG_GOTO_JAIL.setFitWidth(default_corner_inner_size);

    FREE_PARKING.setPrefWidth(default_corner_inner_size);
    FREE_PARKING.setPrefHeight(default_corner_inner_size);

    // Resize Tile images
    ImageView[] tileImages =
        new ImageView[] {
          IMG_TRAIN_STAT_1,
          IMG_TRAIN_STAT_2,
          IMG_TRAIN_STAT_3,
          IMG_TRAIN_STAT_4,
          IMG_POWER_STAT,
          IMG_WATER_CO,
          IMG_INCOME_TAX,
          IMG_SUPER_TAX,
          IMG_POT_LUCK_1,
          IMG_POT_LUCK_2,
          IMG_POT_LUCK_3,
          IMG_OP_KNOCK_1,
          IMG_OP_KNOCK_2,
          IMG_OP_KNOCK_3
        };
    for (int i = 0; i < tileImages.length; i++) {
      tileImages[i].setFitHeight(default_tile_inner_height);
      tileImages[i].setFitWidth(default_tile_inner_width);
    }

    // Resize squares
    for (GuiSquare sq : gameBoard.getSquares()) {
      if (sq.getPane().getChildren().get(0) instanceof VBox) {
        sq.getPane().setPrefHeight(default_tile_height);
        sq.getPane().setPrefWidth(default_tile_width);
        ((Pane) sq.getPane().lookup("#PANEL"))
            .setPrefSize(default_tile_inner_width, default_tile_inner_height);
        ((Pane) sq.getPane().lookup("#PROPERTY_GROUP"))
            .setPrefSize(default_tile_inner_width, default_house_block_height);
        ((Label) sq.getPane().lookup("#PROPERTY_NAME"))
            .setPrefSize(default_tile_inner_width, default_property_name_height);
        ((Label) sq.getPane().lookup("#PROPERTY_PRICE"))
            .setPrefSize(default_tile_inner_width, default_price_height);
      } else {
        sq.getPane().setPrefSize(default_street_height, default_street_height);
        ((HBox) sq.getPane().getChildren().get(0))
            .setPrefSize(default_corner_inner_size, default_corner_inner_size);
      }
    }
    calculateSquareCentres();
  }

  /**
   * Method for initializing the GUI, called on startup.
   *
   * @throws IOException
   */
  @FXML
  void initialize() throws IOException {
    Logger logger = LoggerFactory.getLogger(App.class);
    checkNotNull();
    // Build GuiSquare array
    GuiSquare[] guiSquares =
        new GuiSquare[] {
          new GuiSquare(SQUARE_0),
          new GuiSquare(SQUARE_1),
          new GuiSquare(SQUARE_2),
          new GuiSquare(SQUARE_3),
          new GuiSquare(SQUARE_4),
          new GuiSquare(SQUARE_5),
          new GuiSquare(SQUARE_6),
          new GuiSquare(SQUARE_7),
          new GuiSquare(SQUARE_8),
          new GuiSquare(SQUARE_9),
          new GuiSquare(SQUARE_10),
          new GuiSquare(SQUARE_11),
          new GuiSquare(SQUARE_12),
          new GuiSquare(SQUARE_13),
          new GuiSquare(SQUARE_14),
          new GuiSquare(SQUARE_15),
          new GuiSquare(SQUARE_16),
          new GuiSquare(SQUARE_17),
          new GuiSquare(SQUARE_18),
          new GuiSquare(SQUARE_19),
          new GuiSquare(SQUARE_20),
          new GuiSquare(SQUARE_21),
          new GuiSquare(SQUARE_22),
          new GuiSquare(SQUARE_23),
          new GuiSquare(SQUARE_24),
          new GuiSquare(SQUARE_25),
          new GuiSquare(SQUARE_26),
          new GuiSquare(SQUARE_27),
          new GuiSquare(SQUARE_28),
          new GuiSquare(SQUARE_29),
          new GuiSquare(SQUARE_30),
          new GuiSquare(SQUARE_31),
          new GuiSquare(SQUARE_32),
          new GuiSquare(SQUARE_33),
          new GuiSquare(SQUARE_34),
          new GuiSquare(SQUARE_35),
          new GuiSquare(SQUARE_36),
          new GuiSquare(SQUARE_37),
          new GuiSquare(SQUARE_38),
          new GuiSquare(SQUARE_39)
        };

    // Load Audio Clips
    throwDiceAudio =
        new AudioClip(ClassLoader.getSystemResource("audio/rollDice.mp3").toExternalForm());
    dropTokenAudio =
        new AudioClip(ClassLoader.getSystemResource("audio/dropToken.mp3").toExternalForm());
    takeCardAudio =
        new AudioClip(ClassLoader.getSystemResource("audio/takeCard.mp3").toExternalForm());
    tingAudio = new AudioClip(ClassLoader.getSystemResource("audio/ting.mp3").toExternalForm());

    // Initialise players - FOR TESTING
    // To be set up by New Game Dialog box
    GuiPlayer[] players =
        new GuiPlayer[] {
          new GuiPlayer(
              "Player 1", new GuiToken(TOKEN_PLAYER_1, 0), false, new PlayerInfo(PLAYER_1)),
          new GuiPlayer(
              "Player 2", new GuiToken(TOKEN_PLAYER_2, 0), false, new PlayerInfo(PLAYER_2)),
          new GuiPlayer(
              "Player 3", new GuiToken(TOKEN_PLAYER_3, 0), true, new PlayerInfo(PLAYER_3)),
          new GuiPlayer(
              "Player 4", new GuiToken(TOKEN_PLAYER_4, 0), true, new PlayerInfo(PLAYER_4)),
          new GuiPlayer(
              "Player 5", new GuiToken(TOKEN_PLAYER_5, 0), true, new PlayerInfo(PLAYER_5)),
          new GuiPlayer("Player 6", new GuiToken(TOKEN_PLAYER_6, 0), true, new PlayerInfo(PLAYER_6))
        };

    gameBoard = new GuiGameBoard(GAME_BOARD_CONTAINER);
    gameBoard.setSquares(guiSquares);
    gameBoard.setPlayers(players);
    gameBoard.getCurrentPlayer().getInfo().getName().getStyleClass().add("playerNameHighlighted");

    logger.debug("Screen height: " + Screen.getPrimary().getBounds().getHeight());
    logger.debug("Screen scaling: " + Screen.getPrimary().getOutputScaleX());
    // Scale game board based on screen DPI
    //rescaleGameBoard((Screen.getPrimary().getBounds().getHeight() / 720) * 1 / Screen.getPrimary().getOutputScaleX());
    rescaleGameBoard(1 / (1080.0 / Screen.getPrimary().getBounds().getHeight()));

    // read JSON file
    BoardReaderJson boardReader = new BoardReaderJson();
    boardReader.readFile("src/main/resources/boardDataJSON.json");

    // set popup & game variables/listener
    currentPopup = new Stage();
    currentPopup.initStyle(StageStyle.TRANSPARENT);
    playerPopUp = new Stage();
    playerPopUp.initStyle(StageStyle.UTILITY);
    playerPopUp.setTitle("Extended Player Data");

    game = Game.newGame();

    game.registerListener(this);

    // for every square on the board
    for (GuiSquare sq : gameBoard.getSquares()) {
      boardReader.nextObject();

      // if square is a non-corner square
      if (sq.getPane().getChildren().get(0) instanceof VBox) {

        // access elements of square
        VBox v = (VBox) sq.getPane().getChildren().get(0);
        HBox group = (HBox) v.lookup("#PROPERTY_GROUP");
        Label name = (Label) v.lookup("#PROPERTY_NAME");
        Label price = (Label) v.lookup("#PROPERTY_PRICE");
        // set group names (if square is in a group)
        if (boardReader.getProperties().get("group") != null) {
          String propGroup =
              boardReader.getProperties().get("group").toUpperCase().replace(' ', '_');
          sq.setGroup(PropertyGroup.valueOf(propGroup));
          group.getStyleClass().clear();
          group.getStyleClass().add(sq.getGroup().getCssClass());
        }

        // set name/price values
        name.setText(boardReader.getProperties().get("name"));
        price.setText(boardReader.getProperties().get("cost"));
      }
      // get next square

    }

    message = gameBoard.getCurrentPlayer().getName() + " , roll the dice!";
    displayMessage();
    upgrade_state = false;
    buy_state = false;
    move_state = true;

    // GAME OVER TEST
    // Create and show a New Game Dialog
    GameOver gameOver = new GameOver();
    gameOver.setWinner(new GuiPlayer("Duane Dibbley", null));
    gameOver.showDialog();
    if (gameOver.doNewGame()) {
      newGame(null);
    } else {
      endGame(null);
    }
    // Results pushed to GameBoard class
    //NewGame newGameDialog = new NewGame();
    //newGameDialog.showDialog();
    //gameBoard.setPlayers(newGameDialog.getNewPlayers().toArray(GuiPlayer[]::new));
    //logger.debug("Number of players: " + gameBoard.numberPlayers());
  }

  /** Method for checking fields are not null. */
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
