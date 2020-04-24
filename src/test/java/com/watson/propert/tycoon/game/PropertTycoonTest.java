/*
 * MIT License
 *
 * Copyright (c) 2020 Philip Borndalen
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.watson.propert.tycoon.game;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.*;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.watson.propert.tycoon.game.bord.Board;
import com.watson.propert.tycoon.game.bord.BoardSource;
import com.watson.propert.tycoon.game.bord.BordBuilder;
import com.watson.propert.tycoon.game.entitys.Player;
import com.watson.propert.tycoon.game.events.BuyOrNotMsg;
import com.watson.propert.tycoon.game.events.ChangePlayerEvent;
import com.watson.propert.tycoon.game.events.DiceEvent;
import com.watson.propert.tycoon.io.BoardReaderJson;

public class PropertTycoonTest {

  PropertTycoon game;
  EventBus channel;
  TestListener spy;

  @BeforeEach
  void setup() {
    channel = new EventBus();
    BoardSource source = BoardSource.using(new BoardReaderJson());
    source.readFile("src/test/testResources/jsonTest.json");
    Board board = BordBuilder.with(channel).addFrom(source).getBoard();

    game = new Game(board, channel);
    GameSetting setting = new GameSetting();
    setting.set(Player.Id.ONE);
    setting.set(Player.Id.TWO);
    game.startGame(setting);

    spy = new TestListener();
    channel.register(spy);
  }

  @Test
  void PropertyInfoReturnsInfoOnlyForProperty() {
    assertThrows(NoSuchElementException.class, () -> game.propertyInfo(1).orElseThrow());
    assertDoesNotThrow(() -> game.propertyInfo(2).orElseThrow());
  }


  class TestListener {
    public List<Object> msgs = new ArrayList<>();

    @Subscribe
    void catchDiceEvent(DiceEvent de) {
      msgs.add(de);
    }

    @Subscribe
    void changePlayerHandler(ChangePlayerEvent event) {
      msgs.add(event);
    }

    @Subscribe
    void buyOrNotHandler(BuyOrNotMsg msg) {
      msgs.add(msg);
    }
  }
}
