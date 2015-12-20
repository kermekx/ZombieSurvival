package com.kermekx.engine.drawable;

public class EmptyRectangle2D extends Lines2D {

	public EmptyRectangle2D(float x, float y, float width, float height) {
		super(new float[][] { { x - width / 2, y - height / 2, x + width / 2, y - height / 2 },
				{ x - width / 2, y - height / 2, x - width / 2, y + height / 2 },
				{ x + width / 2, y - height / 2, x + width / 2, y + height / 2 },
				{ x - width / 2, y + height / 2, x + width / 2, y + height / 2 } });
	}

	public EmptyRectangle2D(float x, float y, float width, float height, float[] color) {
		super(new float[][] { { x - width / 2, y - height / 2, x + width / 2, y - height / 2 },
				{ x - width / 2, y - height / 2, x - width / 2, y + height / 2 },
				{ x + width / 2, y - height / 2, x + width / 2, y + height / 2 },
				{ x - width / 2, y + height / 2, x + width / 2, y + height / 2 } }, color);
	}

}