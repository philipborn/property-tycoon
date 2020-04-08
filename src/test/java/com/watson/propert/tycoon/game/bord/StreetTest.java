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
    Street street2 = new Street("test2", 150, Street.Colour.BLUE, rents2);
    Street street =
        (Street)
            BordBuilder.with(new EventBus())
                .addStreet("test", 100, Street.Colour.BLUE, rents)
                .addSquare(street2)
                .getBord();
    Owner owner = new TestOwner();
    street.newOwner(owner);
    street2.newOwner(owner);

    assertEquals(0, street.getNumHouse());
    assertEquals(rents.get(0) * 2, street.getRent());
    street.buyHouses();
    assertEquals(1, street.getNumHouse());
    assertEquals(rents.get(1), street.getRent());
    street2.buyHouses();
    street.buyHouses();
    street2.buyHouses();
    street.buyHouses();
    assertEquals(3, street.getNumHouse());
    assertEquals(rents.get(3), street.getRent());
  }

  @Test
  void ifPlayerOwensAllSameColorRentIsDoubledForNoHouse() {
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
    Street second = (Street) street.step(1);
    second.newOwner(owner);
    assertEquals(value * 2, street.getRent());
    street.buyHouses();
    assertEquals(rents.get(1), street.getRent());
  }

  @Test
  void tryAddToManyHouseThrowIllegalConstructionException() {
    List<Integer> rents = new ArrayList<>(Arrays.asList(10, 100, 200, 300));
    Street street =
        (Street)
            BordBuilder.with(new EventBus())
                .addStreet("test", 100, Street.Colour.RED, rents)
                .getBord();
    street.newOwner(new TestOwner());

    assertDoesNotThrow(street::buyHouses);
    assertDoesNotThrow(street::buyHouses);
    assertDoesNotThrow(street::buyHouses);
    assertThrows(IllegalConstructionException.class, street::buyHouses);
  }

  @Test
  void tryToRemoveTOManyHouseThrowsIllegalConstructionException() {
    List<Integer> rents = new ArrayList<>(Arrays.asList(10, 100, 200, 300));
    Street street =
        (Street)
            BordBuilder.with(new EventBus())
                .addStreet("test", 100, Street.Colour.RED, rents)
                .getBord();
    street.newOwner(new TestOwner());

    assertDoesNotThrow(street::buyHouses);
    assertDoesNotThrow(street::buyHouses);
    assertDoesNotThrow(street::buyHouses);
    assertDoesNotThrow(street::sellHouse);
    assertDoesNotThrow(street::sellHouse);
    assertDoesNotThrow(street::sellHouse);
    assertThrows(IllegalConstructionException.class, street::sellHouse);
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
