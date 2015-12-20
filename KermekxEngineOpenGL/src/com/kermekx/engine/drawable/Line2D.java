package com.kermekx.engine.drawable;

public class Line2D extends BaseDrawable {

	public Line2D(float[] p1, float[] p2) {
		super(3);
		
		setVertex(0, p1[0], p1[1]);
		setVertex(1, p2[0], p2[1]);
		setVertex(2, p1[0], p1[1]);
	}
	
	public Line2D(float[] p1, float[] p2, float[] color) {
		super(3);
		
		setVertex(0, p1[0], p1[1]);
		setVertex(1, p2[0], p2[1]);
		setVertex(2, p1[0], p1[1]);
		
		setColor(color);
	}

}
