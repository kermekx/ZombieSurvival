package com.kermekx.engine.position;

import org.lwjgl.util.vector.Vector3f;

public class Vector {
	
	/**
	 * Vecteur de 3 flotants
	 */
	Vector3f vector;

	/**
	 * Vecteur vide (0;0;1)
	 */
	public Vector() {
		vector = new Vector3f(0f, 0f, 1f);
	}
	
	/**
	 * vecteur X-Y (x;y;1)
	 * @param x
	 * @param y
	 */
	public Vector(float x, float y) {
		vector = new Vector3f(x, y, 1f);
	}
	
	/**
	 * renvoie la taille x du vecteur
	 * @return taille x
	 */
	public float getX() {
		return vector.getX();
	}
	
	/**
	 * modifie la taille x du vecteur
	 * @param x taille x
	 */
	public void setX(float x) {
		vector.setX(x);
	}
	
	/**
	 * renvoie la taille y du vecteur
	 * @return taille y
	 */
	public float getY() {
		return vector.getY();
	}
	
	/**
	 * modifie la taille y du vecteur
	 * @param y taille y
	 */
	public void setY(float y) {
		vector.setY(y);
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Vector))
			return false;
		Vector vector = (Vector) object;
		return this.vector.equals(vector.vector);
	}

}
