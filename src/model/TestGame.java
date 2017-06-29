package model;

import java.util.Scanner;

public class TestGame {

	public static void main(String[] args) {
		Game mygame = new Game();
		mygame.setConfiguration("Latin cross");
		System.out.println("----------------------");
		mygame.printBoard();
		while (!mygame.isWin() && !mygame.isLose()) {
			Scanner scanner = new Scanner(System.in);
			String line = scanner.nextLine();
			int[] start = { Integer.parseInt(Character.toString(line.charAt(0))),
					Integer.parseInt(Character.toString(line.charAt(2))) };
			line = scanner.nextLine();
			int[] end = { Integer.parseInt(Character.toString(line.charAt(0))),
					Integer.parseInt(Character.toString(line.charAt(2))) };
			mygame.makeMove(start, end);
			System.out.println("----------------------");
			mygame.printBoard();
		}
		if (mygame.isWin()) {
			System.out.print("YOU WON!! :D :D");
		} else if (mygame.isLose()) {
			System.out.print("YOU LOSE!! :( :(");
		}

	}

}
