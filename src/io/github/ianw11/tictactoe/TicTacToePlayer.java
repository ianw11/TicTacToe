package io.github.ianw11.tictactoe;

import io.github.ianw11.gamebase.io.InputMethod;
import io.github.ianw11.gamebase.turn.BasePlayer;
import io.github.ianw11.gamebase.turn.TurnAction;
import io.github.ianw11.tictactoe.RulesEngine.Symbol;

public class TicTacToePlayer extends BasePlayer {
   
   private final Symbol mSymbol;
   
   public TicTacToePlayer(final int id, final String name, final InputMethod input, final Symbol symbol) {
      super(id, name, input);
      mSymbol = symbol;
   }
   
   public Symbol getSymbol() {
      return mSymbol;
   }

   @Override
   protected TurnAction getInitialTurnAction() {
      return new TicTacToeTurnAction();
   }

}
