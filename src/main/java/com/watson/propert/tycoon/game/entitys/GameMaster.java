package com.watson.propert.tycoon.game.entitys;

import java.util.*;

import com.google.common.collect.ImmutableList;

public class GameMaster {

  private List<Player> players;
  private ArrayDeque<Player> round;
  private ArrayDeque<Player> deads;

  public Player currentPlayer() {
    return round.peekFirst();
  }

  public Player newTurn() {
    Player currentPlayer = round.pop();
    if (round.isEmpty()) {
      round.addAll(players);
    }
    return currentPlayer;
  }

  public void removePlayer(Player player) {
    players.remove(player);
    round.remove(player);
    deads.addLast(player);
  }

  public boolean newTurnWillStartNewRound() {
    return round.size() == 0;
  }

  public int numActivePlayers() {
    return players.size();
  }

  public ImmutableList<Player> getPlayers() {
    return ImmutableList.copyOf(players);
  }

  public List<Player> theRanking() {
    List<Player> rank = new ArrayList<>(players);
    rank.sort(new HighestTotalValue());
    rank.addAll(deads);
    return rank;
  }

  public void newGame(List<Player> players) {
    List<Player> temp = new ArrayList<>(players);
    this.players = new ArrayList<>(temp);
    this.round = new ArrayDeque<>(temp);
    this.deads = new ArrayDeque<>(players.size());
  }

  private class HighestTotalValue implements Comparator<Player> {

    @Override
    public int compare(Player player, Player other) {
      Integer playerTotal = player.totalValue();
      Integer otherTotal = other.totalValue();
      return playerTotal.compareTo(otherTotal);
    }
  }
}
