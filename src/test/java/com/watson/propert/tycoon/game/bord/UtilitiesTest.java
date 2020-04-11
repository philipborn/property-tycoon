package com.watson.propert.tycoon.game.bord;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

import com.google.common.eventbus.EventBus;
import com.watson.propert.tycoon.game.BankAccount;
import com.watson.propert.tycoon.game.Player;
import com.watson.propert.tycoon.game.events.DiceEvent;

public class UtilitiesTest {

  @Test
  void rentIsBasedOnDices() throws InterruptedException {
    int value = 100;
    UtilitiesGroup group = new UtilitiesGroup();
    Utilities utilOne = new Utilities("test1", value, group);
    int factor = 4;

    BordBuilder.with(new EventBus()).addSquare(utilOne).getFirstSquare();

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
    Utilities utilOne = new Utilities("test1", value, group);
    Utilities utilTwo = new Utilities("test1", value, group);
    Utilities utilThree = new Utilities("test1", value, group);
    Player playerOne = new Player(Player.Id.ONE, new BankAccount(), utilOne, null);
    Player playerTwo = new Player(Player.Id.TWO, new BankAccount(), utilOne, null);
    utilOne.newOwner(playerOne);
    utilThree.newOwner(playerTwo);

    int dices = 3 + 2;
    group.catchDices(new DiceEvent(3, 2));

    assertEquals(dices * 4, utilOne.getRent());
    utilTwo.newOwner(playerTwo);
    assertEquals(dices * 4, utilOne.getRent());
    utilTwo.newOwner(playerOne);
    assertEquals(dices * 10, utilOne.getRent());
    utilThree.newOwner(playerOne);
    assertEquals(dices * 10, utilOne.getRent());
  }
}
