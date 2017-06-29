package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import model.Field.Symbol;

import java.util.Iterator;
import java.util.List;

public class Game implements Serializable{
	private static final long serialVersionUID = 2751443219936576304L;
	private Board board;
	private Map<String, int[]> directions = new HashMap<>();
	private static final int COORDROW = 0;
	private static final int COORDCOL = 1;

	/*
	 * Init board, init Map with directions
	 */
	public Game() {
		board = new Board();
		directions.put("up", new int[] { -1, 0 });
		directions.put("down", new int[] { 1, 0 });
		directions.put("left", new int[] { 0, -1 });
		directions.put("right", new int[] { 0, 1 });
	}

	/**
	 * Set the configuration of the board
	 * @param name
	 *            the configuration's name
	 */
	public void setConfiguration(String name) {
		board.setConfiguration(name);
	}

	/**
	 * Possible moves from a given position on the board
	 * 
	 * @param row
	 *            Row position on the board
	 * @param col
	 *            Column position on the board
	 * @return List of Integer arrays with the possible moves
	 */
	public List<int[]> possibleMoves(int row, int col) {
		List<int[]> moves = new ArrayList<>();
		Iterator<String> it = directions.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			if (canMove(row, col, directions.get(key))) {
				int[] newPos = getJumpMove(row, col, directions.get(key));
				moves.add(new int[] { newPos[COORDROW], newPos[COORDCOL] });
			}
		}
		return moves;
	}

	/**
	 * Get the direction depending on the start and end positions on the board
	 * 
	 * @param start
	 *            Integer array with the row ([0]) and col ([1]) of the start
	 *            position
	 * @param end
	 *            Integer array with the row ([0]) and col ([1]) of the end
	 *            position
	 * @return Integer array with the direction<br>
	 * 		An empty array otherwise
	 * 
	 */
	public int[] getDirection(int[] start, int[] end) {
		Iterator<String> it = directions.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			int[] direction = directions.get(key);
			int[] possibleEnd = getJumpMove(start[COORDROW], start[COORDCOL], direction);
			if (Arrays.equals(possibleEnd, end))
				return direction;
		}
		return new int[0];
	}

	/**
	 * Make a move on the board if its possible
	 * 
	 * @param start
	 *            Integer array with the row ([0]) and col ([1]) of the start
	 *            position
	 * @param end
	 *            Integer array with the row ([0]) and col ([1]) of the end
	 *            position
	 */
	public void makeMove(int[] start, int[] end) {
		List<int[]> moves = possibleMoves(start[COORDROW], start[COORDCOL]);
		boolean notExistsFlag = true;
		for (int[] item : moves) {
			if (Arrays.equals(item, new int[] { end[COORDROW], end[COORDCOL] })) {
				notExistsFlag = false;
				break;
			}
		}
		if (notExistsFlag)
			return;
		board.setHole(start[COORDROW], start[COORDCOL]);
		board.setPeg(end[COORDROW], end[COORDCOL]);
		int[] direction = getDirection(start, end);
		int[] neighbour = getNeighbor(start[COORDROW], start[COORDCOL], direction);
		board.setHole(neighbour[COORDROW], neighbour[COORDCOL]);
	}

	/**
	 * Check if the game is over, and if it is a win
	 * 
	 * @return <b>true</b> if there is one peg on the board<br>
	 * 		<b>false</b> otherwise
	 */
	public boolean isWin() {
		return board.getListPegs().size() == 1;
	}

	/**
	 * Check if the game is over, and if it is a lose
	 * 
	 * @return <b>true</b> if the pegs have no more possible moves<br>
	 * 		<b>false</b> otherwise
	 */
	public boolean isLose() {
		List<int[]> listPegs = board.getListPegs();
		for (int[] peg : listPegs) {
			int row = peg[COORDROW];
			int col = peg[COORDCOL];
			if (!possibleMoves(row, col).isEmpty())
				return false;
		}
		return true;
	}

	/**
	 * Get the neighbor of a given position on the board depending on the
	 * direction
	 * 
	 * @param row
	 *            Row position on the board
	 * @param col
	 *            Column position on the board
	 * @param direction
	 *            Direction of the desired move
	 * @return Integer array with position of the neighbor
	 */
	public int[] getNeighbor(int row, int col, int[] direction) {
		return new int[] { row + direction[COORDROW], col + direction[COORDCOL] };
	}

	/**
	 * Check if the peg in the position (row,col) can move in the desired
	 * direction
	 * 
	 * @param row
	 *            Row position on the board
	 * @param col
	 *            Column position on the board
	 * @param direction
	 *            Direction of the desired move
	 * @return <b>true</b> if this movement is possible<br>
	 *         <b>false</b> otherwise
	 */
	public boolean canMove(int row, int col, int[] direction) {
		int[] neighbour = getNeighbor(row, col, direction);
		if (board.isHole(neighbour[COORDROW], neighbour[COORDCOL]))
			return false;
		int jumpRow = neighbour[COORDROW] + direction[COORDROW];
		int jumpCol = neighbour[COORDCOL] + direction[COORDCOL];
		if (jumpRow > board.getSize() || jumpCol > board.getSize()) {
			return false;
		}
		return board.getSymbol(jumpRow, jumpCol) == Symbol.O;
	}

	/**
	 * Get the end position of a movement depending on the desired direction
	 * 
	 * @param row
	 *            Row position on the board
	 * @param col
	 *            Column position on the board
	 * @param direction
	 *            Direction of the desired move
	 * @return Integer array with the position of the movement
	 */
	public int[] getJumpMove(int row, int col, int[] direction) {
		return new int[] { row + 2 * direction[COORDROW], col + 2 * direction[COORDCOL] };
	}

	/**
	 * Print the board
	 */
	public void printBoard() {
		board.printBoard();
	}
}
