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
	private int[] start = {};
	private int[] end = {};
	
	public Controller(){
		this.game = new Game();
		this.game.setConfiguration("");
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
			if(start.length == 0){
				start = view.getLocation(((NButton)e.getSource()));
				System.out.println(start[0] + ", " + start[1]);
			}else if(end.length == 0){
				end = view.getLocation(((NButton)e.getSource()));
				System.out.println(end[0] + ", " + end[1]);
				game.makeMove(start, end);
			}
			System.out.println("=============================");
			System.out.println(game.printBoard());
		}
	}
	
	public boolean isGameOver(){
		return (game.isWin() || game.isLose()) ? true : false;  
	}
}
