package com.watson.propert.tycoon.game.events;

public class PlayerMovedEvent {

  public final int newPost;
  public final int oldPost;

  public PlayerMovedEvent(int newPost, int oldPost) {
    this.newPost = newPost;
    this.oldPost = oldPost;
  }
}
