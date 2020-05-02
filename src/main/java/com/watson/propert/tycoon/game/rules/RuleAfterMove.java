package com.watson.propert.tycoon.game.rules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.EventBus;
import com.watson.propert.tycoon.game.bord.*;
import com.watson.propert.tycoon.game.entitys.Player;
import com.watson.propert.tycoon.game.events.CardDrawEvent;

public class RuleAfterMove implements SquareVisitor {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private Player player;
  private EventBus channel;

  public RuleAfterMove(Player player, EventBus channel) {
    this.player = player;
    this.channel = channel;
  }

  private void propertyHandling(Property property) {
    property
        .owner()
        .filter((owner) -> owner != player)
        .ifPresent((owner) -> player.payTo(owner, property.getRent()));
  }

  @Override
  public void areAt(Street street) {
    logger.debug("Player " + player.getId() + " landed on a street");
    propertyHandling(street);
  }

  @Override
  public void areAt(Station station) {
    logger.debug("Player " + player.getId() + " landed on a station");
    propertyHandling(station);
  }

  @Override
  public void areAt(Utilities utilities) {
    logger.debug("Player " + player.getId() + " landed on a utilities");
    propertyHandling(utilities);
  }

  @Override
  public void areAt(ActionTrigger actionTrigger) {
    logger.debug("Player " + player.getId() + " landed on a action Square");
    actionTrigger.getAction().run(player);
  }

  @Override
  public void areAt(FreePark freePark) {
    logger.debug("Player " + player.getId() + " landed on free park");
    int amount = freePark.cash();
    freePark.payTo(player, amount);
  }

  @Override
  public void areAt(Deck deck) {
    Card card = deck.draw();
    logger.debug("Player " + player.getId() + " landed on a card: " + card.getDeckName());
    channel.post(new CardDrawEvent(card.getDeckName(), card.getDescription()));
    deck.put(card);
    card.run(player);
  }
}
