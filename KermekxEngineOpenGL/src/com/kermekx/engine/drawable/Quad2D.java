package com.kermekx.engine.drawable;

public class Quad2D extends BaseDrawable {
	
	public Quad2D(float p1[], float p2[], float p3[], float p4[]) {
		super(6);
		
		setVertex(0, p1[0], p1[1]);
		setVertex(1, p2[0], p2[1]);
		setVertex(2, p3[0], p3[1]);
		
		setVertex(3, p4[0], p4[1]);
		setVertex(4, p2[0], p2[1]);
		setVertex(5, p3[0], p3[1]);
		
	}
	
	public Quad2D(float p1[], float p2[], float p3[], float p4[], float[] color) {
		super(6);
		
		setVertex(0, p1[0], p1[1]);
		setVertex(1, p2[0], p2[1]);
		setVertex(2, p3[0], p3[1]);
		
		setVertex(3, p4[0], p4[1]);
		setVertex(4, p2[0], p2[1]);
		setVertex(5, p3[0], p3[1]);
		
		setColor(color);
	}
	
	public Quad2D(float p1[], float p2[], float p3[], float p4[], int texture) {
		super(12);
		
		setVertex(0, 0, 0);
		setVertex(1, p1[0], p1[1]);
		setVertex(2, 1, 0);
		setVertex(3, p2[0], p2[1]);
		setVertex(4, 0, 1);
		setVertex(5, p3[0], p3[1]);
		
		setVertex(6, 1, 1);
		setVertex(7, p4[0], p4[1]);
		setVertex(8, 1, 0);
		setVertex(9, p2[0], p2[1]);
		setVertex(10, 0, 1);
		setVertex(11, p3[0], p3[1]);
		
		setTexture(texture);
	}
	
	public Quad2D(float p1[], float p2[], float p3[], float p4[], float z, int texture) {
		super(12);
		
		setVertex(0, 0, 0);
		setVertex(1, p1[0], p1[1], z);
		setVertex(2, 1, 0);
		setVertex(3, p2[0], p2[1], z);
		setVertex(4, 0, 1);
		setVertex(5, p3[0], p3[1], z);
		
		setVertex(6, 1, 1);
		setVertex(7, p4[0], p4[1], z);
		setVertex(8, 1, 0);
		setVertex(9, p2[0], p2[1], z);
		setVertex(10, 0, 1);
		setVertex(11, p3[0], p3[1], z);
		
		setTexture(texture);
	}

}
