package com.watson.propert.tycoon.game.actions;

import com.watson.propert.tycoon.game.bord.Action;
import com.watson.propert.tycoon.game.entitys.JailException;
import com.watson.propert.tycoon.game.entitys.Player;

public class PlayerToJail implements Action {

  @Override
  public void run(Player player) {
    throw new JailException();
  }
}
