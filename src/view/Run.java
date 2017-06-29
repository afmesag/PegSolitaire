/**
 * Basic class who runs the view frame application
 * must be erased when the controller is finished
 */
package view;

import model.Game;

public class Run {
	private static Game myGame = new Game();
	private static View myView = new View();
	private static Run myRun;

	public static void main(String[] args) {
		myRun = new Run();
		myGame.setConfiguration("Latin cross");
		while (!myGame.isWin() && !myGame.isLose()) {
			
		}
	}
}
