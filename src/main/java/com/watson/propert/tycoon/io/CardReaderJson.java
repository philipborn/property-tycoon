package com.watson.propert.tycoon.io;

import java.io.*;
import java.util.*;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.watson.propert.tycoon.game.bord.CardOrder;
import com.watson.propert.tycoon.game.bord.CardReader;

public class CardReaderJson implements CardReader {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private String path;

  public CardReaderJson(String path) {
    this.path = path;
  }

  @Override
  public List<CardOrder> getCardOrders() {
    JSONParser parser = new JSONParser();
    List<CardOrder> orders = new ArrayList<>(40);
    try {
      Reader reader = new FileReader(path);
      JSONArray jsonObj = (JSONArray) parser.parse(reader);
      for (Object obj : jsonObj) {
        JSONObject object = (JSONObject) obj;
        orders.add(createCardOrder(object));
      }

    } catch (FileNotFoundException e) {
      logger.error("File not found: " + path, e);
    } catch (ParseException e) {
      logger.error("Cant parse " + path, e);
    } catch (IOException e) {
      logger.error("IO problem: " + path, e);
    }

    return orders;
  }

  private CardOrder createCardOrder(JSONObject object) {
    Map<String, String> stringValus = new HashMap<>();
    Map<String, Long> intValus = new HashMap<>();
    Set<String> keys = object.keySet();

    for (String key : keys) {
      Optional.ofNullable(object.get(key))
          .filter(String.class::isInstance)
          .map(String.class::cast)
          .ifPresent((val) -> stringValus.put(key.toLowerCase(), val));
      Optional.ofNullable(object.get(key))
          .filter(Long.class::isInstance)
          .map(Long.class::cast)
          .ifPresent((val) -> intValus.put(key.toLowerCase(), val));
    }
    return new CardOrderJson(intValus, stringValus);
  }

  class CardOrderJson implements CardOrder {

    private Map<String, Long> intValues;
    private Map<String, String> strValues;

    CardOrderJson(Map<String, Long> intValues, Map<String, String> strValues) {
      this.intValues = intValues;
      this.strValues = strValues;
    }

    @Override
    public String getDeckName() {
      return strValues.get("deckname");
    }

    @Override
    public String getTyp() {
      return strValues.get("typ");
    }

    @Override
    public String getDescription() {
      return strValues.get("description");
    }

    @Override
    public int getPay() {
      return Math.toIntExact(intValues.get("pay"));
    }

    @Override
    public int getSteps() {
      return Math.toIntExact(intValues.get("steps"));
    }

    @Override
    public int getPosition() {
      return Math.toIntExact(intValues.get("position"));
    }

    @Override
    public String getCard() {
      return strValues.get("card");
    }

    @Override
    public int getHouse() {
      return Math.toIntExact(intValues.get("house"));
    }

    @Override
    public int getHotel() {
      return Math.toIntExact(intValues.get("hotel"));
    }
  }
}
