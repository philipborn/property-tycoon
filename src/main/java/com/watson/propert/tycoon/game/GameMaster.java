package com.watson.propert.tycoon.game;

import java.util.*;

public class GameMaster {

  private ArrayDeque<Player> players;
  private ArrayDeque<Player> deads;

  GameMaster(Collection<Player> players) {
    this.players = new ArrayDeque<>(players);
    this.deads = new ArrayDeque<>(players.size());
  }

  public Player currentPlayer() {
    return players.peekFirst();
  }

  public Player newTurn() {
    Player p = players.pop();
    players.addLast(p);
    return p;
  }

  public void removePlayer(Player player) {
    players.remove(player);
    deads.addLast(player);
  }

  public List<Player> ranks() {
    List<Player> rank = new ArrayList<>(players);
    rank.sort(new HighestTotalValue());
    rank.addAll(deads);
    return rank;
  }

  private class HighestTotalValue implements Comparator<Player>{

    @Override
    public int compare(Player player, Player other) {
      Integer playerTotal = player.totalValue();
      Integer otherTotal = other.totalValue();
      return  playerTotal.compareTo(otherTotal);
    }
  }
}
