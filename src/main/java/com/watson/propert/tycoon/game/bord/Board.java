package com.watson.propert.tycoon.game.bord;

public class Board {

  private Go go;
  private Jail jail;
  private FreePark freePark;

  public Go getGo() {
    return go;
  }

  public Jailer getJailer() {
    return jail;
  }

  public FreePark getFreePark() {
    return freePark;
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
}
