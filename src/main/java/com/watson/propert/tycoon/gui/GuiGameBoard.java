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

  /**
   * Construct a new GuiGameBoard
   *
   * @param gameBoard main Pane of the game board
   */
  public GuiGameBoard(Pane gameBoard) {
    this.gameBoard = gameBoard;
    this.dice = new GuiDice();
  }

  /**
   * Gets the index number of a GuiSquare
   *
   * @param guiSquare
   * @return Index of square on game board
   */
  public int getIndexOf(GuiSquare guiSquare) {
    int i = 0;
    while (squares[i] != guiSquare) {
      i++;
    }
    return i;
  }

  /**
   * Gets an array of all GuiSquares on the game board
   *
   * @return Array of GuiSquares
   */
  public GuiSquare[] getSquares() {
    return squares;
  }

  /**
   * Set game board GuiSquares
   *
   * @param squares
   */
  public void setSquares(GuiSquare[] squares) {
    this.squares = squares;
    this.length = this.squares.length;
  }

  /**
   * Get details of all Players in the game
   *
   * @return An array of GuiPlayer
   */
  public GuiPlayer[] getPlayers() {
    return players;
  }

  /**
   * Set Players in current game
   *
   * @param players
   */
  public void setPlayers(GuiPlayer[] players) {
    this.players = players;
  }

  /**
   * Get details of the player currently taking their turn
   *
   * @return the active player
   */
  public GuiPlayer getCurrentPlayer() {
    return this.players[currentPlayer];
  }

  /**
   * Get a GuiSquare using index on game board
   *
   * @param i index to position on game board
   * @return the requested GuiSquare
   */
  public GuiSquare getSquare(int i) {
    return squares[i % length];
  }

  /**
   * Gets the width of the game board
   *
   * @return width of board
   */
  public Double getBoardWidth() {
    return this.gameBoardWidth;
  }

  /**
   * Get the dimensions of a corner square
   *
   * @return length of a corner square
   */
  public Double getCornerSize() {
    return this.cornerSize;
  }

  /**
   * Get the number of property squares on one side of the game board
   *
   * @return number of properties along a street (usually 9)
   */
  public int getPropertiesPerStreet() {
    return this.properties_per_street;
  }

  /**
   * Set next player
   *
   * @param idNumber
   * @return selected next player
   */
  public GuiPlayer setNextPlayer(int idNumber) {
    if (idNumber >= numberPlayers() || idNumber < 0) {
      throw new IllegalArgumentException(
          "IdNumber need be positive and smaller then number of player");
    }
    currentPlayer = (idNumber);
    return players[currentPlayer];
  }

  /**
   * Get number of players in current game
   *
   * @return total number of players
   */
  public int numberPlayers() {
    if (this.players == null) {
      return 0;
    }
    return players.length;
  }

  /**
   * Get Image of dice face
   *
   * @param i number of dice face
   * @return image of dice
   */
  public Image diceFace(int i) {
    return dice.getDiceFace(i);
  }

  /**
   * Does the current game finished on a timeout
   *
   * @return true if a timed game, false otherwise
   */
  public Boolean getTimedGame() {
    return timedGame;
  }

  /**
   * Set the current game as a timed game
   *
   * @param timedGame
   */
  public void setTimedGame(Boolean timedGame) {
    this.timedGame = timedGame;
  }

  /** Clear all houses and hotels from game board */
  public void clearAllHouses() {
    for (GuiSquare sq : squares) {
      clearHousesFromProperty(sq);
    }
  }

  /**
   * Clear all houses or hotel from a given Square
   *
   * @param property
   */
  public void clearHousesFromProperty(GuiSquare property) {
    VBox panel = (VBox) property.getPane().lookup("#PANEL");
    if (panel != null) {
      HBox group = (HBox) panel.lookup("#PROPERTY_GROUP");
      group.getChildren().clear();
    }
  }

  /** Reset Tokens to GO */
  public void tokensReset() {
    for (GuiPlayer player : players) {
      GuiCoords centre = getSquare(0).getCentre();
      player.getToken().setPosition(0);
      player.getToken().getToken().setTranslateX(centre.getX() - 25);
      player.getToken().getToken().setTranslateY(centre.getY() - 25);
    }
  }

  /** Reset game board for a new game */
  public void reset() {
    currentPlayer = 0;
    tokensReset();
    // Clear all houses and hotels from board
    clearAllHouses();
  }
}
