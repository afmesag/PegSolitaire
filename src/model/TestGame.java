package model;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;

public class TestGame {

  private static final Logger LOGGER = Logger.getLogger("logger");

  public static void main(String[] args) {
    try {
      Handler consoleHandler = new ConsoleHandler();
      Handler fileHandler = new FileHandler("logger.xml", false);
      LOGGER.addHandler(consoleHandler);
      LOGGER.addHandler(fileHandler);
      consoleHandler.setLevel(Level.ALL);
      fileHandler.setLevel(Level.ALL);
      Game mygame = new Game();
      mygame.setConfiguration("Latin cross");
      LOGGER.log(Level.INFO, () -> "" + mygame.printBoard());
      while (!mygame.isWin() && !mygame.isLose()) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int[] start = {Integer.parseInt(Character.toString(line.charAt(0))),
          Integer.parseInt(Character.toString(line.charAt(2)))};
        line = scanner.nextLine();
        int[] end = {Integer.parseInt(Character.toString(line.charAt(0))),
          Integer.parseInt(Character.toString(line.charAt(2)))};
        mygame.makeMove(start, end);
        LOGGER.log(Level.INFO, () -> "Start: " + printArray(start));
        LOGGER.log(Level.INFO, () -> "End: " + printArray(end));
        LOGGER.log(Level.INFO, () -> "" + mygame.printBoard());
      }
      if (mygame.isWin())
        LOGGER.log(Level.INFO, "YOU WON");
      else if (mygame.isLose())
        LOGGER.log(Level.INFO, "YOU LOSE");
    } catch (IOException ex) {
      LOGGER.log(Level.SEVERE, "Error of IO");
    } catch (SecurityException ex) {
      LOGGER.log(Level.SEVERE, "Error of Seguridad");
    }
  }

  public static String printArray(int[] array) {
    StringBuilder response = new StringBuilder();
    for (int item : array) {
      response.append(item);
      response.append(" ");
    }
    return response.toString();
  }
}
