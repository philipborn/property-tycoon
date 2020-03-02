package com.watson.propert.tycoon.game;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

public class GameMaster {

  private Deque<Player> players;

  GameMaster(Collection<Player> players) {
    this.players = new ArrayDeque<>(players);
  }

  public Player currentPlayer() {
    return players.peekFirst();
  }

  public Player newTurn() {
    Player p = players.pop();
    players.addLast(p);
    return p;
  }
}
