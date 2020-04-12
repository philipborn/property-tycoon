package com.watson.propert.tycoon.game.bord;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.google.common.eventbus.Subscribe;
import com.watson.propert.tycoon.game.events.DiceEvent;

public class UtilitiesGroup implements Group {

  private Set<Utilities> members = new HashSet<>();
  private int lastDices;

  public int numOfOwned(Owner owner) {
    return (int)
        members
            .stream()
            .map(Utilities::owner)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .filter((owner1 -> owner1.equals(owner)))
            .count();
  }

  public int size() {
    return members.size();
  }

  public void add(Utilities utilities) {
    members.add(utilities);
  }

  public int getLastDices() {
    return lastDices;
  }

  @Subscribe
  public void catchDices(DiceEvent event) {
    lastDices = event.firstDice() + event.secondDice();
  }
}
