package com.watson.propert.tycoon.game.actions;

import com.watson.propert.tycoon.game.bord.Action;
import com.watson.propert.tycoon.game.entitys.Player;

public class PlayerSteps implements Action {

  private final int steps;

  protected PlayerSteps(int steps) {
    this.steps = steps;
  }

  @Override
  public void run(Player player) {
    player.move(steps);
  }
}
