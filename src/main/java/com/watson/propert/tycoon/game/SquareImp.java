package com.watson.propert.tycoon.game;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class SquareImp implements Square {

  private Square next;
  private Square back;
  private String name;

  public SquareImp() {}

  public SquareImp(String name) {
    this.name = name;
  }

  @Override
  public Square nextSquare() {
    return next;
  }

  @Override
  public Square backSquare() {
    return back;
  }

  @Override
  public String name() {
    return name;
  }

  public void setNext(Square node) {
    next = node;
  }

  public void setBack(Square node) {
    back = node;
  }

  @Override
  public Iterator<Square> iterator() {
    return new BordIterator(this);
  }

  @Override
  public void forEach(Consumer action) {
    new BordIterator(this).forEachRemaining(action);
  }

  @Override
  public Spliterator<Square> spliterator() {
    throw new UnsupportedOperationException();
  }
}
