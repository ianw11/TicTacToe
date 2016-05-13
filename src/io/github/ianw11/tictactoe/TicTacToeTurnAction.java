package io.github.ianw11.tictactoe;

import io.github.ianw11.gamebase.board.Coordinate;
import io.github.ianw11.gamebase.io.InputMethod;
import io.github.ianw11.gamebase.turn.TurnAction;

public class TicTacToeTurnAction extends TurnAction {

   private Coordinate mData;
   
   @Override
   public String getTag() {
      return RulesEngine.COORDINATE_TAG;
   }
   
   @Override
   public Object getData() {
      return mData;
   }
   
   @Override
   public TurnActionResult doAction(InputMethod inputMethod) {
      try {
         int x = inputMethod.nextInt();
         int y = inputMethod.nextInt();
         mData = new Coordinate(x, y);
         return TurnActionResult.SUCCESS;
      } catch (Exception e) {
         return TurnActionResult.FAILURE;
      }
   }
   
   /*
    * OVERRIDE THIS METHOD TO CHAIN TURN ACTIONS
    */
   @Override
   public TurnAction getNextAction() {
      return super.getNextAction();
   }

}
