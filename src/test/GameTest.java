package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import model.Game;

public class GameTest {
	@Test
	public void testSetConfigurationLatinCross() {
		Game game = new Game();
		game.setConfiguration("Latin cross");
		String expected = "XXOOOXX\nXXOIOXX\nOOIIIOO\nOOOIOOO\nOOOIOOO\nXXOOOXX\nXXOOOXX";
		assertEquals(expected, game.printBoard());
	}

	@Test
	public void testSetConfigurationDefault() {
		Game game = new Game();
		game.setConfiguration("");
		String expected = "XXIIIXX\nXXIIIXX\nIIIIIII\nIIIOIII\nIIIIIII\nXXIIIXX\nXXIIIXX";
		assertEquals(expected, game.printBoard());
	}

	@Test
	public void testImpossibleToMove() {
		Game game = new Game();
		game.setConfiguration("");
		List<int[]> moves = game.possibleMoves(0, 2);
		assertTrue(moves.isEmpty());
	}

	@Test
	public void testPossibleToMove() {
		Game game = new Game();
		game.setConfiguration("");
		List<int[]> moves = game.possibleMoves(1, 3);
		int[] expected = { 3, 3 };
		for (int[] item : moves) {
			assertFalse(!Arrays.equals(expected, item));
		}
	}

	@Test
	public void testGetNoDirection() {
		Game game = new Game();
		game.setConfiguration("");
		int[] start = { 2, 0 };
		int[] end = { 2, 6 };
		int[] direction = game.getDirection(start, end);
		assertTrue(direction.length == 0);
	}

	@Test
	public void testGetUpDirection() {
		Game game = new Game();
		game.setConfiguration("");
		int[] start = { 4, 0 };
		int[] end = { 2, 0 };
		int[] direction = game.getDirection(start, end);
		int[] expected = { -1, 0 };
		assertTrue(Arrays.equals(expected, direction));
	}

	@Test
	public void testGetDownDirection() {
		Game game = new Game();
		game.setConfiguration("");
		int[] start = { 2, 0 };
		int[] end = { 4, 0 };
		int[] direction = game.getDirection(start, end);
		int[] expected = { 1, 0 };
		assertTrue(Arrays.equals(expected, direction));
	}

	@Test
	public void testGetLeftDirection() {
		Game game = new Game();
		game.setConfiguration("");
		int[] start = { 4, 2 };
		int[] end = { 4, 0 };
		int[] direction = game.getDirection(start, end);
		int[] expected = { 0, -1 };
		assertTrue(Arrays.equals(expected, direction));
	}

	@Test
	public void testGetRightDirection() {
		Game game = new Game();
		game.setConfiguration("");
		int[] start = { 4, 0 };
		int[] end = { 4, 2 };
		int[] direction = game.getDirection(start, end);
		int[] expected = { 0, 1 };
		assertTrue(Arrays.equals(expected, direction));
	}

	@Test
	public void testMakeNoMove() {
		Game game = new Game();
		game.setConfiguration("");
		int[] start = { 2, 0 };
		int[] end = { 2, 6 };
		assertFalse(game.makeMove(start, end));
	}

	@Test
	public void testMakeMove() {
		Game game = new Game();
		game.setConfiguration("");
		int[] start = { 1, 3 };
		int[] end = { 3, 3 };
		assertTrue(game.makeMove(start, end));
	}

	@Test
	public void testIsNotWin() {
		Game game = new Game();
		game.setConfiguration("Latin cross");
		assertFalse(game.isWin());
	}

	@Test
	public void testIsWin() {
		Game game = new Game();
		game.setConfiguration("Latin cross");
		int[][] moves = { { 2, 3 }, { 2, 5 }, { 4, 3 }, { 2, 3 }, { 2, 2 }, { 2, 4 }, { 2, 5 }, { 2, 3 }, { 1, 3 },
				{ 3, 3 } };
		int index = 0;
		while (index < moves.length)
			game.makeMove(moves[index++], moves[index++]);
		assertTrue(game.isWin());
	}

