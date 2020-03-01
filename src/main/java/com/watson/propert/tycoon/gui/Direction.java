package com.watson.propert.tycoon.gui;

/**
 * Direction representation
 *
 * @author Lee Richards
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

  /*
   * Turn direction
   * Returns a new Direction
   */
  private Direction turn(int r) {
    Direction[] dirs = Direction.values();
    return dirs[(this.value + r) % 4];
  }

  /**
   * Change direction by turning right
   *
   * @return Direction
   */
  public Direction turnRight() {
    return turn(1);
  }

  /**
   * Change direction by turning left
   *
   * @return Direction
   */
  public Direction turnLeft() {
    return turn(3);
  }

  /**
   * Change direction by turning in opposite direction
   *
   * @return Direction
   */
  public Direction turnBack() {
    return turn(2);
  }
}
