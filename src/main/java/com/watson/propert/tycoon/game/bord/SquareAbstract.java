package com.watson.propert.tycoon.game.bord;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public abstract class SquareAbstract implements Square {

  private SquareAbstract next;
  private SquareAbstract back;
  private String name;

  public SquareAbstract(String name) {
    this.name = name;
  }

  @Override
  public Square step(int numStep, SquareVisitor actionOnTheWay) {
    Square newLocation = this;
    if (numStep > 0) {
      newLocation = stepForward(numStep, actionOnTheWay);
    } else if (numStep < 0) {
      newLocation = stepBack(numStep, actionOnTheWay);
    }
    return newLocation;
  }

  @Override
  public Square step(int steps) {
    return step(steps, null);
  }

  @Override
  public Square find(String propertyName) {
    Iterator<Square> iter = this.iterator();
    while (iter.hasNext()) {
      Square square = iter.next();
      if (square.name().equals(propertyName)) {
        return square;
      }
    }
    return null;
  }

  @Override
  public Square find(String propertyName, SquareVisitor visitor) {
    Iterator<Square> iter = this.iterator();
    while (iter.hasNext()) {
      Square square = iter.next();
      square.visitBy(visitor);
      if (square.name().equals(propertyName)) {
        return square;
      }
    }
    return null;
  }

  private Square stepBack(int steps, SquareVisitor actionOnTheWay) {
    if (steps < 0) {
      if (actionOnTheWay != null) {
        this.back.visitBy(actionOnTheWay);
      }
      return this.back.step(steps + 1, actionOnTheWay);
    } else {
      return this;
    }
  }

  private Square stepForward(int steps, SquareVisitor actionOnTheWay) {
    if (steps > 0) {
      if (actionOnTheWay != null) {
        this.back.visitBy(actionOnTheWay);
      }
      return this.next.step(steps - 1, actionOnTheWay);
    } else {
      return this;
    }
  }

  @Override
  public String name() {
    return name;
  }

  protected void setNext(SquareAbstract node) {
    next = node;
  }

  protected void setBack(SquareAbstract node) {
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

  private class BordIterator implements Iterator<Square> {

    private SquareAbstract iterStart;
    private SquareAbstract iterNext;
    private Boolean atBegin = Boolean.TRUE;

    private BordIterator(SquareAbstract start) {
      this.iterStart = start;
      this.iterNext = start;
    }

    @Override
    public boolean hasNext() {
      if (atBegin) {
        return true;
      }
      return iterNext != iterStart;
    }

    @Override
    public SquareAbstract next() {
      atBegin = false;
      SquareAbstract current = iterNext;
      iterNext = iterNext.next;
      return current;
    }
  }
}
