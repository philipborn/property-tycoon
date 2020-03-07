package com.watson.propert.tycoon.game;

public interface SquareVisitor {
  void SquareImp(SquareImp square);

  void street(Street street);

  void station(Station station);

  void utilities(Utilities utilities);
}
