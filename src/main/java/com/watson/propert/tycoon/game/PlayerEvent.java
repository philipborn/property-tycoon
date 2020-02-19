package com.watson.propert.tycoon.game;

public class PlayerEvent {

  private Player from;
  private Square oldPost;

  public PlayerEvent(Player from, Square oldPostion) {
    this.from = from;
  }

  public Player player() {
    return from;
  }

  public Square oldPostion() {
    return oldPost;
  }
}
