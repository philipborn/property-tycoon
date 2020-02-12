package com.watson.propert.tycoon.game;

import static org.junit.jupiter.api.Assertions.*;

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
}
