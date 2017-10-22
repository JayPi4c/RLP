package com.JayPi.Schule;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.JayPi.Homework.EvenBetterBilddaten;

public class JDemoBild extends JFrame {

	private static final long serialVersionUID = 1L;
	private JBildPanel AnzeigeFlaeche = new JBildPanel();
	private Bildanzeige einBild;
	private EvenBetterBilddaten dasBild = new EvenBetterBilddaten();

	private JButton starten = new JButton("Starten");
	private JButton invertieren = new JButton("invertieren");
	private JButton horizontal = new JButton("horizontal");
	private JButton vertikal = new JButton("vertikal");

	public JDemoBild(String title) {
		super(title);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(550, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		this.getContentPane().setLayout(null);

		AnzeigeFlaeche.setBounds(10, 10, 512, 512);
		einBild = new Bildanzeige();
		bildInAnzeige(dasBild);
		AnzeigeFlaeche.setBildanzeige(einBild);
		AnzeigeFlaeche.setBorder(new javax.swing.border.LineBorder(Color.ORANGE));
		AnzeigeFlaeche.setBackground(Color.WHITE);
		this.getContentPane().add(AnzeigeFlaeche);

		starten.setBounds(20, 550, 100, 50);
		starten.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bildInAnzeige(dasBild);
				repaint();
			}
		});
		add(starten);

		invertieren.setBounds(130, 550, 100, 50);
		invertieren.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dasBild.invertieren();
				bildInAnzeige(dasBild);
				repaint();
			}
		});
		add(invertieren);

		horizontal.setBounds(240, 550, 100, 50);
		horizontal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dasBild.horizontalSpiegeln();
				bildInAnzeige(dasBild);
				repaint();
			}
		});
		add(horizontal);

		vertikal.setBounds(350, 550, 100, 50);
		vertikal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dasBild.vertikalSpiegeln();
				bildInAnzeige(dasBild);
				repaint();
			}
		});
		add(vertikal);

		setVisible(true);
	}

	public static void main(String[] args) {
		new JDemoBild("JDemoBild");
	}

	private void bildInAnzeige(Bilddaten bd) {
		for (int z = 0; z < 16; z++) {
			for (int s = 0; s < 16; s++) {
				int grauwert = bd.getFarbwert(z, s);
				einBild.setFarbe(z, s, new Color(grauwert, grauwert, grauwert));
			}
		}
	}

}
