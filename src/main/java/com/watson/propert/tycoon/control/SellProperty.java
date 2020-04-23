package com.watson.propert.tycoon.control;

import com.watson.propert.tycoon.gui.GuiProperty;

/**
 * Class to record property being sold, functionality for selling houses without the square or
 * selling the whole square.
 *
 * @author Tom Doran
 */
public class SellProperty {

  private GuiProperty property;
  private boolean sellingSquare;
  private int housesToRemove;

  public SellProperty(GuiProperty property) {
    this.property = property;
    housesToRemove = 0;
    sellingSquare = false;
  }

  public boolean sellingSquare() {
    return sellingSquare;
  }

  public void setSellingSquare(boolean sellingSquare) {
    this.sellingSquare = sellingSquare;
  }

  public GuiProperty getProperty() {
    return property;
  }

  public int getHousesToRemove() {
    return housesToRemove;
  }

  public void setHousesToRemove(int housesToRemove) {
    this.housesToRemove = housesToRemove;
  }
}
