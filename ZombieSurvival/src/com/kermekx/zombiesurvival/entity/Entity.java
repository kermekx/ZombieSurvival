package com.kermekx.zombiesurvival.entity;

import java.util.ArrayList;
import java.util.List;

import com.kermekx.engine.drawable.Drawable;
import com.kermekx.engine.drawable.EmptyRectangle2D;
import com.kermekx.engine.position.Matrix;
import com.kermekx.engine.position.Vector;
import com.kermekx.engine.scene.Scene;
import com.kermekx.zombiesurvival.hitbox.Hitbox;

public abstract class Entity {

	private final Scene context;
	private Hitbox hitbox;
	private Vector position;
	private float rotation;
	private boolean alive = true;
	private int life = -1;
	private List<Drawable> drawables = new ArrayList<Drawable>();

	public Entity(Scene context, Vector position, Vector size) {
		this.context = context;
		setPosition(position);
		hitbox = new Hitbox(position, size);
		addDrawable(new EmptyRectangle2D(position.getX(), position.getY(), size.getX(), size.getY()));
	}

	public Entity(Scene context, Vector position, Vector size, int life) {
		this.context = context;
		setPosition(position);
		hitbox = new Hitbox(position, size);
		addDrawable(new EmptyRectangle2D(getHitbox().getPosition().getX(), getHitbox().getPosition().getY(),
				getHitbox().getSize().getX(), getHitbox().getSize().getY()));
		this.life = life;
	}

	public Scene getContext() {
		return context;
	}

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
		position = Matrix.translation(position, (float) (tx * Math.cos(radian) - ty * Math.sin(radian)),
				(float) (ty * Math.cos(radian) + tx * Math.sin(radian)));
		hitbox.translate(tx, ty);
		for (Drawable d : getDrawables())
			d.translate(tx, ty);
	}

	public void rotate(float angle) {
		rotation += angle;
		hitbox.rotate(angle);
		for (Drawable d : getDrawables())
			d.setRotation(rotation);
	}

	public float getRotation() {
		return rotation;
	}

	public Hitbox getHitbox() {
		return this.hitbox;
	}

	public void damage(int damage) {
		if (life == -1)
			return;

		if ((life -= damage) <= 0)
			kill();
	}

	public void kill() {
		alive = false;
	}

	public void update(int delta) {
	}

	public boolean isAlive() {
		return alive;
	}

	public boolean contains(Entity entity) {
		return hitbox.contains(entity.hitbox);
	}
}
