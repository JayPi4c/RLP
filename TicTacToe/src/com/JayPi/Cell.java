package com.JayPi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Cell {

	public enum States {
		PLAYER1, PLAYER2, UNUSED
	}

	private States state;

	private int x, y;
	private int w, h;
	private int i, j;

	public Cell(int i, int j, int r) {
		this.i = i;
		this.j = j;
		this.x = i * r;
		this.y = j * r;
		this.w = r;
		this.h = r;
		this.state = States.UNUSED;
	}

	public int getI() {
		return this.i;
	}

	public int getJ() {
		return this.j;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getWidth() {
		return this.w;
	}

	public int getHeight() {
		return this.h;
	}

	public States getState() {
		return this.state;
	}

	public void setState(States s) {
		this.state = s;
	}

	public void show(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(this.x, this.y, this.w, this.h);
		g.setColor(Color.BLACK);
		g.drawRect(this.x, this.y, this.w, this.h);
		if (this.state == States.PLAYER1) {

			g.drawLine(this.x, this.y, this.x + this.w, this.y + this.h);
			g.drawLine(this.x + this.w, this.y, this.x, this.y + this.h);
		} else if (this.state == States.PLAYER2) {
			g.drawOval(this.x, this.y, this.w, this.h);
		}
	}

	public boolean contains(Point p) {
		if (p.x > this.x && p.x < this.x + this.w) {
			if (p.y > this.y && p.y < this.y + this.h) {
				return true;
			}
		}
		return false;
	}

}
