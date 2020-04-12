package com.watson.propert.tycoon.game;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.watson.propert.tycoon.game.entitys.BankAccount;
import com.watson.propert.tycoon.game.entitys.Player;
import org.junit.jupiter.api.*;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.watson.propert.tycoon.game.bord.BoardSource;
import com.watson.propert.tycoon.game.bord.BordBuilder;
import com.watson.propert.tycoon.game.bord.Square;
import com.watson.propert.tycoon.game.events.CashEvent;
import com.watson.propert.tycoon.io.BoardReaderJson;

public class PlayerTest {
  Player player;
  EventBus channel;
  Square first;
  static int bordSize = 5;

  @BeforeEach
  void setup() {
    EventBus channel = new EventBus();
    BoardSource source = BoardSource.using(new BoardReaderJson());
    source.readFile("src/test/testResources/jsonTest.json");
    first = BordBuilder.with(channel).addFrom(source).getFirstSquare();

    this.channel = new EventBus();
    player = new Player(Player.Id.ONE, new BankAccount(1000), first, this.channel);
  }

  @Test
  void bordeIsCirular() {
    assertEquals(player.move(bordSize), first);
    assertEquals(player.move(-bordSize), first);
  }

  @Test
  void movingForeardThenBackSamePostion() {
    player.move(2);
    Square secondSquare = player.postion();
    player.move(-2);

    assertNotEquals(player.postion(), secondSquare);
    assertEquals(player.postion(), first);
  }

  @Test
  void receive_cash_increase_amount_of_cash() {
    int amount = 50;
    int before = player.cash();

    player.receiveCash(amount);

    int changed = player.cash() - before;
    assertEquals(amount, changed);
    assertThrows(IllegalArgumentException.class, () -> player.receiveCash(-50));
  }

  @Test
  void cash_Change_will_send_CashEvent() {
    Listener spy = new Listener();
    channel.register(spy);
    int old = player.cash();

    player.receiveCash(50);

    CashEvent event = spy.event;
    assertEquals(old, event.getOldCash());
    assertEquals(player.cash(), event.getNewCash());
  }

  @Test
  void player_receive_cash_when_passing_Go() {
    Listener spy = new Listener();
    channel.register(spy);

    player.move(bordSize + 1);

    CashEvent event = spy.event;
    assert (event.getNewCash() > event.getOldCash());
  }

  class Listener {
    public CashEvent event;

    @Subscribe
    void getEvent(CashEvent event) {
      this.event = event;
    }
  }
}
