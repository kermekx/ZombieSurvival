package com.kermekx.zombiesurvival.entity;

import com.kermekx.engine.position.Vector;

public class Hitbox {
	Vector position;
	Vector size;

	public Hitbox(Vector position, Vector size) {
		this.position = position;
		this.size = size;
	}

	public boolean collisions(Hitbox otherHitbox) {
		//a faire
		return false;
	}

	public Vector getPosition() {
		return position;
	}

	public Vector getSize() {
		return size;
	}
}
