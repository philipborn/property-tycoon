package com.watson.propert.tycoon.game.bord;

import com.watson.propert.tycoon.game.entitys.Player;

/** Action that from Cards and Square need implement this interface */
public interface Action {
  void run(Player player);
}
