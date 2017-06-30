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
	public void actionPerformed(ActionEvent e) {
		if(!(game.isWin()) || !(game.isLose())){
			int[] coords = view.getLocation(((NButton)e.getSource()));
			System.out.println(coords[0] + ", " + coords[1]);
		}
	}
	
	public boolean isGameOver(){
		return (game.isWin() || game.isLose()) ? true : false;  
	}
}
