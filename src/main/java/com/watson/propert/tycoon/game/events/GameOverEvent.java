package com.watson.propert.tycoon.game.events;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.watson.propert.tycoon.game.entitys.Player;

public class GameOverEvent {

  public final ImmutableList<Player.Id> ranking;

  public GameOverEvent(List<Player.Id> ranking) {
    this.ranking = ImmutableList.copyOf(ranking);
  }
}
