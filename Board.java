//****************************************************************
// Author: Matthew Melvin
// Assignment: Homework 1A: Console Sudoku
// Instructor: Dr. Yoonsik Cheon
// Purpose: Board class for sudoku application
// Last Date Modified: 1/23/18
//****************************************************************

package edu.utep.cs.cs3331.sudoku;

public class Board {
	//Variables
	private int size;
	private static int[][] board;
	
	//Default constructor
	public Board() {
		
	}
	
	//Constructor receiving board size
	public Board(int size) {
		this.size = size;
		Board.board = new int[size][size];
	}

	//Determines if the board has been solved
	public boolean isSolved() {
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(board[i][j]==0) {
					return false;
				}
			}
		}
		return true;
	}

	//Trys to insert the number in a certain location
	public void place(int[] n) {
		//{X,Y,A}
		int x, y, a;
		x = n[0];
		y = n[1];
		a = n[2];
		
		if(inRange(a)) {
			if(isValid(x,y,a)) {
				board[y][x] = a;
			}
		}
	}

	//Checks if the number trying to be inserted is valid at that location
	private boolean isValid(int x, int y, int a) {
		if(!checkRow(x,y,a)) {
			return false;
		}if(!checkColumn(x,y,a)) {
			return false;
		}if(!checkSubgrid(x,y,a)) {
			return false;
		}
		return true;
	}
	
	//Checks the validity of a number in a certain subgrid
	private boolean checkSubgrid(int x, int y, int a) {
		int sub = (int)(Math.sqrt(size));
		int row = y-y%sub;
		int column = x-x%sub;
		
		for(int i=row;i<row+sub;i++) {
			for(int j=column;j<column+sub;j++) {
				if(board[i][j]==a) {
					System.out.println("Invalid "+x+" "+y+" "+a);
					return false;
				}
			}
		}
		return true;
	}
	
	//Checks the validity of a number in a certain column
	private boolean checkColumn(int x, int y, int a) {
		for(int i=0;i<size;i++) {
			if(board[i][x]==a) {
				System.out.println("Invalid "+x+" "+y+" "+a);
				return false;
			}
		}
		return true;
	}

	//Checks the validity of a number in a certain row
	private boolean checkRow(int x, int y, int a) {
		for(int i=0;i<size;i++) {
			if(board[y][i]==a) {
				System.out.println("Invalid "+x+" "+y+" "+a);
				return false;
			}
		}
		return true;
	}

	//Checks if the number trying to be inserted is in the range of the board
	private boolean inRange(int a) {
		if(a<1||a>size) {
			return false;
		}else {
			return true;	
		}
	}

	//Returns the current board (for ConsoleUI)
	public static int[][] getBoard() {
		return board;
	}
}
