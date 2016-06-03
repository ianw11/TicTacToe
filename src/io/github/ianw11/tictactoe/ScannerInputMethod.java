package io.github.ianw11.tictactoe;

import java.util.Scanner;

import io.github.ianw11.gamebase.io.InputMethod;

public class ScannerInputMethod extends InputMethod {
   private final Scanner scanner = new Scanner(System.in);
   
   @Override
   public int nextInt() {
      return scanner.nextInt();
   }
   
   @Override
   public void close() {
      scanner.close();
   }
}
