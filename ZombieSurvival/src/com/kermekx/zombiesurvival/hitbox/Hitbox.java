package com.kermekx.zombiesurvival.hitbox;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

import com.kermekx.engine.position.Matrix;
import com.kermekx.engine.position.Vector;

public class Hitbox {
	private Vector position;
	private Vector size;
	private float rotation;
	private Area bounds;

	public Hitbox(Vector position, Vector size) {
		this.position = position;
		this.size = size;
		setBounds();
	}

	/**
	 * verifie si la hitbox touche la hitbox en parametre
	 * 
	 * @param hitbox
	 * @return vrai si ils se touchent
	 */
	public boolean contains(Hitbox hitbox) {
		// verifie si il est tres probable que les hitbox se touchent
		if (bounds.getBounds2D().intersects(hitbox.bounds.getBounds2D())) {
			// verifie si les hitbox se touchent reelement
			Area area = new Area(bounds);
			area.intersect(hitbox.bounds);
			return !area.isEmpty();
		}
		return false;
	}

	public void setHitbox(Vector position, Vector size) {
		this.position = position;
		this.size = size;
	}

	public void setBounds() {
		Area bounds = new Area(new Rectangle((int) (position.getX() - size.getX() / 2),
				(int) (position.getY() - size.getY() / 2), (int) size.getX(), (int) size.getY()));

		AffineTransform af = new AffineTransform();
		double radian = Math.toRadians(rotation);
		af.rotate(radian, position.getX(), position.getY());

		this.bounds = bounds.createTransformedArea(af);
	}

	public void translate(float tx, float ty) {
		double radian = Math.toRadians(rotation);
		position = Matrix.translation(position, (float) (tx * Math.cos(radian) - ty * Math.sin(radian)),
				(float) (ty * Math.cos(radian) + tx * Math.sin(radian)));
		setBounds();
	}

	public void rotate(float angle) {
		rotation += angle;
		setBounds();
	}

	public void setRotation(float angle) {
		if (rotation == angle)
			return;
		rotation = angle;
		setBounds();
	}

	public Vector getPosition() {
		return position;
	}

	public Vector getSize() {
		return size;
	}

	public Area getBounds() {
		return this.bounds;
	}
}
