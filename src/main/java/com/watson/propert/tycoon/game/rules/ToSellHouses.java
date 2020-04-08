package com.watson.propert.tycoon.game.rules;

import com.watson.propert.tycoon.game.Player;
import com.watson.propert.tycoon.game.bord.*;

public class ToSellHouses implements SquareVisitor {

  private Player player;

  public ToSellHouses(Player player) {
    this.player = player;
  }

  public void sellHouses(Square square) {
    square.vist(this);
  }

  @Override
  public void street(Street street) {
    street.owner().filter((owner) -> owner.equals(player)).ifPresent((owner) -> street.sellHouse());
  }
}
