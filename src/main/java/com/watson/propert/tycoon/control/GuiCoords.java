package com.watson.propert.tycoon.control;

/**
 * GUI Coordinates Class A simple data structure to represent X and Y coordinates.
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
}
