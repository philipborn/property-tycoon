package com.watson.propert.tycoon.gui;

import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CLass to represent the GUI game board
 *
 * @author Lee Richards
 * @version Sprint4
 */
public class GuiGameBoard {
  public static int length = 40;
  Logger logger = LoggerFactory.getLogger(App.class);
  Pane gameBoard;
  Double gameBoardWidth = 960.0;
  Double cornerSize = 130.0;
  int properties_per_street = 9;
  GuiSquare[] squares;
  GuiPlayer[] players;
  GuiDice dice;
  int currentPlayer = 0;
  Boolean timedGame = false;
  int timeRemaining = 0;

  public GuiGameBoard(Pane gameBoard) {
    this.gameBoard = gameBoard;
    this.dice = new GuiDice();
  }

  public GuiSquare[] getSquares() {
    return squares;
  }

  public void setSquares(GuiSquare[] squares) {
    this.squares = squares;
    this.length = this.squares.length;
  }

  public GuiPlayer[] getPlayers() {
    return players;
  }

  public void setPlayers(GuiPlayer[] players) {
    this.players = players;
  }

  public GuiPlayer getCurrentPlayer() {
    return this.players[currentPlayer];
  }

  public GuiSquare getSquare(int i) {
    return squares[i % length];
  }

  public Double getBoardWidth() {
    return this.gameBoardWidth;
  }

  public Double getCornerSize() {
    return this.cornerSize;
  }

  public int getPropertiesPerStreet() {
    return this.properties_per_street;
  }

  public GuiPlayer getNextPlayer() {
    currentPlayer = (currentPlayer + 1) % numberPlayers();
    return players[currentPlayer];
  }

  public int numberPlayers() {
    if (this.players == null) {
      return 0;
    }
    return players.length;
  }

  public Image diceFace(int i) {
    return dice.getDiceFace(i);
  }

  public Boolean getTimedGame() {
    return timedGame;
  }

  public void setTimedGame(Boolean timedGame) {
    this.timedGame = timedGame;
  }

  // Clear all houses and hotels from gameboard
  public void clearAllHouses() {
    for (GuiSquare sq : squares) {
      clearHousesFromProperty(sq);
    }
  }

  // Clear all houses or hotel from a Square
  public void clearHousesFromProperty(GuiSquare property) {
    VBox panel = (VBox) property.getPane().lookup("#PANEL");
    if (panel != null) {
      HBox group = (HBox) panel.lookup("#PROPERTY_GROUP");
      group.getChildren().clear();
    }
  }

  // Reset Tokens to GO
  public void tokensReset() {
    for (GuiPlayer player : players) {
      GuiCoords centre = getSquare(0).getCentre();
      player.getToken().setPosition(0);
      player.getToken().getToken().setTranslateX(centre.getX() - 25);
      player.getToken().getToken().setTranslateY(centre.getY() - 25);
    }
  }

  // Reset gameboard
  public void reset() {
    currentPlayer = 0;
    tokensReset();
    // Clear all houses and hotels from board
    clearAllHouses();
  }
}
