package com.watson.propert.tycoon.game.rules;

import com.watson.propert.tycoon.game.Player;
import com.watson.propert.tycoon.game.bord.*;

public class ToSellHouses implements SquareVisitor {

  private Player player;
  private int houseToSell;

  public ToSellHouses(Player player) {
    this.player = player;
  }

  public void sellHouses(Square square, int houseToSell) {
    this.houseToSell = houseToSell;
    square.vist(this);
  }

  @Override
  public void street(Street street) {
    street.sellHouses(houseToSell);
  }
}
