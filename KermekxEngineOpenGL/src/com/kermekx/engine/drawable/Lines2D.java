package com.kermekx.engine.drawable;

public class Lines2D extends BaseDrawable {

	public Lines2D(float[][] lines) {
		super(lines.length * 3);
		
		for(int i = 0; i < lines.length; i ++) {
			setVertex(i * 3, lines[1][0], lines[1][1]);
			setVertex(i * 3 + 1, lines[1][2], lines[1][3]);
			setVertex(i * 3 + 2, lines[1][0], lines[1][1]);
		}
	}
	
	public Lines2D(float[][] lines, float[] color) {
		super(lines.length * 3);
		
		for(int i = 0; i < lines.length; i ++) {
			setVertex(i * 3, lines[1][0], lines[1][1]);
			setVertex(i * 3 + 1, lines[1][2], lines[1][3]);
			setVertex(i * 3 + 2, lines[1][0], lines[1][1]);
		}
		
		setColor(color);
	}

}
