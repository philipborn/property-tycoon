package com.watson.propert.tycoon.game;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.*;

public class DicePairTest {

  DicePair dicePair;

  @BeforeEach
  void setup() {
    dicePair = new DicePair();
  }

  @Test
  void diceReturnTwoVauls() {
    List<Integer> returned = dicePair.throwDices();

    assertEquals(2, returned.size());
  }

  @Test
  void lastThrowGiveExceptionIfNotThrownBefore() {
    assertThrows(DiceNotThrownError.class, () -> dicePair.lastThrow());
    assertDoesNotThrow(
        () -> {
          dicePair.throwDices();
          dicePair.lastThrow();
        });
  }

  @Test
  void ModifiedOfReturnListDoNotChangeThrowDicesList() {
    List<Integer> first = dicePair.throwDices();

    first.set(0, 7);
    first.add(3);

    assertNotEquals(first, dicePair.lastThrow());
    assertNotEquals(dicePair.lastThrow().get(0), 7);
  }
}
