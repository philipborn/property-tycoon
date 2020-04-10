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
  public String getName() {
    return name;
  }

  @Override
  public Square forward(int steps) {
    if (steps <= 0) {
      return this;
    }
    return next.forward(steps - 1);
  }

  @Override
  public Square forward(int steps, SquareVisitor passingVisitor) {
    if (steps <= 0) {
      return this;
    }
    next.visitBy(passingVisitor);
    return next.forward(steps - 1, passingVisitor);
  }

  @Override
  public Square forwardTo(String propertyName) {
    Iterator<Square> iter = this.iterator();
    return iterate(iter);
  }

  @Override
  public Square forwardTo(String name, SquareVisitor passingVisitor) {
    Iterator<Square> iter = this.iterator();
    return iterate(iter, passingVisitor);
  }

  @Override
  public Square backward(int steps) {
    if (steps <= 0) {
      return this;
    }
    return back.backward(steps - 1);
  }

  @Override
  public Square backward(int steps, SquareVisitor passingVisitor) {
    if (steps <= 0) {
      return this;
    }
    back.visitBy(passingVisitor);
    return back.backward(steps - 1, passingVisitor);
  }

  @Override
  public Square backwardTo(String name) {
    Iterator<Square> iter = new BackwardIterator(this);
    return iterate(iter);
  }

  @Override
  public Square backwardTo(String name, SquareVisitor passingVisitor) {
    Iterator<Square> iter = new BackwardIterator(this);
    return iterate(iter, passingVisitor);
  }

  private Square iterate(Iterator<Square> iter) {
    while (iter.hasNext()) {
      Square square = iter.next();
      if (square.getName().equals(name)) {
        return square;
      }
    }
    return null;
  }

  private Square iterate(Iterator<Square> iter, SquareVisitor passingVisitor) {
    Square square;
    while (iter.hasNext()) {
      square = iter.next();
      if (square.getName().equals(name)) {
        return square;
      }
      if (!square.getName().equals(this.name)) {
        square.visitBy(passingVisitor);
      }
      ;
    }
    return null;
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

  private static class BordIterator implements Iterator<Square> {

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

  private static class BackwardIterator implements Iterator<Square> {

    private SquareAbstract iterStart;
    private SquareAbstract iterNext;
    private Boolean atBegin = Boolean.TRUE;

    private BackwardIterator(SquareAbstract start) {
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
      iterNext = iterNext.back;
      return current;
    }
  }
}
