package com.watson.propert.tycoon.game.actions;

import com.watson.propert.tycoon.game.bord.Action;
import com.watson.propert.tycoon.game.bord.Square;
import com.watson.propert.tycoon.game.entitys.Player;
import com.watson.propert.tycoon.game.rules.AfterMove;
import com.watson.propert.tycoon.game.rules.Passing;

public class BackwardTo implements Action {

  int toSeqNum;

  public BackwardTo(int toSeqNum) {
    this.toSeqNum = toSeqNum;
  }

  @Override
  public void run(Player player) {
    Square square = player.postion();
    Square newLocation = square.forwardTo(toSeqNum, Passing.rulesFor(player));
    player.moveTo(newLocation);
    AfterMove rule = new AfterMove(player);
    newLocation.visitBy(rule);
  }
}
