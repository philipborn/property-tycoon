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

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
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
  BoardReaderJson boardReaderJson;

  @BeforeEach
  void setup() {
    boardReaderJson = new BoardReaderJson();
    boardReaderJson.readFile("src/main/resources/boardDataJSON.json");
  }

  @Test
  void output_not_null() {
    boardReaderJson.nextObject();
    assertNotNull(boardReaderJson.getProperties());
  }

  @Test
  void iterates_through_the_list() {
    boardReaderJson.nextObject();
    assertEquals("1", boardReaderJson.getProperties().get("position"));
    boardReaderJson.nextObject();
    assertEquals("2", boardReaderJson.getProperties().get("position"));
  }

  @Test
  void iterate_is_bounded_by_size() {
    // 40 squares on board
    for (int i = 0; i <= 100; i++) {
      boardReaderJson.nextObject();
    }
    assertEquals(boardReaderJson.getProperties().get("position"), "40");
  }

  @Test
  void hash_map_only_has_necessary_fields() {
    boardReaderJson.nextObject();
    assertFalse(boardReaderJson.getProperties().containsKey("Rent"));
    assertFalse(boardReaderJson.getProperties().containsKey("Cost"));
    assertFalse(boardReaderJson.getProperties().containsKey("1house"));
    assertFalse(boardReaderJson.getProperties().containsKey("2houses"));
    assertFalse(boardReaderJson.getProperties().containsKey("3houses"));
    assertFalse(boardReaderJson.getProperties().containsKey("4houses"));
    assertFalse(boardReaderJson.getProperties().containsKey("hotel"));

    boardReaderJson.nextObject();
    assertFalse(boardReaderJson.getProperties().containsKey("Action"));
    assertEquals(boardReaderJson.getProperties().keySet().size(), 11);
  }

  @Test
  void changing_json_file_affects_output() {
    boardReaderJson.nextObject();

    assertEquals("Go",boardReaderJson.getProperties().get("name"));
    boardReaderJson.readFile("src/test/testResources/jsonTest.json");
    boardReaderJson.nextObject();
    assertEquals("name1",boardReaderJson.getProperties().get("name"));
  }

  @Test
  void all_keys_start_with_lower_keys() {
    boardReaderJson.nextObject();

    Set<String> keys = boardReaderJson.getProperties().keySet();

    for (String key : keys) {
      assertEquals(key.toLowerCase(), key);
    }
  }
}
