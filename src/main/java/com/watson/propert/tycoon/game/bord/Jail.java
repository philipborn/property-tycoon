package com.watson.propert.tycoon.game.bord;

import java.util.HashMap;
import java.util.Map;

import com.watson.propert.tycoon.game.entitys.Jailer;
import com.watson.propert.tycoon.game.entitys.Prisonable;

public class Jail implements Jailer, SquareTyp {

  private static final int jailTime = 2;
  private Map<Prisonable, Integer> jailRecord = new HashMap<>();

  @Override
  public void toJail(Prisonable prison) {
    jailRecord.put(prison, jailTime);
  }

  @Override
  public boolean isInJail(Prisonable prisonable) {
    int timeLeft = jailRecord.getOrDefault(prisonable, 0);
    return timeLeft > 0;
  }

  @Override
  public void decrementJailTime(Prisonable prison) {
    jailRecord.computeIfPresent(prison, ((prisonable, integer) -> --integer));
  }

  @Override
  public void visitBy(SquareVisitor visitor) {
    visitor.areAt(this);
  }
}
