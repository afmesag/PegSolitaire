package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import model.Game;
import view.View;
import entities.Movement;
import entities.NButton;

public class Controller implements ActionListener {

	private Game game;
	private View view;
	private int[] start = {};
	private int[] end = {};
	private boolean restarted = false;

	/**
	 * Constructor for Controller class, loads the "Figure 1" configuration by
	 * default
	 */
	public Controller() {
		this.game = new Game();
		this.game.setConfiguration("");
		this.view = new View();
		addActionListeners();
	}

	/**
	 * Returns an empty array, serves as a reseter for any array
	 * 
	 * @return
	 */
	public int[] resetArray() {
		return new int[0];
	}

	/**
	 * Ask for the model's class game if the movements given by the user are a
	 * win or a lose therefore knowing if the game is over
	 * 
	 * @return true if the game has ended, false otherwise
	 */
	public boolean isGameOver() {
		return (game.isWin() || game.isLose()) ? true : false;
	}

	/**
	 * restarts the game with a new frame and zero movements
	 */
	public void restartGame() {
		view.dispose();
		this.restarted = true;
	}

	/**
	 * Undo movements if there's any
	 */
	public void undo() {
		LinkedList<Movement> movements = (LinkedList<Movement>) game.getMovements();
		if (!movements.isEmpty()) {
			Movement removed = movements.removeFirst();
			view.updatePeg(removed.getStart());
			view.updateHole(removed.getEnd());
			view.updatePeg(removed.getNeighbor());
			if (movements.isEmpty()) {
				view.getUndoButton().setEnabled(false);
			}
		}
	}

	/**
	 * Adds and action listener for every button in the game
	 */
	public void addActionListeners() {
		for (int i = 0; i < view.getNumberOfPegButtons(); i++) {
			this.view.getPegButton(i).addActionListener(this);
		}
		for (int i = 0; i < view.getNumberOfHoleButtons(); i++) {
			this.view.getHoleButton(i).addActionListener(this);
		}
		this.view.getRestartButton().addActionListener(e -> restartGame());
		this.view.getUndoButton().addActionListener(e -> undo());
	}

	/**
	 * Enables the undo button
	 */
	public void enableUndoButton() {
		if (!view.getUndoButton().isEnabled()) {
			view.getUndoButton().setEnabled(true);
		}
	}

	/**
	 * Does actions based on triggered events in the buttons of the game Serves
	 * as a connector between the Model and the View
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (!(game.isWin()) || !(game.isLose())) {
			if (start.length == 0) {
				start = view.getLocation(((NButton) e.getSource()));
			} else if (end.length == 0) {
				end = view.getLocation(((NButton) e.getSource()));
				int[] direction = game.getDirection(start, end);
				if (game.makeMove(start, end)) {
					int[] neighbor = game.getNeighbor(start[0], start[1], direction);
					view.updateHole(start);
					view.updatePeg(end);
					view.updateHole(neighbor);
					enableUndoButton();
				}
				start = resetArray();
				end = resetArray();
			}
		}
	}

	public void informOutcome() {
		if (game.isWin())
			view.informWin();
		if (game.isLose())
			view.informLose();
	}

	public boolean wereRestarted() {
		return this.restarted;
	}

}
