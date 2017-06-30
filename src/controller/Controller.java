package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Game;
import view.View;
import entities.NButton;

public class Controller implements ActionListener {
	
	private Game game;
	private View view;
	
	public Controller(){
		this.game = new Game();
		this.view = new View();
		addActionListeners();
	}
	
	public void addActionListeners(){
			for (int i = 0; i < view.getNumberOfPegButtons(); i++) {
	            this.view.getPegButton(i).addActionListener(this);
	        }
			for (int i = 0; i < view.getNumberOfHoleButtons(); i++) {
	            this.view.getHoleButton(i).addActionListener(this);
	        }
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(!(game.isWin()) || !(game.isLose())){
			for(NButton button : view.getPegButtons() )
				System.out.println(button.getGridx() + ", " + button.getGridy());
			}
		
	}
	
	public boolean isGameOver(){
		return (game.isWin() || game.isLose()) ? true : false;  
	}
}
