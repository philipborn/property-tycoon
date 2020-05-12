package com.watson.propert.tycoon.gui;

import static java.lang.Math.abs;

import javafx.scene.image.Image;

/**
 * GUI Dice class
 *
 * @author Lee Richards
 * @version Sprint3
 */
public class GuiDice {
  Image[] diceFace = new Image[6];

  /** Construct a new GuiDice */
  public GuiDice() {
    this.initDiceFaces();
  }

  /*
   * Load dice faces from resources
   */
  private void initDiceFaces() {
    for (int i = 0; i < 6; i++) {
      diceFace[i] =
          new Image(ClassLoader.getSystemResourceAsStream("dice/dice" + (i + 1) + ".png"));
    }
  }

  /**
   * Returns a JavaFX Image representing a dice face
   *
   * @param i The number of the dice side required
   * @return Dice Image
   */
  public Image getDiceFace(int i) {
    i = abs(i - 1) % 6;
    return diceFace[i];
  }
}
