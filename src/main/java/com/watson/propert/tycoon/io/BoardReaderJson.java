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
import com.watson.propert.tycoon.game.bord.Street;

/**
 * Class for reading a JSON file & outputting the object data as a HashMap for later classes to use
 *
 * @author Tom Doran
 * @version 1.1
 * @since 13/02/2020
 */
public class BoardReaderJson implements BordReader, BordBuilder.Source {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  private JSONArray objects;
  private int index;

  private BordBuilder builder;

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

  @Override
  public void extractTo(BordBuilder builder) {
    this.builder = builder;
    while (hasNextObject()) {
      nextObject();
      Map<String, String> prop = getProperties();
      addSquare(prop);
    }
    this.builder = null;
  }

  private void addSquare(Map<String, String> prop) {
    if (isStreet(prop)) {
      buildStreet(prop);
    } else if (isStation(prop)) {
      buildStation(prop);
    } else if (isUtility(prop)) {
      buildUtility(prop);
    } else {
      buildSquare(prop);
    }
  }

  private void buildSquare(Map<String, String> prop) {
    String name = prop.get("name");
    builder.addSquare(name);
  }

  private Boolean isStreet(Map<String, String> prop) {
    final Set<String> streetKeys =
        Set.of(
            "position",
            "name",
            "group",
            "canBeBought".toLowerCase(),
            "cost",
            "rent",
            "1house",
            "2houses",
            "3houses",
            "4houses",
            "hotel");
    Set<String> keys = prop.keySet();
    return keys.containsAll(streetKeys);
  }

  private void buildStreet(Map<String, String> prop) {
    String name = prop.get("name");
    int value = Integer.parseInt(prop.get("cost"));
    Street.Colour color = extractColor(prop.get("group"));
    List<Integer> rent = new ArrayList<>(5);
    rent.add(Integer.valueOf(prop.get("rent")));
    builder.addStreet(name, value, color, rent);
  }

  private Street.Colour extractColor(String color) {
    String regex = " ";
    String replacement = "_";
    return Street.Colour.valueOf(color.toUpperCase().replaceAll(regex, replacement));
  }

  private Boolean isStation(Map<String, String> prop) {
    if (prop.get("group") != null) {
      return prop.get("group").toLowerCase().equals("station");
    }
    return false;
  }

  private void buildStation(Map<String, String> prop) {
    String name = prop.get("name");
    int value = Integer.parseInt(prop.get("cost"));
    builder.addStation(name, value);
  }

  private Boolean isUtility(Map<String, String> prop) {
    if (prop.get("group") == null) {
      return false;
    }
    return prop.get("group").toLowerCase().equals("utilities");
  }

  private void buildUtility(Map<String, String> prop) {
    String name = prop.get("name");
    int value = Integer.parseInt(prop.get("cost"));
    builder.addUtility(name, value);
  }
}
