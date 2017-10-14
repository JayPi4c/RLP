package com.JayPi;

import java.awt.Graphics;

import javax.swing.JPanel;

public class FieldShower extends JPanel {

	private static final long serialVersionUID = 5397231417641997603L;

	private Field f;

	public void setField(Field f) {
		this.f = f;
	}

	@Override
	public void paintComponent(Graphics g) {
		if (f != null)
			f.show(g);
		else
			g.drawString("No Information", 50, 50);
	}

}
