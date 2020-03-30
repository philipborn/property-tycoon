package com.watson.propert.tycoon.game;

import java.util.List;

import com.google.common.eventbus.EventBus;

public class NewTurnState implements GameState {

  GameMaster master;
  DicePair dicePair;
  EventBus channel;

  NewTurnState(GameMaster master, EventBus channel) {
    this.master = master;
    this.channel = channel;
  }

  @Override
  public void entry() {
    channel.post(new ChangePlayerEvent(master.newTurn().id));
  }

  @Override
  public GameState throwDicesAndMove() {
    dicePair = new DicePair(channel);

    Player currentPlayer = master.currentPlayer();
    List<Integer> dices = dicePair.throwDices();
    Integer sum = dices.stream().mapToInt((a) -> a).sum();
    currentPlayer.move(sum);

    if (currentPlayer.postion() instanceof Property) {
      return switchTo(new NonOwnerState(master, channel));
    } else {
      return switchTo(new FixPropertyState(master, channel));
    }
  }
}
