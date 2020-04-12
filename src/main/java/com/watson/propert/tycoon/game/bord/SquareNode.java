package com.watson.propert.tycoon.game.bord;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class SquareNode implements Square {

  private SquareNode next;
  private SquareNode back;
  private String name;
  private SquareTyp typ;
  private final int seqNumber;

  public SquareNode(int seqNumber, String name) {
    this.seqNumber = seqNumber;
    this.name = name;
    this.typ = new NullTyp();
  }

  public SquareNode(int seqNumber, String name, SquareTyp typ) {
    this.seqNumber = seqNumber;
    this.name = name;
    this.typ = typ;
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

  @Override
  public void visitBy(SquareVisitor visitor) {
    typ.visitBy(visitor);
  }

  @Override
  public int getNumber() {
    return seqNumber;
  }

  protected void setNext(SquareNode node) {
    next = node;
  }

  protected void setBack(SquareNode node) {
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

    private SquareNode iterStart;
    private SquareNode iterNext;
    private Boolean atBegin = Boolean.TRUE;

    private BordIterator(SquareNode start) {
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
    public SquareNode next() {
      atBegin = false;
      SquareNode current = iterNext;
      iterNext = iterNext.next;
      return current;
    }
  }

  private static class BackwardIterator implements Iterator<Square> {

    private SquareNode iterStart;
    private SquareNode iterNext;
    private Boolean atBegin = Boolean.TRUE;

    private BackwardIterator(SquareNode start) {
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
    public SquareNode next() {
      atBegin = false;
      SquareNode current = iterNext;
      iterNext = iterNext.back;
      return current;
    }
  }
}
