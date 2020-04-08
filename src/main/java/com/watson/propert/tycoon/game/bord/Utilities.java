package com.watson.propert.tycoon.game.bord;

import java.util.Iterator;

import com.google.common.eventbus.Subscribe;
import com.watson.propert.tycoon.game.events.DiceEvent;

public class Utilities extends Property {

  private int lastDices = 0;

  public Utilities(String name, int amount) {
    super(name, amount);
  }

  @Subscribe
  public void catchDices(DiceEvent event) {
    lastDices = event.firstDice() + event.secondDice();
  }

  public Iterator utilitiesIter() {
    return new UtilitiesIterator(this);
  }

  @Override
  public int getRent() {
    int owned = numberOwnedUtilities();
    int factor = owned > 1 ? 10 : 4;
    return factor * lastDices;
  }

  private int numberOwnedUtilities() {
    if (this.owner().isEmpty()) {
      return 0;
    }
    int count = 0;
    for (Iterator<Utilities> it = utilitiesIter(); it.hasNext(); ) {
      Utilities utilities = it.next();
      if (utilities.owner().equals(this.owner())) {
        count++;
      }
    }
    return count;
  }

  @Override
  public void visitBy(SquareVisitor visitor) {
    visitor.utilities(this);
  }
}
