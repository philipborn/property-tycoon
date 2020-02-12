package com.watson.propert.tycoon.game;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

public class PlayerTest {
  Player player;

  @BeforeEach
  void setup() {
    player = new Player();
  }

  @Test
  void newplayerNameNeverReturnNull() {
    // setup gives new player

    assertNotNull(player.getName());
  }

  @Test
  void changeNameToNullNullpointException() {
    assertThrows(
        NullPointerException.class,
        () -> {
          player.changeName(null);
        });
  }

  @Test
  void bordeIsCirular() {
    int bordSize = 3;
    Square first = bordOf(bordSize);
    player = new Player(first);

    assertEquals(player.move(bordSize), first);
    assertEquals(player.move(-bordSize), first);
  }

  @Test
  void movingForeardThenBackSamePostion() {
    Square first = bordOf(5);
    player = new Player(first);

    player.move(2);
    player.move(-2);

    assertEquals(player.postion(), first);
  }

  private Square bordOf(int numSquares) {
    List<SquareImp> list = new ArrayList<>(numSquares);
    for (int i = 0; i < numSquares; ++i) {
      SquareImp node = new SquareImp();
      list.add(node);
      if (i != 0) {
        SquareImp nodeBefor = list.get(i - 1);
        node.setBack(nodeBefor);
        nodeBefor.setNext(node);
      }
    }
    SquareImp lastNode = list.get(numSquares - 1);
    SquareImp firstNode = list.get(0);
    firstNode.setBack(lastNode);
    lastNode.setNext(firstNode);
    return firstNode;
  }
}
