package com.watson.propert.tycoon.control;

/**
 * Direction representation
 *
 * @auther Lee Richards
 * @version Sprint3
 */
public enum Direction {
  DOWN(0),
  LEFT(1),
  UP(2),
  RIGHT(3);

  private int value;

  private Direction(int value) {
    this.value = value;
  }

  private Direction turn(int r) {
    Direction[] dirs = Direction.values();
    return dirs[(this.value + r) % 4];
  }

  public Direction turnRight() {
    return turn(1);
  }

  public Direction turnLeft() {
    return turn(3);
  }

  public Direction turnBack() {
    return turn(2);
  }
}
