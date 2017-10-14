package com.JayPi;

public class MinMax extends Bot {

	private Field f;

	public MinMax(Field f) {
		super(f);
	}

	public void setField(Field f) {
		this.f = f;
	}

	@Override
	int[] move() {
		System.out.println(heuristic(this.f));

		return null;
	}

	private int heuristic(Field f) {
		int score = 0;
		int[] myCol = new int[f.getFieldSize()];
		int[] oppCol = new int[f.getFieldSize()];

		for (int i = 0; i < f.getFieldSize(); i++) {
			myCol[i] = 0;
			oppCol[i] = 0;
		}

		for (int i = 0; i < f.getFieldSize(); i++) {
			Cell.States s = f.getCell(0, i).getState();
			System.out.println(s);
			if (s == this.myState)
				myCol[0]++;
			else if (s == this.oppState)
				oppCol[0]++;
			s = f.getCell(1, i).getState();

			if (s == this.myState)
				myCol[1]++;
			else if (s == this.oppState)
				oppCol[1]++;
			s = f.getCell(2, i).getState();

			if (s == this.myState)
				myCol[2]++;
			else if (s == this.oppState)
				oppCol[2]++;

		}

		for (int i = 0; i < f.getFieldSize(); i++) {
			if (oppCol[i] == 0) {
				if (myCol[i] == 0)
					score += 0;
				else if (myCol[i] == 1)
					score += 1;
				else if (myCol[i] == 2)
					score += 10;
				else if (myCol[i] == 3)
					score += 100;
			}
			if (myCol[i] == 0) {
				if (oppCol[i] == 0)
					score -= 0;
				else if (oppCol[i] == 1)
					score -= 1;
				else if (oppCol[i] == 2)
					score -= 10;
				else if (oppCol[i] == 3)
					score -= 100;
			}

		}

		int[] myRow = new int[f.getFieldSize()];
		int[] oppRow = new int[f.getFieldSize()];

		for (int i = 0; i < f.getFieldSize(); i++) {
			myRow[i] = 0;
			oppRow[i] = 0;
		}

		for (int i = 0; i < f.getFieldSize(); i++) {
			Cell.States s = f.getCell(i, 0).getState();
			if (s == this.myState)
				myRow[0]++;
			else if (s == this.oppState)
				oppRow[0]++;
			s = f.getCell(i, 1).getState();
			if (s == this.myState)
				myRow[1]++;
			else if (s == this.oppState)
				oppRow[1]++;
			s = f.getCell(i, 2).getState();
			if (s == this.myState)
				myRow[2]++;
			else if (s == this.oppState)
				oppRow[2]++;

		}

		for (int i = 0; i < f.getFieldSize(); i++) {
			if (oppRow[i] == 0) {
				if (myRow[i] == 0)
					score += 0;
				else if (myRow[i] == 1)
					score += 1;
				else if (myRow[i] == 2)
					score += 10;
				else if (myRow[i] == 3)
					score += 100;
			}
			if (myRow[i] == 0) {
				if (oppRow[i] == 0)
					score += 0;
				else if (oppRow[i] == 1)
					score -= 1;
				else if (oppRow[i] == 2)
					score -= 10;
				else if (oppRow[i] == 3)
					score -= 100;
			}
		}

		int myDiag = 0;
		int myRDiag = 0;
		int oppDiag = 0;
		int oppRDiag = 0;
		for (int i = 0; i < f.getFieldSize(); i++) {
			Cell.States s = f.getCell(i, i).getState();
			if (s == this.myState) {
				myDiag++;
			} else if (s == this.oppState) {
				oppDiag++;
			}
			s = f.getCell(i, f.getFieldSize() - 1 - i).getState();
			if (s == this.myState) {
				myRDiag++;
			} else if (s == this.oppState) {
				oppRDiag++;
			}
		}

		if (oppDiag == 0) {
			switch (myDiag) {
			case 0:
				score += 0;
				break;
			case 1:
				score += 1;
				break;
			case 2:
				score += 10;
				break;
			case 3:
				score += 100;
				break;
			}
		} else if (myDiag == 0) {
			switch (oppDiag) {
			case 0:
				score -= 0;
				break;
			case 1:
				score -= 1;
				break;
			case 2:
				score -= 10;
				break;
			case 3:
				score -= 100;
				break;
			}
		}
		if (oppRDiag == 0) {
			switch (myRDiag) {
			case 0:
				score += 0;
				break;
			case 1:
				score += 1;
				break;
			case 2:
				score += 10;
				break;
			case 3:
				score += 100;
				break;
			}
		} else if (myRDiag == 0) {
			switch (oppRDiag) {
			case 0:
				score -= 0;
				break;
			case 1:
				score -= 1;
				break;
			case 2:
				score -= 10;
				break;
			case 3:
				score -= 100;
				break;
			}
		}
		return score;
	}

}
