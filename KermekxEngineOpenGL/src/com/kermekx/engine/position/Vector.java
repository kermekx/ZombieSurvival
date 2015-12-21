package com.kermekx.engine.position;

import org.lwjgl.util.vector.Vector3f;

public class Vector {
	
	Vector3f vector;

	public Vector() {
		vector = new Vector3f();
	}
	
	public Vector(float x, float y) {
		vector = new Vector3f(x, y, 1f);
	}
	
	public float getX() {
		return vector.getX();
	}
	
	public void setX(float x) {
		vector.setX(x);
	}
	
	public float getY() {
		return vector.getY();
	}
	
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
