package com.JayPi.Manipulation;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Ein Klassenerweiterung, welche BufferedImages bearbeitet.
 * 
 * @author JayPi4c
 * @version 1.0
 */
public class BufferedImageExtension {

	/**
	 * Bearbeitungskonstanten
	 * 
	 * @author JayPi4c
	 * @since 1.0
	 */
	public static enum Constants {
		vertical, horizontal;
	}

	/**
	 * Anhand eines Bufferedimages und einer Bearbeitungskonstante wird dieses
	 * Bild horizontal oder vertikal gespiegelt
	 * 
	 * @since 1.0
	 * @author JayPi4c
	 * @param img
	 *            das zu bearbeitende Bild
	 * @param c
	 *            die Handlung, die mit dem Bild gemacht werden soll
	 * @return gibt das �berarbeitete Bild wieder zur�ck
	 */
	public static BufferedImage spiegeln(BufferedImage img, Constants c) {
		if (c == Constants.vertical) {
			for (int i = 0; i < img.getHeight(); i++) {
				for (int j = 0; j < img.getWidth() / 2; j++) {
					int temp = img.getRGB(j, i);
					img.setRGB(j, i, img.getRGB(img.getWidth() - 1 - j, i));
					img.setRGB(img.getWidth() - 1 - j, i, temp);
				}
			}
		} else if (c == Constants.horizontal) {
			for (int j = 0; j < img.getWidth(); j++) {
				for (int i = 0; i < img.getHeight() / 2; i++) {
					int temp = img.getRGB(j, i);
					img.setRGB(j, i, img.getRGB(j, img.getHeight() - 1 - i));
					img.setRGB(j, img.getHeight() - 1 - i, temp);
				}
			}
		}
		return img;
	}

	/**
	 * Das �bergebene Bild wir in ein Negativ gewandelt
	 * 
	 * @author JayPi4c
	 * @since 1.0
	 * @param img
	 *            das zu �berarbeitende Bild
	 * @return gibt das �berarbeitete Bild zur�ck
	 */
	public static BufferedImage invertieren(BufferedImage img) {
		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {
				Color c = new Color(img.getRGB(i, j));
				c = new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
				img.setRGB(i, j, c.getRGB());
			}
		}
		return img;
	}

}
