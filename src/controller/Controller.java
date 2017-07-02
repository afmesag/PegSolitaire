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
  private NButton startButtonSelected;

  /**
   * Constructor for Controller class, loads the "Figure 1" configuration by
   * default
   */
  public Controller() {
    this.game = new Game();
    this.game.setConfiguration("");
    this.view = new View();
    this.view.setCountMovementsTest(String.valueOf(this.game.getCountMovements()));
    addActionListeners();
  }

  /**
   * Returns an empty array, serves as a reseter for any array
   *
   * @return empty integer array
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
    return this.game.isWin() || this.game.isLose();
  }

  /**
   * restarts the game with a new frame and zero movements
   */
  public void restartGame() {
    this.view.dispose();
    this.restarted = true;
  }

  /**
   * Undo movements if there's any
   */
  public void undo() {
    LinkedList<Movement> movements = (LinkedList<Movement>) this.game.getMovements();
    if (!movements.isEmpty()) {
      Movement removed = movements.removeFirst();
      this.game.undo(removed);
      this.view.updatePeg(removed.getStart());
      this.view.updateHole(removed.getEnd());
      this.view.updatePeg(removed.getNeighbor());
      this.view.setCountMovementsTest(String.valueOf(this.game.getCountMovements()));
      if (movements.isEmpty()) {
        this.view.getUndoButton().setEnabled(false);
      }
    }
  }

  /**
   * Adds and action listener for every button in the game
   */
  public void addActionListeners() {
    for (int i = 0; i < this.view.getNumberOfPegButtons(); i++) {
      this.view.getPegButton(i).addActionListener(this);
    }
    for (int i = 0; i < this.view.getNumberOfHoleButtons(); i++) {
      this.view.getHoleButton(i).addActionListener(this);
    }
    this.view.getRestartButton().addActionListener(e -> restartGame());
    this.view.getUndoButton().addActionListener(e -> undo());
  }

  /**
   * Enables the undo button
   */
  public void enableUndoButton() {
    if (!this.view.getUndoButton().isEnabled()) {
      this.view.getUndoButton().setEnabled(true);
    }
  }

  /**
   * Does actions based on triggered events in the buttons of the game Serves
   * as a connector between the Model and the View
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (!this.game.isWin() || !this.game.isLose()) {
      if (start.length == 0) {
        startButtonSelected = ((NButton) e.getSource());
        this.view.setBackgroundSelected(startButtonSelected);
        start = this.view.getLocation(startButtonSelected);
      } else if (end.length == 0) {
        end = this.view.getLocation(((NButton) e.getSource()));
        int[] direction = this.game.getDirection(start, end);
        this.view.setBackgroundDeselected(startButtonSelected);
        if (this.game.makeMove(start, end)) {
          updateGrid(direction);
          enableUndoButton();
        }
        resetValues();
      }
    }
  }
  
  /**
   * Resets the values in the start and end arrays
   */
  public void resetValues() {
    start = resetArray();
    end = resetArray();
    startButtonSelected = null;
  }

  /**
   * Updates the grid of the game with a given direction
   * @param direction
   */
  public void updateGrid(int[] direction) {
    int[] neighbor = this.game.getNeighbor(start[0], start[1], direction);
    this.view.updateHole(start);
    this.view.updatePeg(end);
    this.view.updateHole(neighbor);
    this.view.setCountMovementsTest(String.valueOf(this.game.getCountMovements()));
  }

  /**
   * Informs of the outcome, win or lose
   */
  public void informOutcome() {
    if (this.game.isWin())
      this.view.informWin();
    if (this.game.isLose())
      this.view.informLose();
  }

  /**
   * checks if the game was restarted (restart button clicked)
   * @return
   */
  public boolean wereRestarted() {
    return this.restarted;
  }

}
