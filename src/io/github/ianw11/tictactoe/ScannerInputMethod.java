package io.github.ianw11.tictactoe;

import java.util.Scanner;

import io.github.ianw11.gamebase.io.InputMethod;

public class ScannerInputMethod extends InputMethod {
   Scanner scanner = new Scanner(System.in);
   
   @Override
   public int nextInt() {
      System.out.print("Enter val> ");
      return scanner.nextInt();
   }
}
