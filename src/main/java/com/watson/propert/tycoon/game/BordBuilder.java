package com.watson.propert.tycoon.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.eventbus.EventBus;

public class BordBuilder {

  private EventBus channel;
  private BordReader source;

  private SquareImp first;
  private SquareImp last;
  private SquareImp current;
  Map<String, String> prop;

  public BordBuilder(EventBus channel) {
    this.channel = channel;
  }

  public Square buildBord(BordReader source) {
    this.source = source;

    source.nextObject();
    first = createSquare();
    last = first;

    while (source.hasNextObject()) {
      source.nextObject();
      current = createSquare();
      link(current);
      last = current;
    }
    linkLastAndFirst();

    return first;
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
    Street.StreetColour color = extractColor(prop.get("group"));
    List<Integer> rent = new ArrayList<>(5);
    rent.add(Integer.valueOf(prop.get("rent")));
    return new Street(name, value, color, rent);
  }

  private Street.StreetColour extractColor(String color) {
    String regex = " ";
    String replacement = "_";
    return Street.StreetColour.valueOf(color.toUpperCase().replaceAll(regex, replacement));
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

  SquareImp link(SquareImp current) {
    current.setBack(last);
    last.setNext(current);
    return current;
  }

  private void linkLastAndFirst() {
    last.setNext(first);
    first.setBack(last);
  }
}
