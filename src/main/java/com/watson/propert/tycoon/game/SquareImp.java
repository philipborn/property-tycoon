package com.watson.propert.tycoon.game;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class SquareImp implements Square {

  private Square next;
  private Square back;
  private String name;

  public SquareImp(String name) {
    this.name = name;
  }

  @Override
  public Square move(int numStep, SquareVisitor actionOnTheWay) {
    Square newLocation = this;
    if (numStep > 0) {
      newLocation = stepForward(numStep, actionOnTheWay);
    } else if (numStep < 0) {
      newLocation = stepBack(numStep, actionOnTheWay);
    }
    return newLocation;
  }

  private Square stepBack(int steps, SquareVisitor actionOnTheWay) {
    if (steps < 0) {
      this.back.vist(actionOnTheWay);
      return this.back.move(steps + 1, actionOnTheWay);
    } else {
      return this;
    }
  }

  private Square stepForward(int steps, SquareVisitor actionOnTheWay) {
    if (steps > 0) {
      this.next.vist(actionOnTheWay);
      return this.next.move(steps - 1, actionOnTheWay);
    } else {
      return this;
    }
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

  @Override
  public void vist(SquareVisitor visitor) {
    visitor.SquareImp(this);
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
    Iterator<Square> iter = new BordIterator(this);
    iter.forEachRemaining(action);
  }

  @Override
  public Spliterator<Square> spliterator() {
    throw new UnsupportedOperationException();
  }
}
