package com.watson.propert.tycoon.game.rules;

import com.watson.propert.tycoon.game.*;
import com.watson.propert.tycoon.game.bord.SquareVisitor;
import com.watson.propert.tycoon.game.bord.Station;
import com.watson.propert.tycoon.game.bord.Street;
import com.watson.propert.tycoon.game.bord.Utilities;

public class Bankruptcy implements SquareVisitor {
  private Player player;
  private GameMaster master;

  public Bankruptcy(GameMaster master) {
    this.master = master;
  }

  public void forPlayer(Player player) {
    this.player = player;
    player.postion().forEach((square) -> square.vist(this));
    player.payTo(Bank.instance(), player.cash());
    master.removePlayer(player);
  }

  @Override
  public void street(Street street) {
    street.owner().filter((owner) -> owner.equals(player)).ifPresent((owner) -> street.sell());
  }

  @Override
  public void station(Station station) {
    station.owner().filter((owner) -> owner.equals(player)).ifPresent((owner) -> station.sell());
  }

  @Override
  public void utilities(Utilities utilities) {
    utilities
        .owner()
        .filter((owner) -> owner.equals(player))
        .ifPresent((owner) -> utilities.sell());
  }
}
