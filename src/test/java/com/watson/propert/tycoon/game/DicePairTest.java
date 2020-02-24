package com.watson.propert.tycoon.game;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.*;

import com.google.common.eventbus.EventBus;

public class DicePairTest {

  EventBus channle;
  DicePair dicePair;

  @BeforeEach
  void setup() {
    channle = new EventBus();
    dicePair = new DicePair(channle);
  }

  @Test
  void diceReturnTwoVauls() {
    List<Integer> returned = dicePair.throwDices();

    assertEquals(2, returned.size());
  }
}
