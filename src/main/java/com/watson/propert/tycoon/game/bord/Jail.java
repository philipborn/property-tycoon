package com.watson.propert.tycoon.game.bord;

import java.util.HashMap;
import java.util.Map;

public class Jail extends SquareAbstract implements Jailer {

  private static final int jailTime = 2;
  private Map<Prisonable,Integer> jailRecord = new HashMap<>();

  public Jail(String name) {
    super(name);
  }

  @Override
  public void visitBy(SquareVisitor visitor) {}

  @Override
  public void toJail(Prisonable prison) {
    jailRecord.put(prison,jailTime);
  }

  @Override
  public boolean isInJail(Prisonable prisonable) {
    int timeLeft = jailRecord.getOrDefault(prisonable, 0);
    return timeLeft > 0;
  }

  @Override
  public void decrementJailTime(Prisonable prison) {
    jailRecord.computeIfPresent(prison,((prisonable, integer) -> --integer));
  }
}
