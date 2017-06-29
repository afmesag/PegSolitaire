package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Field.Symbol;

public class Board {
	private static final int SIZE = 7;
	private static final int SIZECORNER = 2;
	private Field[][] gameGrid = new Field[SIZE][SIZE];
	private List<int[]> listPegs = new ArrayList<>();

	/*
	 * Initialize Board, the corners of the Board are setted with X of not hole
	 */
	public Board() {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				this.gameGrid[row][col] = new Field(Symbol.O);
			}
		}
		for (int row = 0; row < SIZECORNER; row++) {
			for (int col = 0; col < SIZECORNER; col++) {
				this.gameGrid[row][col].setSymbol(Symbol.X);
				this.gameGrid[row][SIZE - col - 1].setSymbol(Symbol.X);
				this.gameGrid[SIZE - row - 1][col].setSymbol(Symbol.X);
				this.gameGrid[SIZE - row - 1][SIZE - col - 1].setSymbol(Symbol.X);
			}
		}
	}

	public Symbol getSymbol(int row, int col) {
		return this.gameGrid[row][col].getSymbol();
	}

	public int getSize() {
		return Board.SIZE;
	}

	public void setConfiguration(String name) {
		switch (name) {
		case "Latin cross":
			setLatinCross();
			break;
		default:
			setDefault();
			break;
		}
	}

	/*
	 * Set the peg on the positions that are not in the corners and in the
	 * middle of the grid
	 */
	public void setDefault() {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				if (this.gameGrid[row][col].getSymbol().equals(Symbol.X) || (row == (SIZE / 2) && col == (SIZE / 2)))
					continue;
				else
					this.gameGrid[row][col].setSymbol(Symbol.I);
			}
		}
	}

	public void setLatinCross() {
		int[][] positions = { { 1, 3 }, { 2, 3 }, { 3, 3 }, { 4, 3 }, { 2, 2 }, { 2, 4 } };
		for (int[] position : positions) {
			this.listPegs.add(position);
			this.gameGrid[position[0]][position[1]].setSymbol(Symbol.I);
		}
	}

	public boolean isPeg(int row, int col) {
		return this.gameGrid[row][col].getSymbol() == Symbol.I;
	}

	public boolean isHole(int row, int col) {
		return this.gameGrid[row][col].getSymbol() == Symbol.O;
	}

	public void setPeg(int row, int col) {
		this.gameGrid[row][col].setSymbol(Symbol.I);
		this.listPegs.add(new int[] { row, col });
	}

	public void setHole(int row, int col) {
		this.gameGrid[row][col].setSymbol(Symbol.O);
		int index = 0;
		for (int[] item : this.listPegs) {
			if (Arrays.equals(item, new int[] { row, col })) {
				this.listPegs.remove(index);
				break;
			}
			index++;
		}
	}

	public List<int[]> getListPegs() {
		return this.listPegs;
	}

	public void printBoard() {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				System.out.print(this.gameGrid[row][col]);
			}
			System.out.println();
		}
	}
}
