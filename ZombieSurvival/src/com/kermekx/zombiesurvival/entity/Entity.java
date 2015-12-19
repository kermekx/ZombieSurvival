package com.kermekx.zombiesurvival.entity;

import java.util.ArrayList;
import java.util.List;

import com.kermekx.engine.drawable.Drawable;
import com.kermekx.engine.position.Matrix;
import com.kermekx.engine.position.Vector;

public abstract class Entity {

	private Vector position;
	private float rotation;

	private List<Drawable> drawables = new ArrayList<Drawable>();

	public List<Drawable> getDrawables() {
		return drawables;
	}

	public void addDrawable(Drawable drawable) {
		drawables.add(drawable);
	}

	public Vector getPosition() {
		return position;
	}

	public void setPosition(Vector position) {
		this.position = position;
	}

	public void translate(float tx, float ty) {
		double radian = Math.toRadians(rotation);
		position = Matrix.translation(position,
				(float) (tx * Math.cos(radian) + ty * Math.sin(radian)),
				(float) (ty * Math.cos(radian) + tx * Math.sin(radian)));
		for (Drawable d : getDrawables())
			d.translate(tx, ty);
	}

	public void rotate(float angle) {
		rotation += angle;
		for (Drawable d : getDrawables())
			d.setRotation(rotation);
	}
}
