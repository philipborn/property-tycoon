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
package com.watson.propert.tycoon.game.bord;

import java.util.*;

/**
 * Class for reading a JSON file & outputting the object data as a HashMap for later classes to use
 *
 * @author Tom Doran
 * @version 1.1
 * @since 13/02/2020
 */
public class BoardSource implements BordBuilder.Source {

  private BordBuilder builder;
  private BordReader reader;

  private BoardSource(BordReader reader) {
    this.reader = reader;
  }

  public void readFile(String filePath) {
    reader.readFile(filePath);
  }

  @Override
  public void extractTo(BordBuilder builder) {
    this.builder = builder;
    while (reader.hasNextObject()) {
      reader.nextObject();
      Map<String, String> prop = reader.getProperties();
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
    } else if (isGo(prop)) {
      buildGo(prop);
    } else if (isJail(prop)) {
      buildJail(prop);
    } else if (isFreepark(prop)) {
      buildFreePark(prop);
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

  private boolean isGo(Map<String, String> prop) {
    String group = prop.getOrDefault("group", "");
    return group.toLowerCase().equals("go");
  }

  private void buildGo(Map<String, String> prop) {
    String name = prop.getOrDefault("name", "Go");
    builder.addGo(name);
  }

  private boolean isJail(Map<String, String> prop) {
    String group = prop.getOrDefault("group", "");
    return group.toLowerCase().equals("jail");
  }

  private void buildJail(Map<String, String> prop) {
    String name = prop.getOrDefault("name", "Jail");
    builder.addJail(name);
  }

  private boolean isFreepark(Map<String, String> prop) {
    String group = prop.getOrDefault("group", "");
    return group.toLowerCase().equals("freepark");
  }

  private void buildFreePark(Map<String, String> prop) {
    String name = prop.getOrDefault("name", "FreePark");
    builder.addFreePark(name);
  }

  public static BoardSource using(BordReader reader) {
    return new BoardSource(reader);
  }
}
