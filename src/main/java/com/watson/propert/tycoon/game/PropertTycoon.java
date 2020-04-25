package com.watson.propert.tycoon.game;

import java.util.Optional;

import com.watson.propert.tycoon.game.entitys.Player;

/** The interface for GUI to take action for the players */
public interface PropertTycoon {

  void startGame(GameSetting settings);

  /** Call to stop and clean up the game; */
  void endGame();

  /** Sen player Actions to the game */
  void send(PlayerAction playerAction);

  /**
   * @param squareNum) The number of the square, first square as number 1
   * @return Datastructer with all information of a Property, empty if not a property
   */
  Optional<PropertyInfo> propertyInfo(int squareNum);

  /**
   * PlayerInfo as cash, totalValue, properties owned by the player.
   *
   * @param id Id of the player want info from
   * @return Immutable container player stats if player is still playing, else empty
   */
  Optional<PlayerInfo> playerInfo(Player.Id id);

  /**
   * Used to get start cash for player
   *
   * @return Players Cash at begin. All player start with same amount.
   */
  int startCash();

  /** Register to the event bus used by the game to inform of change state */
  void registerListener(Object listener);

  /** Remove listener of the event bus */
  void unregisterListener(Object listener);
}
