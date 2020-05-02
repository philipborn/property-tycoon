package com.watson.propert.tycoon.game;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.watson.propert.tycoon.game.bord.*;
import com.watson.propert.tycoon.game.entitys.Player;

public class PlayerInfo {

  private Player.Id id;
  private int cash;
  private int totalValue;
  private ImmutableSet<Integer> owens;

  PlayerInfo(Player player) {
    this.id = player.getId();
    this.cash = player.cash();
    this.totalValue = player.totalValue();
    this.owens = getProperties(player);
  }

  private ImmutableSet<Integer> getProperties(Player player) {
    return ImmutableSet.copyOf(new PropertyRecord(player).getProperties());
  }

  public Player.Id getId() {
    return id;
  }

  public int getCash() {
    return cash;
  }

  public int getTotalValue() {
    return totalValue;
  }

  public ImmutableSet<Integer> getProperties() {
    return owens;
  }

  static class PropertyRecord implements SquareVisitor {

    private Player player;
    private Square square;
    private Set<Integer> props;

    PropertyRecord(Player player) {
      this.player = player;
    }

    Set<Integer> getProperties() {
      props = new HashSet<>();
      player.postion().forEach(this::checkSquare);
      return props;
    }

    private void checkSquare(Square square) {
      this.square = square;
      square.visitBy(this);
    }

    @Override
    public void areAt(Street street) {
      street
          .owner()
          .filter((owner -> owner.equals(player)))
          .ifPresent((owner -> props.add(square.getNumber())));
    }

    @Override
    public void areAt(Station station) {
      station
          .owner()
          .filter((owner -> owner.equals(player)))
          .ifPresent((owner -> props.add(square.getNumber())));
    }

    @Override
    public void areAt(Utilities utilities) {
      utilities
          .owner()
          .filter((owner -> owner.equals(player)))
          .ifPresent((owner -> props.add(square.getNumber())));
    }
  }
}
