package com.watson.propert.tycoon.game;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Optional;

import com.watson.propert.tycoon.game.entitys.Player;

public class GameSetting {

  private int secondsToEnd = 0;
  private EnumSet<Player.Id> players = EnumSet.noneOf(Player.Id.class);
  private EnumMap<Player.Id, String> ai = new EnumMap<>(Player.Id.class);

  public void setSecondsToEnd(int secondsToEnd) {
    this.secondsToEnd = secondsToEnd;
  }

  public int getSecondsToEnd() {
    return secondsToEnd;
  }

  public void set(Player.Id id) {
    players.add(id);
    ai.remove(id);
  }

  public void set(Player.Id id, String aiTyp) {
    players.add(id);
    ai.put(id, aiTyp);
  }

  public void remove(Player.Id id) {
    players.remove(id);
    ai.remove(id);
  }

  public EnumSet<Player.Id> getPlayers() {
    return EnumSet.copyOf(players);
  }

  public Optional<String> getAiTyp(Player.Id id) {
    return Optional.ofNullable(ai.get(id));
  }
}
