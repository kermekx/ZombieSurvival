package com.kermekx.engine.position;

public class Vector extends Matrix {
	
	@Override
	public String toString() {
		return "Vector [X=" + getX() + ", Y=" + getY() + "]";
	}

	public Vector(float x, float y) {
		super(3, 1);
		setValueAt(0, 0, x);
		setValueAt(1, 0, y);
		setValueAt(2, 0, 1);
	}
	
	public float getX() {
		return getValueAt(0, 0);
	}
	
	public void setX(float x) {
		setValueAt(0, 0, x);
	}
	
	public float getY() {
		return getValueAt(1, 0);
	}
	
	public void setY(float y) {
		setValueAt(1, 0, y);
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Vector))
			return false;
		Vector vector = (Vector) object;
		return (vector.getX() == getX() && vector.getY() == getY());
	}

}
