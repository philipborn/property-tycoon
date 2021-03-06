package com.watson.propert.tycoon.game;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import com.google.common.eventbus.EventBus;
import com.watson.propert.tycoon.game.bord.Square;
import com.watson.propert.tycoon.game.bord.SquareNode;
import com.watson.propert.tycoon.game.entitys.BankAccount;
import com.watson.propert.tycoon.game.entitys.GameMaster;
import com.watson.propert.tycoon.game.entitys.Player;

public class GameMasterTest {

  GameMaster master;

  List<Player> players;
  Square square;
  EventBus channel;

  @BeforeEach
  void setup() {
    square = new SquareNode(1, "Test");
    channel = new EventBus();

    int cash = 10000;
    players = new ArrayList<>(6);
    players.add(new Player(Player.Id.ONE, new BankAccount(cash), square, channel));
    players.add(new Player(Player.Id.TWO, new BankAccount(cash), square, channel));
    master = new GameMaster();
    master.newGame(players);
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
