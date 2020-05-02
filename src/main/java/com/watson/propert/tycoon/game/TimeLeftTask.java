package com.watson.propert.tycoon.game;

import java.util.TimerTask;

import com.google.common.eventbus.EventBus;
import com.watson.propert.tycoon.game.events.TimeOutEvent;
import com.watson.propert.tycoon.game.events.TimeTicEvent;

public class TimeLeftTask extends TimerTask {

  private EventBus channel;
  private int secondsLeft;

  public TimeLeftTask(EventBus channel, int secondsToEnd) {
    this.channel = channel;
    this.secondsLeft = secondsToEnd;
  }

  @Override
  public void run() {
    --secondsLeft;
    channel.post(new TimeTicEvent(secondsLeft));
    if (secondsLeft == 0) {
      channel.post(TimeOutEvent.INSTANCE);
      cancel();
    }
  }
}
