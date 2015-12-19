package com.kermekx.engine.drawable;

import com.kermekx.engine.position.Vector;

public class Rectangle2D extends Quad2D {

	public Rectangle2D(float x, float y, float width, float height) {
		super(new float[] { x - width / 2, y - height / 2 }, new float[] {
				x + width / 2, y - height / 2 }, new float[] { x - width / 2,
				y + height / 2 }, new float[] { x + width / 2, y + height / 2 });
		setPosition(new Vector(x, y));
	}
	
	public Rectangle2D(float x, float y, float width, float height, float[] color) {
		super(new float[] { x - width / 2, y - height / 2 }, new float[] {
				x + width / 2, y - height / 2 }, new float[] { x - width / 2,
				y + height / 2 }, new float[] { x + width / 2, y + height / 2 }, color);
		setPosition(new Vector(x, y));
	}
	
	public Rectangle2D(float x, float y, float width, float height, int texture) {
		super(new float[] { x - width / 2, y - height / 2 }, new float[] {
				x + width / 2, y - height / 2 }, new float[] { x - width / 2,
				y + height / 2 }, new float[] { x + width / 2, y + height / 2 }, texture);
		setPosition(new Vector(x, y));
	}
}
