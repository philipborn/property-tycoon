package com.watson.propert.tycoon.game.bord;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

import com.watson.propert.tycoon.game.entitys.Bank;
import com.watson.propert.tycoon.game.events.DiceEvent;

public class UtilitiesTest {

  @Test
  void rentIsBasedOnDices() throws InterruptedException {
    int value = 100;
    UtilitiesGroup group = new UtilitiesGroup();
    Utilities utilOne = new Utilities(value, group);
    int factor = 4;

    assertEquals(0, utilOne.getRent());
    group.catchDices(new DiceEvent(1, 1));
    assertEquals((1 + 1) * factor, utilOne.getRent());
    group.catchDices(new DiceEvent(2, 1));
    assertEquals((2 + 1) * factor, utilOne.getRent());
    group.catchDices(new DiceEvent(3, 1));
    assertEquals((3 + 1) * factor, utilOne.getRent());
    group.catchDices(new DiceEvent(1, 3));
    assertEquals((1 + 3) * factor, utilOne.getRent());
    group.catchDices(new DiceEvent(6, 6));
    assertEquals((6 + 6) * factor, utilOne.getRent());
  }

  @Test
  void rentWillIncreasedByFactorWhenOwnerOwensMultiUtilities() {
    int value = 100;
    UtilitiesGroup group = new UtilitiesGroup();
    Utilities utilOne = new Utilities(value, group);
    Utilities utilTwo = new Utilities(value, group);
    Utilities utilThree = new Utilities(value, group);
    Bank bank1 = Bank.instance();
    Bank bank2 = Bank.instance();
    utilOne.newOwner(bank1);
    utilThree.newOwner(bank2);

    int dices = 3 + 2;
    group.catchDices(new DiceEvent(3, 2));

    assertEquals(dices * 4, utilOne.getRent());
    utilTwo.newOwner(bank2);
    assertEquals(dices * 4, utilOne.getRent());
    utilTwo.newOwner(bank1);
    assertEquals(dices * 10, utilOne.getRent());
    utilThree.newOwner(bank1);
    assertEquals(dices * 10, utilOne.getRent());
  }
}
