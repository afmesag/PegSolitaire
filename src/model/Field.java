package model;

public class Field {
	private Symbol symbol;

	public Field(Symbol symbol) {
		this.symbol = symbol;
	}

	public void setSymbol(Symbol newSymbol) {
		this.symbol = newSymbol;
	}

	public Symbol getSymbol() {
		return this.symbol;
	}

	public String toString() {
		return this.symbol.toString();
	}

	/*
	 * X = not hole I = peg O = hole
	 */
	public enum Symbol {
		X, I, O
	}
}
