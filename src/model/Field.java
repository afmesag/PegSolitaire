package model;

import java.io.Serializable;

public class Field implements Serializable {
  private static final long serialVersionUID = 4807993420635437867L;
  private Symbol symbol;

  /**
   * Initialize the field with the given Symbol
   *
   * @param symbol Symbol (<b>I,O,X</b>)
   */
  public Field(Symbol symbol) {
    this.symbol = symbol;
  }

  /**
   * Set the symbol of the field
   *
   * @param newSymbol Symbol (<b>I,O,X</b>)
   */
  public void setSymbol(Symbol newSymbol) {
    this.symbol = newSymbol;
  }

  /**
   * Get the symbol of the field
   *
   * @return Symbol (<b>I,O,X</b>)
   */
  public Symbol getSymbol() {
    return this.symbol;
  }

  /**
   * Return the Symbol
   */
  public String toString() {
    return this.symbol.toString();
  }

  /**
   * <b>X</b> = Not hole<br>
   * <b>I</b> = Peg<br>
   * <b>O</b> = Hole
   */
  public enum Symbol {
    X, I, O
  }
}
