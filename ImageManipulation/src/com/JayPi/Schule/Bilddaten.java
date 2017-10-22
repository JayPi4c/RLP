package com.JayPi.Schule;

public class Bilddaten {

	protected int bild[][] = new int[16][16];

	public static enum choices {
		CHESS, GREYSCALE;
	}

	public Bilddaten(choices c) {
		if (c == choices.CHESS) {
			for (int i = 0; i < bild.length; i++) {
				for (int j = 0; j < bild[i].length; j++) {
					bild[i][j] = (i + j) % 2 * 255;
				}
			}
		} else if (c == choices.GREYSCALE) {
			for (int z = 0; z < 16; z++) {
				for (int s = 0; s < 16; s++) {
					int grauwert = 16 * z + s;
					bild[z][s] = grauwert;
				}
			}
		}
	}

	public void setBild(int bild[][]) {
		this.bild = bild;
	}

	public void setFarbwert(int i, int j, int farbe) {
		bild[i][j] = farbe;
	}

	public int getFarbwert(int i, int j) {
		return bild[i][j];
	}

}
