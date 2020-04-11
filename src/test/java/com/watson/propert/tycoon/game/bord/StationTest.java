package com.watson.propert.tycoon.game.bord;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import com.watson.propert.tycoon.game.Bank;

public class StationTest {

  @Test
  void stationRentIsExponentialToNumberOfOwnedStations() {
    StationGroup group = new StationGroup();
    Station station = new Station("test", 100, group);
    Station station2 = new Station("test2", 100, group);
    Station station3 = new Station("test3", 100, group);
    Station station4 = new Station("test4", 100, group);
    Bank bank = Bank.instance();

    assertEquals(Station.BASE_RENT, station.getRent());
    station.newOwner(bank);
    assertEquals(Station.BASE_RENT, station.getRent());
    station2.newOwner(bank);
    assertEquals(Station.BASE_RENT * 2, station.getRent());
    station3.newOwner(bank);
    assertEquals(Station.BASE_RENT * 2 * 2, station.getRent());
    station4.newOwner(bank);
    assertEquals(Station.BASE_RENT * 2 * 2 * 2, station.getRent());
    station2.sell();
    assertEquals(Station.BASE_RENT * 2 * 2, station3.getRent());
    station.sell();
    assertEquals(Station.BASE_RENT * 2, station3.getRent());
    station4.sell();
    assertEquals(Station.BASE_RENT, station3.getRent());
  }

  @Test
  void rentDontChangeIfOtherPlayerBuy() {
    StationGroup group = new StationGroup();
    Station station = new Station("test", 100, group);
    Station station2 = new Station("test2", 100, group);
    Station station3 = new Station("test3", 100, group);
    Station station4 = new Station("test4", 100, group);
    Bank bank = Bank.instance();
    Bank bank1 = Bank.instance();

    station.newOwner(bank);
    assertEquals(Station.BASE_RENT, station.getRent());
    assertEquals(Station.BASE_RENT, station2.getRent());
    station2.newOwner(bank1);
    assertEquals(Station.BASE_RENT, station.getRent());
    assertEquals(Station.BASE_RENT, station2.getRent());
    station3.newOwner(bank1);
    assertEquals(Station.BASE_RENT, station.getRent());
    assertEquals(Station.BASE_RENT * 2, station2.getRent());
  }
}