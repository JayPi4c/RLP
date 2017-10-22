package com.JayPi.Schule;

import java.awt.Graphics;

import javax.swing.JPanel;

class JBildPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Bildanzeige einBild;

	public void setBildanzeige(Bildanzeige einBild) {
		this.einBild = einBild;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (einBild != null) {
			einBild.zeigeDich(g);
		} else {
			g.setColor(getBackground());
			g.drawRect(0, 0, getSize().width, getSize().height);
		}
	}
}
