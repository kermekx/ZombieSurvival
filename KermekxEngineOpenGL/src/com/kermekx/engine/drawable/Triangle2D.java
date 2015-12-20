package com.kermekx.engine.drawable;

public class Triangle2D extends BaseDrawable {

	public Triangle2D(float[] p1, float[] p2, float[] p3) {
		super(3);
		setVertex(0, p1[0], p1[1]);
		setVertex(1, p2[0], p2[1]);
		setVertex(2, p3[0], p3[1]);
	}
	
	public Triangle2D(float[] p1, float[] p2, float[] p3, float[] color) {
		super(3);
		
		setVertex(0, p1[0], p1[1]);
		setVertex(1, p2[0], p2[1]);
		setVertex(2, p3[0], p3[1]);
		
		setColor(color);
	}

	public Triangle2D(float[] p1, float[] p2, float[] p3, int texture) {
		super(3);
		setVertex(0, p1[0], p1[1]);
		setVertex(1, p2[0], p2[1]);
		setVertex(2, p3[0], p3[1]);
		
		setTexture(texture);
	}
}
