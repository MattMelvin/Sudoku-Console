//****************************************************************
// Author: Matthew Melvin
// Assignment: Homework 1A: Console Sudoku
// Instructor: Dr. Yoonsik Cheon
// Purpose: A simple console based sudoku application
// Last Date Modified: 1/23/18
//****************************************************************

package edu.utep.cs.cs3331.sudoku;

public class Main {
	private static Board board = new Board();
	private static ConsoleUI ui = new ConsoleUI();
	
	//Calls all necessary methods
	public static void main(String[] args) {
		play();
		ConsoleUI.quit();
	}
	
	//Starts the game
	public static void play() {
		ui.welcome();
		int size = ui.askSize();
		board = new Board(size);
			
		while(!board.isSolved()) {
			if(size==4) {
				ConsoleUI.printCurrentBoardState4();
			}else {
				ConsoleUI.printCurrentBoardState9();
			}
			int[] nums = ConsoleUI.askNumbers();
			board.place(nums);
		}
		ui.congratulate();
	}
}
