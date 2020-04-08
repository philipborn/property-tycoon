package com.watson.propert.tycoon.game.bord;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.eventbus.EventBus;

public class BordBuilder {

  public interface Source {
    void extractTo(BordBuilder builder);
  }

  private EventBus channel;
  private BordReader source;

  private SquareImp first;
  private SquareImp last;
  private Map<String, String> prop;
  private Boolean doneLastLink = false;

  private BordBuilder(EventBus channel) {
    this.channel = channel;
  }

  public Square getBord() {
    if (doneLastLink == false) {
      linkLastAndFirst();
    }
    return first;
  }

  public BordBuilder addStreet(String name, int value, Street.Colour color, List<Integer> rents) {
    checkIfCanAddSquare();
    addToLink(new Street(name, value, color, rents));
    return this;
  }

  public BordBuilder addStation(String name, int value) {
    checkIfCanAddSquare();
    addToLink(new Station(name, value));
    return this;
  }

  public BordBuilder addUtility(String name, int value) {
    checkIfCanAddSquare();
    Utilities utilities = new Utilities(name, value);
    addToLink(utilities);
    channel.register(utilities);
    return this;
  }

  public BordBuilder addSquare(String name) {
    checkIfCanAddSquare();
    addToLink(new SquareImp(name));
    return this;
  }

  public BordBuilder addSquare(SquareImp square) {
    checkIfCanAddSquare();
    addToLink(square);
    return this;
  }

  public BordBuilder addFrom(Source source) {
    checkIfCanAddSquare();
    source.extractTo(this);
    return this;
  }

  public BordBuilder X(BordReader source) {
    checkIfCanAddSquare();

    this.source = source;
    source.nextObject();
    first = createSquare();
    last = first;

    while (source.hasNextObject()) {
      source.nextObject();
      SquareImp current = createSquare();
      addToLink(current);
    }
    return this;
  }

  private void checkIfCanAddSquare() {
    if (doneLastLink) {
      throw new RuntimeException("Can't add Square if the bord is build");
    }
  }

  private SquareImp createSquare() {
    prop = source.getProperties();
    if (isStreet()) {
      return buildStreet();
    } else if (isStation()) {
      return buildStation();
    } else if (isUtilits()) {
      return buildUtilitys();
    }
    return new SquareImp(prop.get("name"));
  }

  private Boolean isStreet() {
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

  private SquareImp buildStreet() {
    String name = prop.get("name");
    int value = Integer.valueOf(prop.get("cost"));
    Street.Colour color = extractColor(prop.get("group"));
    List<Integer> rent = new ArrayList<>(5);
    rent.add(Integer.valueOf(prop.get("rent")));
    return new Street(name, value, color, rent);
  }

  private Street.Colour extractColor(String color) {
    String regex = " ";
    String replacement = "_";
    return Street.Colour.valueOf(color.toUpperCase().replaceAll(regex, replacement));
  }

  private Boolean isStation() {
    if (prop.get("group") != null) {
      return prop.get("group").toLowerCase().equals("station");
    }
    return false;
  }

  private SquareImp buildStation() {
    String name = prop.get("name");
    int value = Integer.valueOf(prop.get("cost"));
    Station station = new Station(name, value);
    return new Station(name, value);
  }

  private Boolean isUtilits() {
    if (prop.get("group") == null) {
      return false;
    }
    return prop.get("group").toLowerCase().equals("utilities");
  }

  private SquareImp buildUtilitys() {
    String name = prop.get("name");
    int value = Integer.valueOf(prop.get("cost"));
    return new Utilities(name, value);
  }

  private SquareImp addToLink(SquareImp current) {
    if (last != null) {
      current.setBack(last);
      last.setNext(current);
    } else {
      first = current;
    }
    last = current;
    return current;
  }

  private void linkLastAndFirst() {
    last.setNext(first);
    first.setBack(last);
  }

  public static BordBuilder with(EventBus channel) {
    return new BordBuilder(channel);
  }
}
