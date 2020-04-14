package com.watson.propert.tycoon.game.bord;

public interface CardOrder {
  String getDeckName();

  String getTyp();

  String getDescription();

  int getPay();

  int getSteps();

  int getPosition();

  String getCard();

  int getHouse();

  int getHotel();
}
