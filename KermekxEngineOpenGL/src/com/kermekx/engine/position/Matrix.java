package com.kermekx.engine.position;

import org.lwjgl.util.vector.Matrix3f;

public class Matrix {

	/**
	 * Matrice 3x3 flotants
	 */
	Matrix3f matrix;
	
	/**
	 * Créé une matrice vide
	 */
	public Matrix() {
		matrix = new Matrix3f();
	}
	
	/**
	 * Créé une matrice contenant les donnée
	 * @param data tableau de flotant 3x3
	 */
	public Matrix(float[][] data) {
		matrix = new Matrix3f();
		setData(data);
	}

	/**
	 * enregistre les donnée de la matrice
	 * @param data donnée a enregistrer
	 */
	private void setData(float[][] data) {
		matrix.m00 = data[0][0];
		matrix.m01 = data[1][0];
		matrix.m02 = data[2][0];
		matrix.m10 = data[0][1];
		matrix.m11 = data[1][1];
		matrix.m12 = data[2][1];
		matrix.m20 = data[0][2];
		matrix.m21 = data[1][2];
		matrix.m22 = data[2][2];
	}

	/**
	 * Produit matricielle entre un vecteur position et une matrice de trandformation
	 * @param m matrice de transformation
	 * @param v vecteur position
	 * @return nouveau vecteur position
	 */
	public static Vector transform(Matrix m, Vector v) {
		Vector r = new Vector();
		Matrix3f.transform(m.matrix, v.vector, r.vector);
		return r;
	}

	/**
	 * Effectue une homotetie sur le vecteur de facteur k (zoom)
	 * @param v vecteur position
	 * @param k facteur k
	 * @return nouveau vecteur
	 */
	public static Vector homothetie(Vector v, float k) {
		Matrix m = new Matrix();
		Matrix3f m3f = m.matrix;
		m3f.m00 = k;
		m3f.m11 = k;
		m3f.m22 = 1f;
		return transform(m, v);
	}

	/**
	 * Effectue un cadrage sur le vecteur de facteur k1 sur X et k2 sur Y (zoom sur x,y)
	 * @param v vecteur position
	 * @param k1 facteur sur X
	 * @param k2 facteur sur Y
	 * @return nouveau vecteur
	 */
	public static Vector framing(Vector v, float k1, float k2) {
		Matrix m = new Matrix(new float[][] { { k1, 0f, 0f }, { 0f, k2, 0f }, { 0f, 0f, 1f } });
		return transform(m, v);
	}

	public static Vector symmetryX(Vector v) {
		Matrix m = new Matrix(new float[][] { { 1, 0f, 0f }, { 0f, -1, 0f }, { 0f, 0f, 1f } });
		return transform(m, v);
	}

	public static Vector symmetryY(Vector v) {
		Matrix m = new Matrix(new float[][] { { -1, 0f, 0f }, { 0f, 1, 0f }, { 0f, 0f, 1f } });
		return transform(m, v);
	}

	public static Vector symmetryOrigin(Vector v) {
		Matrix m = new Matrix(new float[][] { { -1, 0f, 0f }, { 0f, -1, 0f }, { 0f, 0f, 1f } });
		return transform(m, v);
	}

	/**
	 * Effectue une translation sur le vecteur
	 * @param v vecteur position
	 * @param tx translation sur l'axe X
	 * @param ty translation sur l'ae Y
	 * @return nouveau vecteur
	 */
	public static Vector translation(Vector v, float tx, float ty) {
		Matrix m = new Matrix();
		Matrix3f m3f = m.matrix;
		m3f.m20 = tx;
		m3f.m21 = ty;
		m3f.m00 = 1f;
		m3f.m11 = 1f;
		m3f.m22 = 1f;
		return transform(m, v);
	}


	/**
	 * Effectue une rotation par rapport à l'origine
	 * @param v vecteur position
	 * @param angle angle de rotaition en radian
	 * @return nouveau vecteur
	 */
	public static Vector rotation(Vector v, float angle) {
		Matrix m = new Matrix(new float[][] { { (float) Math.cos(angle), (float) -Math.sin(angle), 0 },
				{ (float) Math.sin(angle), (float) Math.cos(angle), 0 }, { 0f, 0f, 1f } });
		return transform(m, v);
	}

}
