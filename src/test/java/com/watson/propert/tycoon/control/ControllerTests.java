package com.watson.propert.tycoon.control;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import com.watson.propert.tycoon.gui.Direction;
import com.watson.propert.tycoon.gui.GuiCoords;

/**
 * JUnit tests for moveCoords Method. Method returns new coordinates move by given distance and
 * direction
 *
 * @author Lee Richards
 * @version Sprint3
 */
class ControllerTests {
  GuiCoords o = new GuiCoords(100.0, 100.0);

  @Test
  void moveCoordinatesTestLeft() {
    GuiCoords m = o.moveCoords(90.0, Direction.LEFT);
    GuiCoords d = new GuiCoords(10.0, 100.0);
    assertEquals(d.getX(), m.getX());
    assertEquals(d.getY(), m.getY());
  }

  @Test
  void moveCoordinatesTestRight() {
    GuiCoords m = o.moveCoords(90.0, Direction.RIGHT);
    GuiCoords d = new GuiCoords(190.0, 100.0);
    assertEquals(d.getX(), m.getX());
    assertEquals(d.getY(), m.getY());
  }

  @Test
  void moveCoordinatesTestDown() {
    GuiCoords m = o.moveCoords(90.0, Direction.DOWN);
    GuiCoords d = new GuiCoords(100.0, 190.0);
    assertEquals(d.getX(), m.getX());
    assertEquals(d.getY(), m.getY());
  }

  @Test
  void moveCoordinatesTestUp() {
    GuiCoords m = o.moveCoords(90.0, Direction.UP);
    GuiCoords d = new GuiCoords(100.0, 10.0);
    assertEquals(d.getX(), m.getX());
    assertEquals(d.getY(), m.getY());
  }
}
