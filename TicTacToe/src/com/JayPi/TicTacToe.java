package com.JayPi;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

public class TicTacToe extends JFrame implements MouseListener, ActionListener {

	private static final long serialVersionUID = -3138303072193109244L;
	private static int frameWidth = 800;
	private static int frameHeight = 620;
	private static int fieldWidth = 600;
	private static int fieldHeight = 600;

	private static Field field;
	private static FieldShower fs = new FieldShower();

	private static JButton restart;

	public static AIPlayerTableLookup AI;
	public static MinMax AIMinMax;

	public TicTacToe(String title) {
		super(title);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(frameWidth, frameHeight);
		setLocationRelativeTo(null);
		setResizable(true);

		restart = new JButton("Restart");
		restart.setBounds(670, 400, 80, 25);
		restart.addActionListener(this);
		add(restart);
		restart.setVisible(true);

		Container cp = getContentPane();
		cp.setLayout(null);

		fs.setBounds(0, 0, fieldWidth, fieldHeight);
		fs.addMouseListener(this);
		fs.setField(field);
		fs.setBorder(new LineBorder(Color.BLACK));
		fs.setBackground(Color.WHITE);
		cp.add(fs);
		setVisible(true);
	}

	public static void main(String args[]) {
		field = new Field(3, fieldWidth / 3);

		AI = new AIPlayerTableLookup(field);
		AIMinMax = new MinMax(field);

		new TicTacToe("TicTacToe");

	}

	public void move(Cell c) {
		Cell.States currentState = field.getCurrentPlayer();
		field = field.updateField(c);
		fs.setField(field);
		fs.repaint();

		if (field.checkWinning(currentState, c)) {
			JOptionPane.showMessageDialog(null,
					"Spieler " + (currentState == Cell.States.PLAYER1 ? "1" : "2") + " hat gewonnen!");
			JOptionPane.showMessageDialog(null, "Bestätige um neuzustarten.");
			createField();
		} else if (field.checkDraw()) {
			JOptionPane.showMessageDialog(null, "Es ist ein Unentschieden!");
			JOptionPane.showMessageDialog(null, "Bestätige um neuzustarten.");
			createField();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Cell c = field.getCell(e.getPoint());
		move(c);

		// AIMinMax.setField(field);
		// int[] AIMove = AIMinMax.move();

		int[] AIMove = AI.move();
		move(field.getCell(AIMove[1], AIMove[0]));
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == restart)
			createField();
	}

	public static void createField() {
		field = new Field(3, fieldWidth / 3);
		fs.setField(field);
		AI = new AIPlayerTableLookup(field);
		fs.repaint();
	}

}
