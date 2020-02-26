package com.watson.propert.tycoon.game;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import org.junit.jupiter.api.*;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.watson.propert.tycoon.io.BoardReaderJson;

public class PlayerTest {
  Player player;
  EventBus bus;
  Square first;
  static int bordSize = 3;

  @BeforeEach
  void setup() {
    EventBus channel = new EventBus();
    BordBuilder bb = new BordBuilder(channel);
    BoardReaderJson br = new BoardReaderJson();
    br.readFile("src/test/testResources/jsonTest.json");

    first = bb.buildBord(br);

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
