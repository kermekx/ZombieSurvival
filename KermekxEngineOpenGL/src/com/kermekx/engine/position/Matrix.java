package com.kermekx.engine.position;

import org.lwjgl.util.vector.Matrix3f;

public class Matrix {

	Matrix3f matrix;
	
	public Matrix() {
		matrix = new Matrix3f();
	}
	
	public Matrix(float[][] data) {
		matrix = new Matrix3f();
		setData(data);
	}

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

	public Matrix(int nrow, int ncol) {
		matrix = new Matrix3f();
	}

	public static Vector transform(Matrix m, Vector v) {
		Vector r = new Vector();
		org.lwjgl.util.vector.Matrix3f.transform(m.matrix, v.vector, r.vector);
		return r;
	}

	public static Vector homothetie(Vector v, float k) {
		Matrix m = new Matrix();
		Matrix3f m3f = m.matrix;
		m3f.m00 = k;
		m3f.m11 = k;
		m3f.m22 = 1f;
		return transform(m, v);
	}

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

	public static Vector rotation(Vector v, float angle) {
		Matrix m = new Matrix(new float[][] { { (float) Math.cos(angle), (float) -Math.sin(angle), 0 },
				{ (float) Math.sin(angle), (float) Math.cos(angle), 0 }, { 0f, 0f, 1f } });
		return transform(m, v);
	}

}
