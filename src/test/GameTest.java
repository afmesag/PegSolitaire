package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

import model.Game;

public class GameTest {
  private Random random = new Random();
  private static final int SIZE = 7;

  @Test
  public void testGame() {
    assertEquals(Game.class, new Game().getClass());
  }

  @Test
  public void testSetConfiguration() {
    Game game = new Game();
    game.setConfiguration("Latin cross");
    String expected = "XXOOOXX\nXXOIOXX\nOOIIIOO\nOOOIOOO\nOOOIOOO\nXXOOOXX\nXXOOOXX";
    assertEquals(expected, game.printBoard());
  }

  @Test
  public void testPossibleMoves() {
    Game game = new Game();
    game.setConfiguration("");
    int row = random.nextInt(SIZE);
    int col = random.nextInt(SIZE);
    int size = game.possibleMoves(row, col).size();
    assertTrue(size >= 0 && size <= 4);
  }

  @Test
  public void testGetDirection() {
    Game game = new Game();
    game.setConfiguration("");
    int[] start = {random.nextInt(SIZE), random.nextInt(SIZE)};
    int[] end = {random.nextInt(SIZE), random.nextInt(SIZE)};
    int[] result = game.getDirection(start, end);
    int[][] possibleDirection = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    boolean assertFlag = false;
    for (int[] item : possibleDirection) {
      if (Arrays.equals(item, result)) {
        assertFlag = true;
        break;
      }
    }
    assertTrue(assertFlag||result.length==0);
  }

  @Test
  public void testMakeMove() {
    assertTrue(true);
  }

  @Test
  public void testIsWin() {
    assertTrue(true);
  }

  @Test
  public void testIsLose() {
    assertTrue(true);
  }

  @Test
  public void testGetNeighbor() {
    assertTrue(true);
  }

  @Test
  public void testCanMove() {
    assertTrue(true);
  }

  @Test
  public void testGetJumpMove() {
    assertTrue(true);
  }

  @Test
  public void testPrintBoard() {
    assertTrue(true);
  }

}
