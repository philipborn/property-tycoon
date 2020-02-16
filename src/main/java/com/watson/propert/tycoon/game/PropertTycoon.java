package com.watson.propert.tycoon.game;

import java.util.List;
import java.util.Optional;

public interface PropertTycoon {

  Optional<List<Integer>> throwDices();

  Optional<List<Integer>> getDices();

  List<String> getSquaresNames();
}
