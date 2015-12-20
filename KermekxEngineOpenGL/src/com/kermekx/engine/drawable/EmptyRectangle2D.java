package com.kermekx.engine.drawable;

import com.kermekx.engine.position.Vector;

public class EmptyRectangle2D extends Lines2D {

	public EmptyRectangle2D(float x, float y, float width, float height) {
		super(new float[][] { { x - width / 2, y - height / 2, x + width / 2, y - height / 2 },
				{ x - width / 2, y - height / 2, x - width / 2, y + height / 2 },
				{ x + width / 2, y - height / 2, x + width / 2, y + height / 2 },
				{ x - width / 2, y + height / 2, x + width / 2, y + height / 2 } });
		setPosition(new Vector(x, y));
	}

	public EmptyRectangle2D(float x, float y, float width, float height, float[] color) {
		super(new float[][] { { x - width / 2, y - height / 2, x + width / 2, y - height / 2 },
				{ x - width / 2, y - height / 2, x - width / 2, y + height / 2 },
				{ x + width / 2, y - height / 2, x + width / 2, y + height / 2 },
				{ x - width / 2, y + height / 2, x + width / 2, y + height / 2 } }, color);
		setPosition(new Vector(x, y));
		setColor(color);
	}

}