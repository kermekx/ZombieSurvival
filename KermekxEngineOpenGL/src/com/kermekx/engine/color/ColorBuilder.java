package com.kermekx.engine.color;

public class ColorBuilder {

	/**
	 * Couleur noire
	 */
	public static final float[] BLACK = { 0, 0, 0 };
	/**
	 * Couleur rouge
	 */
	public static final float[] RED = { 1, 0, 0 };
	/**
	 * Couleur verte
	 */
	public static final float[] GREEN = { 0, 1, 0 };
	/**
	 * Couleur bleue
	 */
	public static final float[] BLUE = { 0, 0, 1 };
	/**
	 * Couleur blanche
	 */
	public static final float[] WHITE = { 1, 1, 1 };

	/**
	 * Crée un vecteur couleur de 3 flotants
	 * 
	 * @param red
	 *            intensité du rouge de 0 à 1
	 * @param green
	 *            intensité du vert de 0 à 1
	 * @param blue
	 *            intensité du bleu de 0 à 1
	 * @return vecteur de la couleur de 3 flotants
	 */
	public static float[] createColor(float red, float green, float blue) {
		return new float[] { red, green, blue };
	}

}
