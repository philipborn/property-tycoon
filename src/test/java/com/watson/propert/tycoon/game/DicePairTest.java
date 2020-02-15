package com.watson.propert.tycoon.game;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.*;

public class DicePairTest {

  DicePair dicePair;

  @BeforeEach
  void setup() {
    dicePair = new DicePair();
  }

  @Test
  void diceReturnTwoVauls() {
    List<Integer> returned = dicePair.throwDices().orElseThrow();

    assertEquals(2, returned.size());
  }

  @Test
  void lastThrowGiveEmptyOptionalIfNotThrownBefore() {
    assertEquals(dicePair.lastThrow(), Optional.empty());
    dicePair.throwDices();
    assertNotEquals(dicePair.lastThrow(), Optional.empty());
  }

  @Test
  void ModifiedOfReturnListDoNotChangeThrowDicesList() {
    List<Integer> first = dicePair.throwDices().orElseThrow();

    first.set(0, 7);
    first.add(3);

    List<Integer> returned = dicePair.lastThrow().orElseThrow();
    assertNotEquals(first, returned);
    assertNotEquals(returned.get(0), 7);
  }
}
