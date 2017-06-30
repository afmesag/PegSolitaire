package test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;

import org.junit.Test;

import model.Game;

public class GameTest {
    private Random random = new Random();
    private static final int SIZE = 7;
    private static final int SIZECORNER = 2;

    @Test
    public void testGame() {
        Game game = new Game();
        assertEquals(Game.class, game.getClass());
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
        int row = -1;
        int col = -1;
    }

    @Test
    public void testGetDirection() {
        Game game = new Game();
        game.setConfiguration("");
        int[] start = new int[2];
        int[] col = new int[2];

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
