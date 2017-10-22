package com.JayPi.Schule;

import java.awt.Color;
import java.awt.Graphics;

public class Bildanzeige {

	protected Quadrat[][] graubild = new Quadrat[16][16];

	public Bildanzeige() {
		for (int z = 0; z < 16; z++) {
			for (int s = 0; s < 16; s++) {
				int grauwert = 16 * z + s;
				graubild[z][s] = new Quadrat(s * 32, z * 32, 32, new Color(grauwert, grauwert, grauwert));
			}
		}
	}

	public void setFarbe(int z, int s, Color farbe) {
		graubild[z][s].setFarbe(farbe);
	}

	public void zeigeDich(Graphics g) {
		for (int z = 0; z < 16; z++) {
			for (int s = 0; s < 16; s++) {
				graubild[z][s].zeichne(g);
			}
		}
	}
}
