package com.watson.propert.tycoon.game.actions;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.watson.propert.tycoon.game.bord.Action;
import com.watson.propert.tycoon.game.bord.Owner;
import com.watson.propert.tycoon.game.bord.SquareVisitor;
import com.watson.propert.tycoon.game.bord.Street;
import com.watson.propert.tycoon.game.entitys.CashUser;
import com.watson.propert.tycoon.game.entitys.Player;

public class PlayerPayRepairs implements Action {

  CashUser receiver;
  int perHouse;
  int perHotel;

  @Override
  public void run(Player player) {
    HouseCounter counter = new HouseCounter(player);
    player.postion().forEach((square -> square.visitBy(counter)));
    Multiset<Integer> result = counter.getCounter();
    int toPay = result.stream().mapToInt((integer -> integer == 5 ? perHotel : perHouse)).sum();
    player.payTo(receiver, toPay);
  }

  private static class HouseCounter implements SquareVisitor {

    private Owner owner;
    private Multiset<Integer> counter = HashMultiset.create();

    public HouseCounter(Owner owner) {
      this.owner = owner;
    }

    public Multiset<Integer> getCounter() {
      return counter;
    }

    @Override
    public void areAt(Street street) {
      street
          .owner()
          .filter((owner -> owner.equals(owner)))
          .ifPresent((owner -> counter.add(street.getNumHouse())));
    }
  }
}
