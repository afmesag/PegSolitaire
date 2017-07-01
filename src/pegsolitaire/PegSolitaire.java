package pegsolitaire;

import controller.Controller;

public class PegSolitaire {
	
	private PegSolitaire(){}
	
	public static void main(String[] args) {

		Controller controller = new Controller();

		while (!controller.isGameOver()) {
			if(controller.wereRestarted())
				controller = new Controller();
			try {
				Thread.sleep(250);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
		controller.informOutcome();
	}

}
