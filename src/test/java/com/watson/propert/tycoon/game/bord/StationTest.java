package com.watson.propert.tycoon.game.bord;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import com.google.common.eventbus.EventBus;
import com.watson.propert.tycoon.game.Player;

public class StationTest {

  @Test
  void stationRentIsExponentialToNumberOfOwnedStations() {
    Station station = new Station("test", 100);
    Station station2 = new Station("test2", 100);
    Station station3 = new Station("test3", 100);
    Station station4 = new Station("test4", 100);
    Player player = new Player(Player.Id.ONE, null, null, null);

    BordBuilder.with(new EventBus())
        .addSquare(station)
        .addSquare(station2)
        .addSquare(station3)
        .addSquare(station4)
        .getFirstSquare();

    assertEquals(Station.BASE_RENT, station.getRent());
    station.newOwner(player);
    assertEquals(Station.BASE_RENT, station.getRent());
    station2.newOwner(player);
    assertEquals(Station.BASE_RENT * 2, station.getRent());
    station3.newOwner(player);
    assertEquals(Station.BASE_RENT * 2 * 2, station.getRent());
    station4.newOwner(player);
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
    Station station = new Station("test", 100);
    Station station2 = new Station("test2", 100);
    Station station3 = new Station("test3", 100);
    Station station4 = new Station("test4", 100);
    Player player = new Player(Player.Id.ONE, null, null, null);
    Player playerTwo = new Player(Player.Id.TWO, null, null, null);

    BordBuilder.with(new EventBus())
        .addSquare(station)
        .addSquare(station2)
        .addSquare(station3)
        .addSquare(station4)
        .getFirstSquare();

    station.newOwner(player);
    assertEquals(Station.BASE_RENT, station.getRent());
    assertEquals(Station.BASE_RENT, station2.getRent());
    station2.newOwner(playerTwo);
    assertEquals(Station.BASE_RENT, station.getRent());
    assertEquals(Station.BASE_RENT, station2.getRent());
    station3.newOwner(playerTwo);
    assertEquals(Station.BASE_RENT, station.getRent());
    assertEquals(Station.BASE_RENT * 2, station2.getRent());
  }
}
