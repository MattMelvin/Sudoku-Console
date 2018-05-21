//****************************************************************
// Author: Matthew Melvin
// Assignment: Homework 1A: Console Sudoku
// Instructor: Dr. Yoonsik Cheon
// Purpose: Console based UI for sudoku
// Last Date Modified: 1/23/18
//****************************************************************

package edu.utep.cs.cs3331.sudoku;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleUI {
	//Variables
	private static InputStream in;
	private static PrintStream out;

	//Default constructor
	public ConsoleUI() {
		in = System.in;
		out = System.out;
	}
	
	//Constructor receiving a pair of input and print streams
	public ConsoleUI(InputStream in, PrintStream out) {
		ConsoleUI.in = in;
		ConsoleUI.out = out;
	}
	
	//Welcomes the player to the game
	public void welcome() {
		out.println("Welcome to Sudoku!");
	}

	//Prompts the user to enter a board size
	public int askSize() {
		Scanner input = new Scanner(in);
		String temp = "";
		int size = 0;
		boolean flag = false;
		
		while(!flag) {
			out.print("Enter the size of the board (4 or 9): ");
			temp = input.nextLine();
			
			if(isInt(temp)) {
				size = Integer.parseInt(temp);
				
				if(size==4||size==9) {
					flag = true;
					break;
				}else {
					out.println("Input is not valid");
				}
			}else {
				out.println("Input is not valid");
			}
		}
		return size;
	}

	//Checks is a string contains a single integer
	private boolean isInt(String s) {
		try {
			Integer.parseInt(s);
		}catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	//Congratulates the player for completing the puzzle
	public void congratulate() {
		out.println("Congratulations, you solved the puzzle!");
	}

	//Prints the current state of 4x4 board in a 2D ASCII format
	public static void printCurrentBoardState4() {
		String T = "+-0-+-1-+-2-+-3-+";
		String R1 = "+---+---+---+---+";
		String R2 = "+===+===*===+===+";
		
		//Top
		out.println(T);
		printNumberSpaces4(0);
		out.println(R1);
		printNumberSpaces4(1);
		//Bottom
		out.println(R2);
		printNumberSpaces4(2);
		out.println(R1);
		printNumberSpaces4(3);
		out.println(R2);
	}
	
	//Prints the current state of 9x9 board in a 2D ASCII format
	public static void printCurrentBoardState9() {
		String T = "+-0-+-1-+-2-+-3-+-4-+-5-+-6-+-7-+-8-+";
		String R1 = "+---+---+---+---+---+---+---+---+---+";
		String R2 = "+===+===+===*===+===+===*===+===+===+";

		//Top
		out.println(T);
		printNumberSpaces9(0);
		out.println(R1);
		printNumberSpaces9(1);
		out.println(R1);
		printNumberSpaces9(2);
		out.println(R2);
		//Middle
		printNumberSpaces9(3);
		out.println(R1);
		printNumberSpaces9(4);
		out.println(R1);
		printNumberSpaces9(5);
		out.println(R2);
		//Bottom
		printNumberSpaces9(6);
		out.println(R1);
		printNumberSpaces9(7);
		out.println(R1);
		printNumberSpaces9(8);
		out.println(R1);
	}
	
	//Prints row with number spaces for 4x4 board
	public static void printNumberSpaces4(int r) {
		int[][] dump = Board.getBoard();
		
		for(int i=0;i<dump.length;i++) {
			if(i==2) {
				if(dump[r][i]==0) {
					out.print("!   ");
				}else {
					out.print("! "+dump[r][i]+" ");
				}
			}else if(i==0) {
				if(dump[r][i]==0) {
					out.print(r+"   ");
				}else {
					out.print(r+" "+dump[r][i]+" ");
				}
			}else {
				if(dump[r][i]==0) {
					out.print("|   ");
				}else {
					out.print("| "+dump[r][i]+" ");
				}
			}
		}
		out.printf("|\n");
	}
	
	//Prints row with number spaces for 9x9
	public static void printNumberSpaces9(int r) {
		int[][] dump = Board.getBoard();
		
		for(int i=0;i<dump.length;i++) {
			if(i%3==0&&i!=0) {
				if(dump[r][i]==0) {
					out.print("!   ");
				}else {
					out.print("! "+dump[r][i]+" ");
				}
			}else if(i==0) {
				if(dump[r][i]==0) {
					out.print(r+"   ");
				}else {
					out.print(r+" "+dump[r][i]+" ");
				}
			}else {
				if(dump[r][i]==0) {
					out.print("|   ");
				}else {
					out.print("| "+dump[r][i]+" ");
				}
			}
		}
		out.printf("|\n");
	}
	
	//Prompts user to enter the coordinates and guess
	public static int[] askNumbers() {
		Scanner input = new Scanner(in);
		String temp = "";
		String[] numsS = new String[3];
		int[] nums = new int[3]; //{X,Y,A}
			
		while(true) {
			out.print("Enter a number (x y a) or -1 to quit: ");
			temp = input.nextLine();
			numsS = temp.split(" ");
			
			if(isIntArr(numsS)) {
				break;
			}else {
				out.println("Input is not valid");
			}
		}
		
		for(int i=0;i<3;i++) {
			nums[i] = Integer.parseInt(numsS[i]);
			if(nums[0]==-1) {
				quit();
			}
		}
		return nums;
	}
	
	//Checks if a string array contains 3 integers
	private static boolean isIntArr(String[] s) {
		try {
			Integer.parseInt(s[0]);
			if(Integer.parseInt(s[0])==-1) {
				quit();
			}
			Integer.parseInt(s[1]);
			Integer.parseInt(s[2]);
		}catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	//Quits the game
	public static void quit() {
		out.println("Thanks for playing!");
		System.exit(1);	
	}
}
