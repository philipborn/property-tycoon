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

package com.watson.propert.tycoon.gui;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.watson.propert.tycoon.io.BoardReaderJson;

/**
 * Test class for BoardReaderJson functionality.
 *
 * @author Tom Doran
 * @version 1.1
 * @since 15/02/2020
 */
class BoardReaderJsonTest {

  // board data used for the test
  BoardReaderJson r = new BoardReaderJson("src/main/resources/boardDataJSON.json");

  @Test
  // test that output of the board reader class is not null
  void test_output_not_null() {
    assertNotNull(r.getObjectData());
  }

  @Test
  // test that iterate method indeed moves through the list
  void test_iterate() {
    assertEquals(r.getObjectData().get("Position"), 1);
    r.iterate();
    assertEquals(r.getObjectData().get("Position"), 2);
  }

  @Test
  // test that iterate method does not go over the end of the list
  void test_iterate_bound() {
    // 40 squares on board
    for (int i = 0; i <= 100; i++) {
      r.iterate();
    }
    assertEquals(r.getObjectData().get("Position"), 40);
  }

  @Test
  // test that Go HashMap doesn't have fields for rent, houses etc.
  void test_HashMapSize_1() {
    assertFalse(r.getObjectData().containsKey("Rent"));
    assertFalse(r.getObjectData().containsKey("Cost"));
    assertFalse(r.getObjectData().containsKey("1house"));
    assertFalse(r.getObjectData().containsKey("2houses"));
    assertFalse(r.getObjectData().containsKey("3houses"));
    assertFalse(r.getObjectData().containsKey("4houses"));
    assertFalse(r.getObjectData().containsKey("hotel"));
  }

  @Test
  // test that Crapper Street HashMap doesn't have key for action, but does have a key for everything else
  void test_HashMapSize_2() {
    r.iterate();
    assertFalse(r.getObjectData().containsKey("Action"));
    assertEquals(r.getObjectData().keySet().size(), 11);
  }
}
