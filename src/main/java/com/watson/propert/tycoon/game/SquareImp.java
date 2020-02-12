package com.watson.propert.tycoon.game;

public class SquareImp implements Square {

  Square next;
  Square back;

  @Override
  public Square nextSquare() {
    return next;
  }

  @Override
  public Square backSquare() {
    return back;
  }

  public void setNext(Square node) {
    next = node;
  }

  public void setBack(Square node) {
    back = node;
  }
}
