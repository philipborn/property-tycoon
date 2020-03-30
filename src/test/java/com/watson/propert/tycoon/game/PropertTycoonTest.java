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

import org.junit.jupiter.api.*;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.watson.propert.tycoon.io.BoardReaderJson;

public class PropertTycoonTest {

  PropertTycoon game;
  EventBus channel;
  TestListener spy;

  @BeforeEach
  void setup() {
    channel = new EventBus();
    BordBuilder bb = new BordBuilder(channel);
    BoardReaderJson br = new BoardReaderJson();
    br.readFile("src/test/testResources/jsonTest.json");

    Square first = bb.buildBord(br);

    game = new Game(first, channel);

    spy = new TestListener();
    channel.register(spy);
  }

  @Test
  void ThrowDice_multiTimes_do_nothing() {
    game.throwDicesAndMove();
    game.throwDicesAndMove();
    game.throwDicesAndMove();
    game.throwDicesAndMove();

    final int expectedNumberOfCashEvents = 1;
    int count = (int) spy.msgs.stream().filter(event -> event instanceof DiceEvent).count();
    assertEquals(expectedNumberOfCashEvents, count);
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
