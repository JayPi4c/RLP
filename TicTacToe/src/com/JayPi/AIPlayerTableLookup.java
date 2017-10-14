package com.JayPi;

public class AIPlayerTableLookup extends Bot {

	private int[][] preferredMoves = { { 1, 1 }, { 0, 0 }, { 0, 2 }, { 2, 2 }, { 0, 1 }, { 1, 0 }, { 1, 2 }, { 2, 1 },
			{ 2, 0 } };

	public AIPlayerTableLookup(Field f) {
		super(f);
	}

	@Override
	int[] move() {
		for (int[] moves : preferredMoves) {
			if (cells[moves[0]][moves[1]].getState() == Cell.States.UNUSED)
				return moves;
		}
		// assert false : "No empty cell?!";
		return null;
	}

}
