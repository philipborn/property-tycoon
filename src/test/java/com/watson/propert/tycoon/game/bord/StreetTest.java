package com.watson.propert.tycoon.game.bord;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.*;

import com.google.common.eventbus.EventBus;
import com.watson.propert.tycoon.game.CashUser;
import com.watson.propert.tycoon.game.Owner;

public class StreetTest {

  @Test
  void RentDependentofNumHouses() {
    List<Integer> rents = new ArrayList<>(Arrays.asList(10, 21, 32, 45));
    List<Integer> rents2 = new ArrayList<>(Arrays.asList(15, 24, 62, 105));
    Street street =
        (Street)
            BordBuilder.with(new EventBus())
                .addStreet("test", 100, Street.Colour.BLUE, rents)
                .addStreet("test2", 150, Street.Colour.BLUE, rents2)
                .getBord();
    street.newOwner(new TestOwner());

    assertEquals(0, street.getNumHouse());
    assertEquals(rents.get(0), street.getRent());
    street.buyHouses(1);
    assertEquals(1, street.getNumHouse());
    assertEquals(rents.get(1), street.getRent());
    street.buyHouses(2);
    assertEquals(3, street.getNumHouse());
    assertEquals(rents.get(3), street.getRent());
  }

  @Test
  void ifPlayerOwensAllSameColorRentIsDoubled() {
    int value = 10;
    List<Integer> rents = new ArrayList<>(Arrays.asList(value, 21, 32, 45));
    List<Integer> rents2 = new ArrayList<>(Arrays.asList(15, 24, 62, 105));
    Street street =
        (Street)
            BordBuilder.with(new EventBus())
                .addStreet("test", 100, Street.Colour.BLUE, rents)
                .addStreet("test2", 150, Street.Colour.BLUE, rents2)
                .getBord();
    Owner owner = new TestOwner();
    street.newOwner(owner);

    assertEquals(0, street.getNumHouse());
    assertEquals(value, street.getRent());
    Street second = (Street) street.move(1);
    second.newOwner(owner);
    assertEquals(value * 2, street.getRent());
  }

  @Test
  void tryAddToManyHouseThrowRuntimeException() {
    List<Integer> rents = new ArrayList<>(Arrays.asList(10, 100, 200, 300));
    Street street = new Street("test", 100, Street.Colour.RED, rents);
    street.newOwner(new TestOwner());

    assertDoesNotThrow(() -> street.buyHouses(rents.size() - 1));
    assertThrows(RuntimeException.class, () -> street.buyHouses(1));
  }

  @Test
  void tryToRemoveTOManyHouseThrowsRuntimeException() {
    List<Integer> rents = new ArrayList<>(Arrays.asList(10, 100, 200, 300));
    Street street = new Street("test", 100, Street.Colour.RED, rents);
    street.newOwner(new TestOwner());
    int change = rents.size() - 1;

    assertDoesNotThrow(() -> street.buyHouses(change));
    assertDoesNotThrow(() -> street.sellHouses(change));
    assertThrows(RuntimeException.class, () -> street.sellHouses(1));
  }

  private class TestOwner implements Owner {

    @Override
    public int cash() {
      return 0;
    }

    @Override
    public void receiveCash(int amount) {}

    @Override
    public void payTo(CashUser cashUser, int amount) {}
  }
}
