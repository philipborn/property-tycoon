package com.watson.propert.tycoon.game.rules;

import java.util.Iterator;

import com.watson.propert.tycoon.game.entitys.CashUser;
import com.watson.propert.tycoon.game.entitys.Player;
import com.watson.propert.tycoon.game.bord.Jail;
import com.watson.propert.tycoon.game.entitys.Jailer;
import com.watson.propert.tycoon.game.bord.Square;
import com.watson.propert.tycoon.game.bord.SquareVisitor;

public class GoToJail {

  private static final int FINE = 50;

  private Player player;
  private Jailer jailer;

  public GoToJail(Player player, Jailer jailer) {
    this.player = player;
    this.jailer = jailer;
  }

  public void movePlayer() {
    JailFinder finder = new JailFinder();
    Square jail = finder.findJail(player.postion());
    player.moveTo(jail);
  }

  public boolean canPayJail() {
    return player.cash() >= FINE;
  }

  public void payJail(CashUser receiver) {
    player.payTo(receiver, FINE);
  }

  public void toJail() {
    jailer.toJail(player);
  }

  public int getFine() {
    return FINE;
  }

  /** Find first jail */
  private class JailFinder implements SquareVisitor {
    boolean found = false;

    public Square findJail(Square start) {
      Iterator<Square> iter = start.iterator();
      Square square = null;
      while (!found && iter.hasNext()) {
        square = iter.next();
        square.visitBy(this);
      }
      return square;
    }

    @Override
    public void areAt(Jail jail) {
      found = true;
    }
  }
}
