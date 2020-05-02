package com.watson.propert.tycoon.game.bord;

import java.util.Iterator;
import java.util.Map;

import com.watson.propert.tycoon.game.actions.ActionFactory;
import com.watson.propert.tycoon.game.actions.ActionTyp;
import com.watson.propert.tycoon.game.entitys.Player;

public class ActionPlaceHolder implements Action {

  private final Map<String, String> props;

  public ActionPlaceHolder(Map<String, String> props) {
    this.props = props;
  }

  public Map<String, String> getProps() {
    return props;
  }

  @Override
  public void run(Player player) {
    throw new RuntimeException("ActionPlaceHolder should not run");
  }

  public static void replaces(ActionFactory factory, Iterator<Square> iterator) {
    Replacer replacer = new Replacer(factory);
    iterator.forEachRemaining(square -> square.visitBy(replacer));
  }

  private static class Replacer implements SquareVisitor {

    private ActionFactory factory;

    public Replacer(ActionFactory factory) {
      this.factory = factory;
    }

    @Override
    public void areAt(ActionTrigger actionTrigger) {
      Action action = actionTrigger.getAction();
      if (action instanceof ActionPlaceHolder) {
        Map<String, String> prop = ((ActionPlaceHolder) action).getProps();
        actionTrigger.setAction(createAction(prop));
      }
    }

    private Action createAction(Map<String, String> props) {
      ActionTyp typ = ActionTyp.valueOf(props.get("typ").replace('-', '_').toUpperCase());
      switch (typ) {
        case PLAYER_STEP_FORWARD:
          return factory.playerStep(Integer.parseInt(props.get("steps")));
        case PLAYER_STEP_BACKWARD:
          return factory.playerStep(-Integer.parseInt(props.get("steps")));
        case PLAYER_FORWARD_TO:
          return factory.forwardTo(Integer.parseInt(props.get("position")));
        case PLAYER_BACKWARD_TO:
          return factory.backwardTo(Integer.parseInt(props.get("position")));
        case PLAYER_TO_JAIL:
          return factory.playerToJail();
        case PLAYER_PAY_BANK:
          return factory.playerPayBank(Integer.parseInt(props.get("pay")));
        case PLAYER_PAY_FREEPARK:
          return factory.playerPayFreePark(Integer.parseInt(props.get("pay")));
        case BANK_PAY_PLAYER:
          return factory.bankPayPlayer(Integer.parseInt(props.get("pay")));
        default:
          throw new RuntimeException(String.format("Unknown card type: %s", typ));
      }
    }
  }
}
