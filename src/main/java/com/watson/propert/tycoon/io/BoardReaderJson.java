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
import java.util.*;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.watson.propert.tycoon.game.bord.BordBuilder;
import com.watson.propert.tycoon.game.bord.BordReader;

/**
 * Class for reading a JSON file and outputting the object data as a HashMap for later classes to use
 *
 * @author Tom Doran
 * @version 1.1
 * @since 13/02/2020
 */
public class BoardReaderJson implements BordReader {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  private JSONArray objects;
  private int index;

  private BordBuilder builder;

  @Override
  public void readFile(String fileName) {
    JSONParser jp = new JSONParser();
    index = -1;

    try {
      Object o = jp.parse(new FileReader(fileName));
      objects = (JSONArray) o;

    } catch (Exception e) {
      logger.error("JSON Parser fail", e);
    }
  }
  /**
   * Primary functionality of the class, translates a JSON object to a HashMap
   *
   * @return HashMap representing the data of a JSON object
   */
  @Override
  public Map<String, String> getProperties() {
    int usedIndex = index;
    if (index < 0) {
      throw new RuntimeException("Has not call nextObject");
    }
    if (index >= objects.size()) {
      usedIndex = objects.size() - 1;
    }

    JSONObject json = (JSONObject) objects.get(usedIndex);
    Set<Object> keys = json.keySet();

    Map<String, String> map = new HashMap<>();
    for (Object key : keys) {
      Object val = json.get(key);
      Optional<String> Optvalue = filter(val);
      Optvalue.ifPresent((s) -> map.put(key.toString().toLowerCase(), s));
    }
    return map;
  }

  private Optional<String> filter(Object val) {
    if (val.toString().equals("")) {
      return Optional.empty();
    }
    return Optional.of(val.toString());
  }

  @Override
  public Boolean hasNextObject() {
    return index < (objects.size() - 1);
  }

  @Override
  public void nextObject() {
    ++index;
  }
}
