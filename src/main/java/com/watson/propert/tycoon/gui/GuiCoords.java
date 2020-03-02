package com.watson.propert.tycoon.gui;

/**
 * GUI Coordinates Class. A simple data structure to represent X and Y coordinates.
 *
 * @author Lee Richards
 * @version Sprint3
 */
public class GuiCoords {
  private Double x;
  private Double y;

  public GuiCoords(Double x, Double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Get X coordinate
   *
   * @return x
   */
  public Double getX() {
    return x;
  }

  /**
   * Set x coordinate
   *
   * @param x
   */
  public void setX(Double x) {
    this.x = x;
  }

  /**
   * Get Y coordinate
   *
   * @return y
   */
  public Double getY() {
    return y;
  }

  /**
   * Set y coordinate
   *
   * @param y
   */
  public void setY(Double y) {
    this.y = y;
  }

  /**
   * Move coordinates by distance and in direction given.
   *
   * @param distance
   * @param direction
   * @return
   */
  public GuiCoords moveCoords(Double distance, Direction direction) {
    switch (direction) {
      case DOWN:
        return new GuiCoords(x, y + distance);
      case LEFT:
        return new GuiCoords(x - distance, y);
      case UP:
        return new GuiCoords(x, y - distance);
      case RIGHT:
        return new GuiCoords(x + distance, y);
    }
    return this;
  }
}
