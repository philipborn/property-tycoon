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

import java.io.FileReader;
import java.util.HashMap;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

/**
 * Class for reading a JSON file & outputting the object data as a HashMap for later classes to use
 *
 * @author Tom Doran
 * @version 1.1
 * @since 13/02/2020
 */
public class BoardReaderJson {

  private JSONArray objects;
  private int iterator;
  private String[] keys = {
    "Position",
    "Name",
    "Group",
    "Action",
    "canBeBought",
    "Cost",
    "Rent",
    "1house",
    "2houses",
    "3houses",
    "4houses",
    "hotel"
  };

  /**
   * Constructor for building a Board reader object.
   *
   * @param fileName path of the JSON file
   */
  public BoardReaderJson(String fileName) {

    JSONParser jp = new JSONParser();
    iterator = 0;

    try {
      Object o = jp.parse(new FileReader(fileName));
      objects = (JSONArray) o;

    } catch (Exception e) {

      e.printStackTrace();
    }
  }

  /**
   * Method for iterating through the JSON Array, also checks that the iterator is within range.
   *
   * @return boolean true if iterator in range, false if not
   */
  public boolean iterate() {
    if (iterator < objects.size() - 1) {
      iterator++;
      return true;
    } else {
      return false;
    }
  }

  /**
   * Primary functionality of the class, translates a JSON object to a HashMap
   *
   * @return HashMap representing the data of a JSON object
   */
  public HashMap<String, String> getObjectData() {

    HashMap<String, String> data = new HashMap<>();
    // get current JSONObject
    JSONObject jso = (JSONObject) objects.get(iterator);

    // build map of data
    for (String key : keys) {
      // if square has relevant fields (ie. no rent information for 'Go')
      if (jso.get(key) instanceof String && ((String) jso.get(key)).length() > 0) {
        data.put(key, jso.get(key).toString());
      } else if (jso.get(key) instanceof Long) { // otherwise if data is a number

        // numbers wrapped with Long, so output ints (can be other data type if necessary later)
        data.put(key, ((Long) jso.get(key)).toString());
      }
    }
    return data;
  }
}
