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

package com.watson.propert.tycoon.io;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for BoardReaderJson functionality.
 *
 * @author Tom Doran
 * @version 1.1
 * @since 15/02/2020
 */
class BoardReaderJsonTest {

  // board data used for the test
  BoardReaderJson boardReaderJson = new BoardReaderJson("src/main/resources/boardDataJSON.json");

  @Test
  void output_not_null() {
    assertNotNull(boardReaderJson.getObjectData());
  }

  @Test
  void iterates_through_the_list() {
    assertEquals(boardReaderJson.getObjectData().get("Position"), "1");
    boardReaderJson.iterate();
    assertEquals(boardReaderJson.getObjectData().get("Position"), "2");
  }

  @Test
  void iterate_is_bounded_by_size() {
    // 40 squares on board
    for (int i = 0; i <= 100; i++) {
      boardReaderJson.iterate();
    }
    assertEquals(boardReaderJson.getObjectData().get("Position"), "40");
  }

  @Test
  void hash_map_only_has_necessary_fields() {
    assertFalse(boardReaderJson.getObjectData().containsKey("Rent"));
    assertFalse(boardReaderJson.getObjectData().containsKey("Cost"));
    assertFalse(boardReaderJson.getObjectData().containsKey("1house"));
    assertFalse(boardReaderJson.getObjectData().containsKey("2houses"));
    assertFalse(boardReaderJson.getObjectData().containsKey("3houses"));
    assertFalse(boardReaderJson.getObjectData().containsKey("4houses"));
    assertFalse(boardReaderJson.getObjectData().containsKey("hotel"));

    boardReaderJson.iterate();
    assertFalse(boardReaderJson.getObjectData().containsKey("Action"));
    assertEquals(boardReaderJson.getObjectData().keySet().size(), 11);
  }

  @Test
  void changing_json_file_affects_output() {

    assertEquals(boardReaderJson.getObjectData().get("Name"), "Go");
    boardReaderJson = new BoardReaderJson("src/test/testResources/jsonTest.json");
    assertEquals(boardReaderJson.getObjectData().get("Name"), "test1");
  }
}
