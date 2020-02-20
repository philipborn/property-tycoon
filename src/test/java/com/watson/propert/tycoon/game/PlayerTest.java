package com.watson.propert.tycoon.game;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class PlayerTest {
  Player player;
  EventBus bus;
  Square first;
  static int bordSize = 3;

  @BeforeEach
  void setup() {
    SquareImp firstNode = new SquareImp("first");
    SquareImp secondNode = new SquareImp("second");
    SquareImp thirdNode = new SquareImp("third");
    firstNode.setNext(secondNode);
    secondNode.setNext(thirdNode);
    thirdNode.setNext(firstNode);
    firstNode.setBack(thirdNode);
    secondNode.setBack(firstNode);
    thirdNode.setBack(secondNode);

    first = firstNode;
    bus = new EventBus();
    player = new Player(first, bus);
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

    assertEquals(player.move(bordSize), first);
    assertEquals(player.move(-bordSize), first);
  }

  @Test
  void movingForeardThenBackSamePostion() {

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

  @Test
  void playerMovePostsEventWhenMove() {
    TestListiner listnier = new TestListiner();
    player.registerForEvents(listnier);

    player.move(3);
    PlayerEvent event = listnier.msgs.get(0);

    assertEquals(1, listnier.msgs.size());
    assertEquals(player, event.player());
    assertEquals(first, event.oldPostion());
  }
}

class TestListiner {

  List<PlayerEvent> msgs = new ArrayList<>();

  @Subscribe
  void collekt(PlayerEvent msg) {
    msgs.add(msg);
  }
}
