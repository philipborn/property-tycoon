package com.watson.propert.tycoon.game.bord;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class StationGroup implements Group {

  public static final int BASE_RENT = 25;

  Set<Station> members = new HashSet<>();

  public int numOfOwned(Owner owner) {
    return (int)
        members
            .stream()
            .map(Station::owner)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .filter((owner1 -> owner1.equals(owner)))
            .count();
  }

  public int size() {
    return members.size();
  }

  public void add(Station station) {
    members.add(station);
  }
}
