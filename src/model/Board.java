package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Field.Symbol;

public class Board implements Serializable {
  private static final long serialVersionUID = -7675633109310448246L;
  private static final int SIZE = 7;
  private static final int SIZECORNER = 2;
  private Field[][] gameGrid = new Field[SIZE][SIZE];
  private List<int[]> listPegs = new ArrayList<>();

  /**
   * Initialize Board, the corners of the Board are setted with X of not hole,
   * and the other fields are holes
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

  /**
   * Return the symbol of the field that is in the position (row,col) on the
   * board
   *
   * @param row Row position on the board
   * @param col Column position on the board
   * @return <b>O</b> Hole<br>
   * <b>I</b> Peg<br>
   * <b>X</b> Not hole
   */
  public Symbol getSymbol(int row, int col) {
    return this.gameGrid[row][col].getSymbol();
  }

  /**
   * Return the size of the board
   *
   * @return Integer with the size of the board
   */
  public int getSize() {
    return Board.SIZE;
  }

  /**
   * Set the configuration of the board
   *
   * @param name The configuration's name
   */
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

  /**
   * Set the default configuration, Greek cross
   */
  public void setDefault() {
    for (int row = 0; row < SIZE; row++) {
      for (int col = 0; col < SIZE; col++) {
        if (this.gameGrid[row][col].getSymbol().equals(Symbol.X) || (row == (SIZE / 2) && col == (SIZE / 2)))
          continue;
        else{
          this.gameGrid[row][col].setSymbol(Symbol.I);
          this.listPegs.add(new int[]{row,col});
        }
      }
    }
  }

  /**
   * Set the Latin cross configuration
   */
  public void setLatinCross() {
    int[][] positions = {{1, 3}, {2, 3}, {3, 3}, {4, 3}, {2, 2}, {2, 4}};
    for (int[] position : positions) {
      this.listPegs.add(position);
      this.gameGrid[position[0]][position[1]].setSymbol(Symbol.I);
    }
  }

  /**
   * Return if there is a peg in (row,col)
   *
   * @param row Row position on the board
   * @param col Column position on the board
   * @return <b>true</b> There is a peg<br>
   * <b>false</b> otherwise
   */
  public boolean isPeg(int row, int col) {
    return this.gameGrid[row][col].getSymbol() == Symbol.I;
  }

  /**
   * Return if there is a hole in (row,col)
   *
   * @param row Row position on the board
   * @param col Column position on the board
   * @return <b>true</b> There is a hole<br>
   * <b>false</b> otherwise
   */
  public boolean isHole(int row, int col) {
    return this.gameGrid[row][col].getSymbol() == Symbol.O;
  }

  /**
   * Set a peg in the position (row,col)
   *
   * @param row Row position on the board
   * @param col Column position on the board
   */
  public void setPeg(int row, int col) {
    this.gameGrid[row][col].setSymbol(Symbol.I);
    this.listPegs.add(new int[]{row, col});
  }

  /**
   * Set a hole in the position (row,col)
   *
   * @param row Row position on the board
   * @param col Column position on the board
   */
  public void setHole(int row, int col) {
    this.gameGrid[row][col].setSymbol(Symbol.O);
    int index = 0;
    for (int[] item : this.listPegs) {
      if (Arrays.equals(item, new int[]{row, col})) {
        this.listPegs.remove(index);
        break;
      }
      index++;
    }
  }

  /**
   * Return the list of pegs
   *
   * @return List of pegs
   */
  public List<int[]> getListPegs() {
    return this.listPegs;
  }

  /**
   * Print the board
   */
  public String printBoard() {
    StringBuilder board = new StringBuilder();
    for (int row = 0; row < SIZE; row++) {
      for (int col = 0; col < SIZE; col++) {
        board.append(this.gameGrid[row][col]);
      }
      board.append("\n");
    }
    return board.toString().substring(0, board.length() - 1);
  }
}
