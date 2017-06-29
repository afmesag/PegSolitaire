package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import model.Field.Symbol;

import java.util.Iterator;
import java.util.List;

public class Game {
	private Board board;
	private Map<String, int[]> directions = new HashMap<>();
	private static final int COORDROW = 0;
	private static final int COORDCOL = 1;

	public Game() {
		board = new Board();
		directions.put("up", new int[] { -1, 0 });
		directions.put("down", new int[] { 1, 0 });
		directions.put("left", new int[] { 0, -1 });
		directions.put("right", new int[] { 0, 1 });
	}

	public void setConfiguration(String name) {
		board.setConfiguration(name);
	}

	public List<int[]> possibleMoves(int row, int col) {
		List<int[]> moves = new ArrayList<>();
		Iterator<String> it = directions.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			if (canMove(row, col, directions.get(key))) {
				int[] newPos = moveTo(row, col, directions.get(key));
				moves.add(new int[] { newPos[COORDROW], newPos[COORDCOL] });
			}
		}
		return moves;
	}

	public int[] getDirection(int[] start, int[] end) {
		Iterator<String> it = directions.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			int[] direction = directions.get(key);
			int[] possibleEnd = moveTo(start[COORDROW], start[COORDCOL], direction);
			if (Arrays.equals(possibleEnd, end))
				return direction;
		}
		return new int[0];
	}

	public boolean makeMove(int[] start, int[] end) {
		List<int[]> moves = possibleMoves(start[COORDROW], start[COORDCOL]);
		boolean notExistsFlag = true;
		for (int[] item : moves) {
			if (Arrays.equals(item, new int[] { end[COORDROW], end[COORDCOL] })) {
				notExistsFlag = false;
				break;
			}
		}
		if (notExistsFlag)
			return false;
		board.setHole(start[COORDROW], start[COORDCOL]);
		board.setPeg(end[COORDROW], end[COORDCOL]);
		int[] direction = getDirection(start, end);
		int[] neighbour = getNeighbour(start[COORDROW], start[COORDCOL], direction);
		board.setHole(neighbour[COORDROW], neighbour[COORDCOL]);
		return true;
	}

	public boolean isWin() {
		return board.getListPegs().size() == 1;
	}

	public boolean isLose() {
		List<int[]> listPegs = board.getListPegs();
		for (int[] peg : listPegs) {
			int row = peg[COORDROW];
			int col = peg[COORDCOL];
			if (board.getSymbol(row, col) == Symbol.I && !possibleMoves(row, col).isEmpty())
				return false;
		}
		return true;
	}

	public int[] getNeighbour(int row, int col, int[] direction) {
		return new int[] { row + direction[0], col + direction[1] };
	}

	public boolean canMove(int row, int col, int[] direction) {
		int[] neighbour = getNeighbour(row, col, direction);
		if (board.isHole(neighbour[0], neighbour[1]))
			return false;
		int jumpRow = neighbour[0] + direction[0];
		int jumpCol = neighbour[1] + direction[1];
		if (jumpRow > board.getSize() || jumpCol > board.getSize()) {
			return false;
		}
		return board.getSymbol(jumpRow, jumpCol) == Symbol.O;
	}

	public int[] moveTo(int row, int col, int[] direction) {
		return new int[] { row + 2 * direction[0], col + 2 * direction[1] };
	}

	public void printBoard() {
		board.printBoard();
	}
}
