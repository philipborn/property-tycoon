package com.watson.propert.tycoon.game.events;

import com.watson.propert.tycoon.game.Movement;

public class PlayerMovedEvent {

  public final int newPost;
  public final int oldPost;
  public final Movement typ;

  public PlayerMovedEvent(int newPost, int oldPost, Movement typ) {
    this.newPost = newPost;
    this.oldPost = oldPost;
    this.typ = typ;
  }
}
