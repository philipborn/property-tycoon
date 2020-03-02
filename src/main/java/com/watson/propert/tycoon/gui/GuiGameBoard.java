package com.watson.propert.tycoon.gui;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CLass to represent the GUI game board
 *
 * @author Lee Richards
 * @version Sprint3
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
  String timeRemaining = "01:59:59";

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
}
