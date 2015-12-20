package com.kermekx.engine.drawable;

import java.awt.Rectangle;

import com.kermekx.engine.color.ColorBuilder;
import com.kermekx.engine.position.Matrix;
import com.kermekx.engine.position.Vector;

public abstract class BaseDrawable implements Drawable {

	protected final Vector[] vertex;
	protected final float[] color = new float[3];
	protected int texture = -1;
	protected float angle = 0;
	protected Vector position;

	public BaseDrawable(int nbVertex) {
		vertex = new Vector[nbVertex];
	}

	public Vector[] getVertex() {
		return vertex;
	}

	public void setVertex(int i, float x, float y) {
		vertex[i] = new Vector(x, y);
		setColor(ColorBuilder.WHITE);
	}

	public float[] getColor() {
		return color;
	}

	public void setColor(float[] color) {
		this.color[0] = color[0];
		this.color[1] = color[1];
		this.color[2] = color[2];
	}

	public int getTextureId() {
		return texture;
	}

	public void setTexture(int texture) {
		this.texture = texture;
	}

	public float getRotation() {
		return angle;
	}

	public Vector getPosition() {
		return position;
	}

	public void setPosition(Vector position) {
		this.position = position;
	}

	public void setRotation(float angle) {
		this.angle = angle;
	}

	public boolean shouldRender(Rectangle bounds) {
		for (int i = 0; i < vertex.length; i++) {
			if (texture != -1)
				i++;
			if (bounds.contains(vertex[i].getX(), vertex[i].getY()))
				return true;
		}
		return false;
	}

	public void update(int delta) {
	}

	public void translate(float tx, float ty) {
		double radian = Math.toRadians(angle);
		position = Matrix.translation(position,
				(float) (tx * Math.cos(radian) - ty * Math.sin(radian)),
				(float) (ty * Math.cos(radian) + tx * Math.sin(radian)));
		for (int i = 0; i < vertex.length; i++) {
			if (texture != -1)
				i++;
			vertex[i] = Matrix.translation(vertex[i],
					(float) (tx * Math.cos(radian) - ty * Math.sin(radian)),
					(float) (ty * Math.cos(radian) + tx * Math.sin(radian)));
		}
	}
}
