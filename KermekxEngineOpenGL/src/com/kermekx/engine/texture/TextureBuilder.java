package com.kermekx.engine.texture;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import com.kermekx.engine.log.KELogger;

public class TextureBuilder {
	
	/**
	 * Texture de réference pour la taille des textes
	 */
	private static BufferedImage bi = new BufferedImage(100, 20, BufferedImage.TYPE_INT_ARGB);
	
	/**
	 * Créé une texture pour le texte en fonction des parametre
	 * @param text texte à inscrire dans l'image
	 * @param fs style de police du texte
	 * @param size taille du text
	 * @return Identifiant de la texture
	 */
	public static int[] buildText(String text, String fs, int size) {
		KELogger.logInfo("Generating texture for text : " + text);
		Font font = new Font(fs, Font.BOLD, size);
		Point p = getFontSize(font, text);
		BufferedImage bi = new BufferedImage(p.x, p.y * 2, BufferedImage.TYPE_INT_ARGB);
		
		Graphics g = bi.createGraphics();
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString(text, 0, p.y);
		int textureID = TextureManager.getTexture(bi);
		KELogger.logInfo("Text texture generated for " + text + " using ID : " + textureID);
		return new int[]{textureID, p.x, p.y};
	}
	
	/**
	 * Renvoie la taille du texte en fonction des parametre
	 * @param font Police du texte
	 * @param text texte
	 * @return Taille du texte
	 */
	private static Point getFontSize(Font font, String text) {
		Graphics g = bi.createGraphics();
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D b = fm.getStringBounds(text, g);
		return new Point((int) b.getWidth(), (int) b.getHeight());
		
	}
}
