package com.JayPi.Schule;

import java.awt.Color;
import java.awt.Graphics;

public class Quadrat {

	protected int xLinks, yOben;

	protected int seite;

	protected Color farbe;

	public Quadrat(int xLinks, int yOben, int seite, Color farbe) {
		this.xLinks = xLinks;
		this.yOben = yOben;
		this.farbe = farbe;
		setSeite(seite);
	}

	public void setLinkeObereEcke(int xLinks, int yOben) {
		this.xLinks = xLinks;
		this.yOben = yOben;
	}

	public void setSeite(int seite) {
		if (seite >= 0)
			this.seite = seite;
	}

	public int getSeite() {
		return seite;
	}

	public void setFarbe(Color farbe) {
		this.farbe = farbe;
	}

	public Color getFarbe() {
		return farbe;
	}

	public void zeichne(Graphics g) {
		if (g != null) {
			g.setColor(farbe);
			g.fillRect(xLinks, yOben, seite, seite);
		}
	}
}
