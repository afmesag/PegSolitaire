package model;

import model.Field.Symbol;

public class Board {
	private static int size = 7;
	private static int sizeCorner = 2;
	private Field[][] gameGrid = new Field[size][size];

	/*
	 * Initialize Board, the corners of the Board are setted with X of not hole
	 */
	public Board() {
		for (int row = 0; row < sizeCorner; row++) {
			for (int col = 0; col < sizeCorner; col++) {
				gameGrid[row][col].setSymbol(Symbol.X);
				gameGrid[row][size - col - 1].setSymbol(Symbol.X);
				gameGrid[size - row - 1][col].setSymbol(Symbol.X);
				gameGrid[size - row - 1][size - col - 1].setSymbol(Symbol.X);
			}
		}
	}

	public void setConfiguration(String name) {
		switch (name) {
		case "Latin cross":

			break;
		default:
			setDefault();
			break;
		}
	}

	/*
	 * 
	 */
	public void setDefault() {
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if (gameGrid[row][col].getSymbol().equals(Symbol.X) || (row == (size / 2) && col == (size / 2)))
					continue;
				gameGrid[row][col].setSymbol(Symbol.I);
			}
		}
	}

	public void printBoard() {
		for (int row = 0; row < size; row++) {
			for (int col = 0; row < size; col++) {
				System.out.print(gameGrid[row][col]);
			}
			System.out.println();
		}
	}
}
