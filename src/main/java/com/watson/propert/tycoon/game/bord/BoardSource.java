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
import java.util.function.BiConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for reading a JSON file & outputting the object data as a HashMap for later classes to use
 *
 * @author Tom Doran
 * @version 1.1
 * @since 13/02/2020
 */
public class BoardSource implements BordBuilder.Source {

  private static final Logger logger = LoggerFactory.getLogger(BoardSource.class);

  enum Group {
    GO(BoardSource::buildGo),
    BLUE(BoardSource::buildStreet),
    BROWN(BoardSource::buildStreet),
    DEEP_BLUE(BoardSource::buildStreet),
    GREEN(BoardSource::buildStreet),
    ORANGE(BoardSource::buildStreet),
    PURPLE(BoardSource::buildStreet),
    RED(BoardSource::buildStreet),
    YELLOW(BoardSource::buildStreet),
    STATION(BoardSource::buildStation),
    UTILITIES(BoardSource::buildUtility),
    JAIL(BoardSource::buildJail),
    FREEPARKING(BoardSource::buildFreePark),
    TAKE_CARD(BoardSource::buildDeck),
    ACTION(BoardSource::buildAction);

    private final BiConsumer<BordBuilder, Map<String, String>> handler;

    Group(BiConsumer<BordBuilder, Map<String, String>> handler) {
      this.handler = handler;
    }

    public void run(BordBuilder builder, Map<String, String> cardProperty) {
      handler.accept(builder, cardProperty);
    }
  }

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
    Optional.ofNullable(prop.get("group"))
        .map(String::trim)
        .map(String::toUpperCase)
        .map((str) -> str.replaceAll(" ", "_"))
        .map(Group::valueOf)
        .ifPresent((group -> group.run(builder, prop)));
  }

  private static void buildSquare(BordBuilder builder, Map<String, String> prop) {
    logger.debug("Make square: " + prop.get("position") + " : " + prop.get("name"));
    String name = prop.get("name");
    builder.addSquare(name);
  }

  private static void buildStreet(BordBuilder builder, Map<String, String> prop) {
    logger.debug("Make street: " + prop.get("position") + " : " + prop.get("name"));
    String name = prop.get("name");
    int value = Integer.parseInt(prop.get("cost"));
    Street.Colour color = extractColor(prop.get("group"));
    List<Integer> rent = extractRents(prop);
    rent.add(Integer.valueOf(prop.get("rent")));
    builder.addStreet(name, value, color, rent);
  }

  private static List<Integer> extractRents(Map<String, String> prop) {
    List<Integer> rents = new ArrayList<>();
    rents.add(Integer.valueOf(prop.get("rent")));
    rents.add(Integer.valueOf(prop.get("1house")));
    rents.add(Integer.valueOf(prop.get("2houses")));
    rents.add(Integer.valueOf(prop.get("3houses")));
    rents.add(Integer.valueOf(prop.get("4houses")));
    rents.add(Integer.valueOf(prop.get("hotel")));
    return rents;
  }

  private static Street.Colour extractColor(String color) {
    String regex = " ";
    String replacement = "_";
    return Street.Colour.valueOf(color.toUpperCase().replaceAll(regex, replacement));
  }

  private static void buildStation(BordBuilder builder, Map<String, String> prop) {
    logger.debug("Make station: " + prop.get("position") + " : " + prop.get("name"));
    String name = prop.get("name");
    int value = Integer.parseInt(prop.get("cost"));
    builder.addStation(name, value);
  }

  private static void buildUtility(BordBuilder builder, Map<String, String> prop) {
    logger.debug("Make utility: " + prop.get("position") + " : " + prop.get("name"));
    String name = prop.get("name");
    int value = Integer.parseInt(prop.get("cost"));
    builder.addUtility(name, value);
  }

  private static void buildGo(BordBuilder builder, Map<String, String> prop) {
    logger.debug("Make Go: " + prop.get("position") + " : " + prop.get("name"));
    String name = prop.getOrDefault("name", "Go");
    builder.addGo(name);
  }

  private static void buildJail(BordBuilder builder, Map<String, String> prop) {
    logger.debug("Make jail: " + prop.get("position") + " : " + prop.get("name"));
    String name = prop.getOrDefault("name", "Jail");
    builder.addJail(name);
  }

  private static void buildFreePark(BordBuilder builder, Map<String, String> prop) {
    logger.debug("Make freePark: " + prop.get("position") + " : " + prop.get("name"));
    String name = prop.getOrDefault("name", "FreePark");
    builder.addFreePark(name);
  }

  private static void buildDeck(BordBuilder builder, Map<String, String> prop) {
    logger.debug("Make card: " + prop.get("position") + " : " + prop.get("name"));
    String squareName = prop.getOrDefault("name", "Unknown Card");
    builder.addDeck(squareName);
  }

  private static void buildAction(BordBuilder builder, Map<String, String> prop) {
    logger.debug("Make ActionTrigger: " + prop.get("position") + " : " + prop.get("name"));
    String squareName = prop.getOrDefault("name", "Unknown Action");
    Action action = new ActionPlaceHolder(prop);
    builder.addActionSquare(squareName, action);
  }

  public static BoardSource using(BordReader reader) {
    return new BoardSource(reader);
  }
}
