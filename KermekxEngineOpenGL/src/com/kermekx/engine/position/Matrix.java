package com.kermekx.engine.position;

import com.kermekx.engine.exceptions.InvalidProductException;
import com.kermekx.engine.exceptions.NoSquareException;

public class Matrix {

	private int nrows;
	private int ncols;
	private float[][] data;

	public Matrix(float[][] data) {
		this.data = data.clone();
		this.nrows = data.length;
		this.ncols = data[0].length;
	}

	public Matrix(int nrow, int ncol) {
		this.nrows = nrow;
		this.ncols = ncol;
		data = new float[nrow][ncol];
	}

	public int getNrows() {
		return nrows;
	}

	public int getNcols() {
		return ncols;
	}

	public float getValueAt(int i, int j) {
		return data[i][j];
	}

	public void setValueAt(int i, int j, float value) {
		data[i][j] = value;
	}

	private boolean isSquare() {
		return ncols == nrows;
	}

	private Matrix multiplyByConstant(float d) {
		Matrix matrix = new Matrix(data);
		for (int i = 0; i < nrows; i++)
			for (int j = 0; j < ncols; j++)
				matrix.setValueAt(i, j, d * data[i][j]);
		return matrix;
	}

	private int size() throws NoSquareException {
		if (isSquare())
			return ncols;
		throw new NoSquareException("matrix need to be square.");
	}

	public static Matrix transpose(Matrix matrix) {
		Matrix transposedMatrix = new Matrix(matrix.getNcols(), matrix.getNrows());
		for (int i = 0; i < matrix.getNrows(); i++) {
			for (int j = 0; j < matrix.getNcols(); j++) {
				transposedMatrix.setValueAt(j, i, matrix.getValueAt(i, j));
			}
		}
		return transposedMatrix;
	}

	public static float determinant(Matrix matrix) throws NoSquareException {
		if (!matrix.isSquare())
			throw new NoSquareException("matrix need to be square.");
		if (matrix.size() == 1) {
			return matrix.getValueAt(0, 0);
		}
		if (matrix.size() == 2) {
			return (matrix.getValueAt(0, 0) * matrix.getValueAt(1, 1))
					- (matrix.getValueAt(0, 1) * matrix.getValueAt(1, 0));
		}
		float sum = 0f;
		for (int i = 0; i < matrix.getNcols(); i++) {
			sum += changeSign(i) * matrix.getValueAt(0, i) * determinant(createSubMatrix(matrix, 0, i));
		}
		return sum;
	}

	private static float changeSign(int i) {
		return (i % 2 == 0) ? 1 : -1;
	}

	public static Matrix createSubMatrix(Matrix matrix, int excluding_row, int excluding_col) {
		Matrix mat = new Matrix(matrix.getNrows() - 1, matrix.getNcols() - 1);
		int r = -1;
		for (int i = 0; i < matrix.getNrows(); i++) {
			if (i == excluding_row)
				continue;
			r++;
			int c = -1;
			for (int j = 0; j < matrix.getNcols(); j++) {
				if (j == excluding_col)
					continue;
				mat.setValueAt(r, ++c, matrix.getValueAt(i, j));
			}
		}
		return mat;
	}

	public static Matrix cofactor(Matrix matrix) throws NoSquareException {
		Matrix mat = new Matrix(matrix.getNrows(), matrix.getNcols());
		for (int i = 0; i < matrix.getNrows(); i++)
			for (int j = 0; j < matrix.getNcols(); j++)
				mat.setValueAt(i, j, changeSign(i) * changeSign(j) * determinant(createSubMatrix(matrix, i, j)));
		return mat;
	}

	public static Matrix inverse(Matrix matrix) throws NoSquareException {
		return (transpose(cofactor(matrix)).multiplyByConstant(1f / determinant(matrix)));
	}

	public static Matrix product(Matrix a, Matrix b) throws InvalidProductException {
		if (a.getNcols() != b.getNrows())
			throw new InvalidProductException("Numbers of rows of A must by the same of the number of cols pf B");
		Matrix r = new Matrix(a.getNrows(), b.getNcols());
		for (int i = 0; i < r.getNrows(); i++)
			for (int j = 0; j < r.getNcols(); j++)
				for (int k = 0; k < a.getNcols(); k++)
					r.setValueAt(i, j, r.getValueAt(i, j) + a.getValueAt(i, k) * b.getValueAt(k, j));
		return r;
	}

	public static Vector homothetie(Vector m, float k) {
		Matrix h = new Matrix(new float[][] { { k, 0f, 0f }, { 0f, k, 0f }, { 0f, 0f, 1f } });
		try {
			Matrix t = product(h, m);
			return new Vector(t.getValueAt(0, 0), t.getValueAt(1, 0));
		} catch (InvalidProductException e) {
			e.printStackTrace();
		}
		return m;
	}

	public static Vector framing(Vector m, float k1, float k2) {
		Matrix h = new Matrix(new float[][] { { k1, 0f, 0f }, { 0f, k2, 0f }, { 0f, 0f, 1f } });
		try {
			Matrix t = product(h, m);
			return new Vector(t.getValueAt(0, 0), t.getValueAt(1, 0));
		} catch (InvalidProductException e) {
			e.printStackTrace();
		}
		return m;
	}

	public static Vector symmetryX(Vector m) {
		Matrix h = new Matrix(new float[][] { { 1, 0f, 0f }, { 0f, -1, 0f }, { 0f, 0f, 1f } });
		try {
			Matrix t = product(h, m);
			return new Vector(t.getValueAt(0, 0), t.getValueAt(1, 0));
		} catch (InvalidProductException e) {
			e.printStackTrace();
		}
		return m;
	}

	public static Vector symmetryY(Vector m) {
		Matrix h = new Matrix(new float[][] { { -1, 0f, 0f }, { 0f, 1, 0f }, { 0f, 0f, 1f } });
		try {
			Matrix t = product(h, m);
			return new Vector(t.getValueAt(0, 0), t.getValueAt(1, 0));
		} catch (InvalidProductException e) {
			e.printStackTrace();
		}
		return m;
	}

	public static Vector symmetryOrigin(Vector m) {
		Matrix h = new Matrix(new float[][] { { -1, 0f, 0f }, { 0f, -1, 0f }, { 0f, 0f, 1f } });
		try {
			Matrix t = product(h, m);
			return new Vector(t.getValueAt(0, 0), t.getValueAt(1, 0));
		} catch (InvalidProductException e) {
			e.printStackTrace();
		}
		return m;
	}

	public static Vector translation(Vector m, float tx, float ty) {
		Matrix h = new Matrix(new float[][] { { 1, 0f, tx }, { 0f, 1, ty }, { 0f, 0f, 1f } });
		try {
			Matrix t = product(h, m);
			return new Vector(t.getValueAt(0, 0), t.getValueAt(1, 0));
		} catch (InvalidProductException e) {
			e.printStackTrace();
		}
		return m;
	}

	public static Vector rotation(Vector m, float angle) {
		Matrix h = new Matrix(new float[][] { { (float) Math.cos(angle), (float) -Math.sin(angle), 0 },
				{ (float) Math.sin(angle), (float) Math.cos(angle), 0 }, { 0f, 0f, 1f } });
		try {
			Matrix t = product(h, m);
			return new Vector(t.getValueAt(0, 0), t.getValueAt(1, 0));
		} catch (InvalidProductException e) {
			e.printStackTrace();
		}
		return m;
	}

}
