package com.JayPi.Homework;

import com.JayPi.Schule.Bilddaten;

public class EvenBetterBilddaten extends Bilddaten {

	public EvenBetterBilddaten() {
		super(Bilddaten.choices.GREYSCALE);
	}

	public void invertieren() {
		for (int i = 0; i < bild.length; i++) {
			for (int j = 0; j < bild[i].length; j++) {
				bild[i][j] = 255 - bild[i][j];
			}
		}
	}

	public void vertikalSpiegeln() {
		for (int i = 0; i < bild.length; i++) {
			for (int j = 0; j < bild[i].length / 2; j++) {
				int pixel = bild[i][j];
				bild[i][j] = bild[i][15 - j];
				bild[i][15 - j] = pixel;
			}
		}
	}

	public void horizontalSpiegeln() {
		for (int j = 0; j < bild.length; j++) {
			for (int i = 0; i < bild.length / 2; i++) {
				int pixel = bild[i][j];
				bild[i][j] = bild[15 - i][j];
				bild[15 - i][j] = pixel;
			}
		}
	}

}
