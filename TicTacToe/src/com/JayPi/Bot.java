package com.JayPi;

//https://www.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe_AI.html
//Die Bots sind alle inspiriert von dieser Website

public abstract class Bot {

	protected Field field;
	protected int cols;
	protected int rows;
	protected Cell.States myState;
	protected Cell.States oppState;

	protected Cell[][] cells;

	public Bot(Field f) {
		this.field = f;

		this.cols = field.getFieldSize();
		this.rows = field.getFieldSize();
		cells = new Cell[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				cells[i][j] = field.getCell(j, i);
			}
		}
	}

	public void setSeed(Cell.States s) {
		myState = s;
		oppState = s == Cell.States.PLAYER1 ? Cell.States.PLAYER2 : Cell.States.PLAYER1;
	}

	abstract int[] move();

}