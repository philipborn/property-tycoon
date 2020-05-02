package com.watson.propert.tycoon.game.actions;

import com.google.common.eventbus.EventBus;
import com.watson.propert.tycoon.game.bord.Action;
import com.watson.propert.tycoon.game.bord.Square;
import com.watson.propert.tycoon.game.entitys.Player;
import com.watson.propert.tycoon.game.rules.Passing;
import com.watson.propert.tycoon.game.rules.RuleAfterMove;

public class ForwardTo implements Action {

  private int toSeqNum;
  private EventBus channel;

  public ForwardTo(int toSeqNum, EventBus channel) {
    this.toSeqNum = toSeqNum;
    this.channel = channel;
  }

  @Override
  public void run(Player player) {
    Square square = player.postion();
    Square newLocation = square.forwardTo(toSeqNum, Passing.rulesFor(player));
    player.moveTo(newLocation);
    RuleAfterMove rule = new RuleAfterMove(player, channel);
    newLocation.visitBy(rule);
  }
}
