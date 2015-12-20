package com.kermekx.engine.drawable;

public class Lines2D extends BaseDrawable {

	public Lines2D(float[][] lines) {
		super(lines.length * 6);
		
		for(int i = 0; i < lines.length; i ++) {
			setVertex(i * 6, lines[i][0], lines[i][1]);
			setVertex(i * 6 + 1, lines[i][2], lines[i][3]);
			setVertex(i * 6 + 2, lines[i][0] + 1, lines[i][1] + 1);
			setVertex(i * 6 + 3, lines[i][0] + 1, lines[i][1] + 1);
			setVertex(i * 6 + 4, lines[i][2], lines[i][3]);
			setVertex(i * 6 + 5, lines[i][2] + 1, lines[i][3] + 1);
		}
	}
	
	public Lines2D(float[][] lines, float[] color) {
		super(lines.length * 6);
		
		for(int i = 0; i < lines.length; i ++) {
			setVertex(i * 6, lines[i][0], lines[i][1]);
			setVertex(i * 6 + 1, lines[i][2], lines[i][3]);
			setVertex(i * 6 + 2, lines[i][0] + 1, lines[i][1] + 1);
			setVertex(i * 6 + 3, lines[i][0] + 1, lines[i][1] + 1);
			setVertex(i * 6 + 4, lines[i][2], lines[i][3]);
			setVertex(i * 6 + 5, lines[i][2] + 1, lines[i][3] + 1);
		}
		
		setColor(color);
	}

}
