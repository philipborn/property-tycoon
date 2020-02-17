package com.watson.propert.tycoon.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameMaster implements PropertTycoon {

  private DicePair dicePair;
  private Square bord;
  private Player token = new Player();

  public GameMaster(Square startPostion) {
    dicePair = new DicePair();
    bord = startPostion;
  }

  @Override
  public List<Integer> throwDices() {
    List<Integer> dices = dicePair.throwDices();
    Integer sum = dices.stream().mapToInt((a)->a).sum();
    token.move(sum);
    return dices;
  }

  private sum(List<Intger>)

  @Override
  public Optional<List<Integer>> getDices() {
    return dicePair.lastThrow();
  }

  @Override
  public List<String> getSquaresNames() {
    List<String> names = new ArrayList<>(40);
    for (Square s : bord) {
      names.add(s.name());
    }
    return names;
  }

  @Override
  public int GetPlayerPostion() {
    return findNumOf(token.postion());
  }

  private int findNumOf(Square square) {
    int post = 0;
    for (Sqaure s: bord) {
      if(square.equals((Object)square))) {
        break;
      }
      ++post;
    }
    return post;
  }
}
