package com.watson.propert.tycoon.game;

import java.util.Map;

import com.google.common.eventbus.EventBus;

public class BordBuilder {

  private EventBus channel;
  private BordReader source;

  private SquareImp first;
  private SquareImp last;
  private SquareImp current;

  public BordBuilder(EventBus channel) {
    this.channel = channel;
  }

  public Square buildBord(BordReader source) {
    this.source = source;

    source.nextObject();
    first = createSquare();

    while (source.hasNextObject()) {
      source.nextObject();
      current = createSquare();
      link(current);
    }
    linkLastAndFirst();

    return first;
  }

  private SquareImp createSquare() {
    Map<String, String> prop = source.getProperties();
    return new SquareImp(prop.get("name"));
  }

  SquareImp link(SquareImp current) {
    current.setBack(last);
    last.setNext(current);
    last = current;
    return current;
  }

  private void linkLastAndFirst() {
    last.setNext(first);
    first.setBack(last);
  }
}
