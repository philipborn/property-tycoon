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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.*;

import com.google.common.eventbus.EventBus;

public class PropertTycoonTest {

  PropertTycoon game;
  EventBus channle;

  @BeforeEach
  void setup() {
    SquareImp firstNode = new SquareImp("first");
    SquareImp secondNode = new SquareImp("second");
    SquareImp thirdNode = new SquareImp("third");
    firstNode.setNext(secondNode);
    secondNode.setNext(thirdNode);
    thirdNode.setNext(firstNode);
    firstNode.setBack(thirdNode);
    secondNode.setBack(firstNode);
    thirdNode.setBack(secondNode);

    channle = new EventBus();
    game = new GameMaster(firstNode, channle);
  }

  @Test
  void getDiceShouldReturnListOfSize2() {
    List<Integer> list = game.throwDices();

    assertEquals(list.size(), 2);
  }

  @Test
  void resultFromThrowDiceAndGetDiceShouldBeEqual() {
    List<Integer> thrownDices = game.throwDices();
    List<Integer> getDices = game.getDices().orElseThrow();

    assertEquals(thrownDices, getDices);
  }

  @Test
  void getNamesGivesAllBordNames() {
    List<String> names = game.getSquaresNames();

    assertEquals("first", names.get(0));
    assertEquals("second", names.get(1));
    assertEquals("third", names.get(2));
  }
}
