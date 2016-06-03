package io.github.ianw11.tictactoe;

import java.util.ArrayList;
import java.util.List;

import io.github.ianw11.gamebase.board.Coordinate;
import io.github.ianw11.gamebase.engine.BaseRulesEngine;
import io.github.ianw11.gamebase.engine.GameStateListener;
import io.github.ianw11.gamebase.io.InputMethod;
import io.github.ianw11.gamebase.turn.BasePlayer;
import io.github.ianw11.gamebase.turn.Turn;

public class RulesEngine extends BaseRulesEngine implements GameStateListener {
   
   public static void main(String[] args) {
      List<BasePlayer> players = new ArrayList<BasePlayer>(2);
      players.add(new TicTacToePlayer(0, "Player1", mInputMethod, Symbol.X));
      players.add(new TicTacToePlayer(1, "Player2", mInputMethod, Symbol.O));
      
      RulesEngine engine = new RulesEngine(players);
      engine.runGame();
      System.out.println("Done with TicTacToe");
   }
   
   private static final InputMethod mInputMethod = new ScannerInputMethod();
   
   private static final int NUM_PLAYERS = 2;
   private static final int DIMENSION = 3;
   
   protected static final String COORDINATE_TAG = "COORDINATE";
   
   protected enum Symbol {
      X,
      O;
   }
   private Symbol[][] mGameGrid;

   public RulesEngine(final List<BasePlayer> players) {
      super(players);
      
      addGameStateListener(this);
   }

   @Override
   protected int getMinNumberOfPlayers() {
      return NUM_PLAYERS;
   }

   @Override
   protected int getMaxNumberOfPlayers() {
      return NUM_PLAYERS;
   }

   @Override
   protected boolean processTurn(Turn currentTurn) {
      Coordinate coordinate = (Coordinate)currentTurn.getTurnData(COORDINATE_TAG);
      int x = coordinate.x;
      int y = coordinate.y;
      
      if (mGameGrid[x][y] != null) {
         System.err.println("Bad location");
         return false;
      }
      
      TicTacToePlayer player = (TicTacToePlayer)currentTurn.getPlayer();
      
      mGameGrid[x][y] = player.getSymbol();
      
      return true;
   }
   
   @Override
   public boolean isRoundOver() {
      return true;
   };
   
   @Override
   public boolean isGameOver() {
      boolean ret = false;
      ret |= checkVerticals();
      ret |= checkHorizontals();
      ret |= checkDiagonals();
      
      return ret;
   }
   
   @Override
   public void onPreTurn() {
      renderToConsole();
   }
   
   @Override
   public void onPreGameInit() {
      mGameGrid = new Symbol[DIMENSION][DIMENSION];
   }
   
   /*
    * NO OP METHODS
    */
   
   @Override
   public void onPostTurn() { }

   @Override
   public void onPreRound() { }

   @Override
   public void onPostRound() { }

   
   /*
    * PRIVATE METHODS FOR GAME STATE
    */
   
   private boolean checkVerticals() {
      for (int x = 0; x < DIMENSION; ++x) {
         int y = 0;
         final Symbol symbol = mGameGrid[x][y];
         if (symbol == null) {
            continue;
         }
         while (++y < DIMENSION) {
            if (mGameGrid[x][y] != symbol) {
               break;
            }
         }
         if (y == DIMENSION) {
            return true;
         }
      }
      
      return false;
   }
   
   private boolean checkHorizontals() {
      for (int y = 0; y < DIMENSION; ++y) {
         int x = 0;
         final Symbol symbol = mGameGrid[x][y];
         if (symbol == null) {
            continue;
         }
         while (++x < DIMENSION) {
            if (mGameGrid[x][y] != symbol) {
               break;
            }
         }
         if (x == DIMENSION) {
            return true;
         }
      }
      
      return false;
   }
   
   private boolean checkDiagonals() {
      int xy = 0;
      final Symbol topLeftSymbol = mGameGrid[xy][xy];
      boolean topLeftSuccess = true;
      
      int inverseX = DIMENSION - 1;
      final Symbol topRightSymbol = mGameGrid[inverseX][xy];
      boolean topRightSuccess = true;
      
      if (topLeftSymbol == null) {
         return false;
      }
      if (topRightSymbol == null) {
         return false;
      }
      
      while (++xy < DIMENSION) {
         if (mGameGrid[xy][xy] != topLeftSymbol) {
            topLeftSuccess = false;
         }
         if (mGameGrid[inverseX - xy][xy] != topRightSymbol) {
            topRightSuccess = false;
         }
      }
      
      return topLeftSuccess | topRightSuccess;
   }

   private void renderToConsole() {
      System.out.println("\n----------------");
      System.out.println("Current player: " + ((TicTacToePlayer)getCurrentPlayer()).getSymbol() );
      for (int y = 0; y < DIMENSION; ++y) {
         for (int x = 0; x < DIMENSION; ++x) {
            Symbol symbol = mGameGrid[x][y];
            String toPrint;
            if (symbol == null) {
               toPrint = "- ";
            } else {
               toPrint = symbol + " ";
            }
            
            System.out.print(toPrint);
         }
         System.out.println();
      }
      System.out.flush();
   }
   
}
