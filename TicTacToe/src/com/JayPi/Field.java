package com.JayPi;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Field {
	private Cell field[][];

	private ArrayList<Field> moves;
	private int fieldSize;

	public Field(int n, int radius) {
		fieldSize = n;
		// winner = null;
		moves = new ArrayList<Field>();
		field = new Cell[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				field[i][j] = new Cell(i, j, radius);
			}
		}
		moves.add(this);
	}

	public void show(Graphics g) {
		for (int i = 0; i < field.length; i++) {
			for (Cell c : field[i]) {
				c.show(g);
			}
		}
	}

	public Cell getCell(int i, int j) {
		return this.field[i][j];
	}

	public Cell getCell(Point p) {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if (field[i][j].contains(p))
					return field[i][j];
			}
		}
		return null;
	}

	public boolean checkWinning(Cell.States s, Cell c) {
		int col = 0;
		int row = 0;
		int diag = 0;
		int rdiag = 0;

		int j = c.getJ();
		int i = c.getI();

		for (int k = 0; k < field.length; k++) {
			if (field[i][k].getState() == s)
				col++;
			if (field[k][j].getState() == s)
				row++;
			if (field[k][k].getState() == s)
				diag++;
			if (field[field.length - 1 - k][k].getState() == s)
				rdiag++;
		}
		if (col == this.fieldSize || row == this.fieldSize || diag == this.fieldSize || rdiag == this.fieldSize)
			return true;
		return false;
	}

	public boolean checkDraw() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if (field[i][j].getState() == Cell.States.UNUSED)
					return false;
			}
		}
		return true;
	}

	public Field updateField(Cell c) {
		if (c.getState() == Cell.States.UNUSED) {
			Cell.States state = moves.size() % 2 == 1 ? Cell.States.PLAYER1 : Cell.States.PLAYER2;
			c.setState(state);
			moves.add(this);
		}
		return this;
	}

	public Cell.States getCurrentPlayer() {
		return moves.size() % 2 == 1 ? Cell.States.PLAYER1 : Cell.States.PLAYER2;
	}

	public boolean checkWinningForAll(Cell.States s) {
		byte row1 = 0;
		byte row2 = 0;
		byte row3 = 0;
		for (int i = 0; i < this.fieldSize; i++) {
			if (this.field[i][0].getState() == s)
				row1++;
			if (this.field[i][1].getState() == s)
				row2++;
			if (this.field[i][2].getState() == s)
				row3++;
		}
		if (row1 == fieldSize || row2 == fieldSize || row3 == fieldSize)
			return true;

		byte col1 = 0;
		byte col2 = 0;
		byte col3 = 0;
		for (int i = 0; i < this.fieldSize; i++) {
			if (this.field[0][i].getState() == s)
				col1++;
			if (this.field[1][i].getState() == s)
				col2++;
			if (this.field[2][i].getState() == s)
				col3++;
		}
		if (col1 == fieldSize || col2 == fieldSize || col3 == fieldSize)
			return true;

		byte diag = 0;
		byte rdiag = 0;
		for (int i = 0; i < fieldSize; i++) {
			if (field[i][i].getState() == s)
				diag++;
			if (field[i][fieldSize - 1 - i].getState() == s)
				rdiag++;
		}
		if (diag == fieldSize || rdiag == fieldSize)
			return true;
		return false;
	}

	public int getFieldSize() {
		return this.fieldSize;
	}
}
