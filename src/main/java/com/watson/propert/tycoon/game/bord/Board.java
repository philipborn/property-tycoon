package com.watson.propert.tycoon.game.bord;

import com.watson.propert.tycoon.game.entitys.Jailer;

public class Board {

  private Go go;
  private Jail jail;
  private FreePark freePark;
  private Square start;

  public Go getGo() {
    return go;
  }

  public Jailer getJailer() {
    return jail;
  }

  public FreePark getFreePark() {
    return freePark;
  }

  public Square getStart() {
    return start;
  }

  protected void setGo(Go go) {
    if (this.go != null) {
      throw new RuntimeException("Only one Go on a Bord");
    }
    this.go = go;
  }

  protected void setJailer(Jail jailer) {
    if (go == null) {
      throw new RuntimeException("First square most be group Go");
    }
    this.jail = jailer;
  }

  protected void setFreePark(FreePark freePark) {
    if (go == null) {
      throw new RuntimeException("First square most be group Go");
    }
    this.freePark = freePark;
  }

  protected void setStart(Square start) {
    this.start = start;
  }
}
