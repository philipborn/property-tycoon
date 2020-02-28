package com.watson.propert.tycoon.game;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import com.google.common.eventbus.EventBus;

public class GameMasterTest {

  GameMaster master;

  List<Player> players;
  Square square;
  EventBus channel;

  @BeforeEach
  void setup() {
    square = new SquareImp();
    channel = new EventBus();

    players = new ArrayList<>(6);
    players.add(new Player(PlayerId.ONE, square, channel));
    players.add(new Player(PlayerId.TWO, square, channel));
    master = new GameMaster(players);
  }

  @Test
  void calling_currentPlayer_give_same_result() {
    Player firstP = master.currentPlayer();
    Player secondP = master.currentPlayer();

    assertTrue(players.contains(firstP));
    assertEquals(firstP, secondP);
  }

  @Test
  void gameMaster_behavis_as_ciruler_que() {
    Player firstP = master.newTurn();
    master.newTurn();
    Player thirdP = master.newTurn();

    assertTrue(players.contains(firstP));
    assertEquals(firstP, thirdP);
  }
}