	@Test
	public void testGameOverNotLose() {
		Game game = new Game();
		game.setConfiguration("Latin cross");
		int[][] moves = { { 2, 3 }, { 2, 5 }, { 4, 3 }, { 2, 3 }, { 2, 2 }, { 2, 4 }, { 2, 5 }, { 2, 3 }, { 1, 3 },
				{ 3, 3 } };
		int index = 0;
		while (index < moves.length)
			game.makeMove(moves[index++], moves[index++]);
		assertFalse(game.isLose());
	}

	@Test
	public void testGameOverIsLose() {
		Game game = new Game();
		game.setConfiguration("Latin cross");
		int[][] moves = { { 3, 3 }, { 5, 3 }, { 1, 3 }, { 3, 3 } };
		int index = 0;
		while (index < moves.length)
			game.makeMove(moves[index++], moves[index++]);
		assertTrue(game.isLose());
	}

	@Test
	public void testNotGameOverNotLose() {
		Game game = new Game();
		game.setConfiguration("Latin cross");
		int[][] moves = { { 2, 3 }, { 2, 5 }, { 4, 3 }, { 2, 3 }, { 2, 2 }, { 2, 4 }, { 2, 5 }, { 2, 3 } };
		int index = 0;
		while (index < moves.length)
			game.makeMove(moves[index++], moves[index++]);
		assertFalse(game.isLose());
	}

	@Test
	public void testNeighborRowNegative() {
		Game game = new Game();
		int[] result = game.getNeighbor(0, 2, new int[] { -1, 0 });
		assertTrue(result.length == 0);
	}

	@Test
	public void testNeighborRowOverSize() {
		Game game = new Game();
		int[] result = game.getNeighbor(6, 2, new int[] { 1, 0 });
		assertTrue(result.length == 0);
	}

	@Test
	public void testNeighborRowUp() {
		Game game = new Game();
		int[] result = game.getNeighbor(1, 2, new int[] { -1, 0 });
		int[] expected = { 0, 2 };
		assertTrue(Arrays.equals(expected, result));
	}

	@Test
	public void testNeighborRowDown() {
		Game game = new Game();
		int[] result = game.getNeighbor(5, 2, new int[] { 1, 0 });
		int[] expected = { 6, 2 };
		assertTrue(Arrays.equals(expected, result));
	}

	@Test
	public void testNeighborColNegative() {
		Game game = new Game();
		int[] result = game.getNeighbor(2, 0, new int[] { 0, -1 });
		assertTrue(result.length == 0);
	}

	@Test
	public void testNeighborColOverSize() {
		Game game = new Game();
		int[] result = game.getNeighbor(2, 6, new int[] { 0, 1 });
		assertTrue(result.length == 0);
	}

	@Test
	public void testNeighborColLeft() {
		Game game = new Game();
		int[] result = game.getNeighbor(2, 1, new int[] { 0, -1 });
		int[] expected = { 2, 0 };
		assertTrue(Arrays.equals(expected, result));
	}

	@Test
	public void testNeighborColRigth() {
		Game game = new Game();
		int[] result = game.getNeighbor(2, 5, new int[] { 0, 1 });
		int[] expected = { 2, 6 };
		assertTrue(Arrays.equals(expected, result));
	}

	@Test
	public void testCanNotMoveEndRowNegative() {
		Game game = new Game();
		game.setConfiguration("");
		assertFalse(game.canMove(1, 2, new int[] { -1, 0 }));
	}

	@Test
	public void testCanNotMoveEndRowOverSize() {
		Game game = new Game();
		game.setConfiguration("");
		assertFalse(game.canMove(5, 2, new int[] { 1, 0 }));
	}

	@Test
	public void testCanNotMoveEndColNegative() {
		Game game = new Game();
		game.setConfiguration("");
		assertFalse(game.canMove(2, 1, new int[] { 0, -1 }));
	}

	@Test
	public void testCanNotMoveEndColOverSize() {
		Game game = new Game();
		game.setConfiguration("");
		assertFalse(game.canMove(2, 5, new int[] { 0, 1 }));
	}
}
