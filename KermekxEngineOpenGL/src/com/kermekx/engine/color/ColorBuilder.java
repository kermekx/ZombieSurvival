package com.kermekx.engine.color;

public class ColorBuilder {
	
	public static final float[] BLACK = {0, 0, 0};
	public static final float[] RED = {1, 0, 0};
	public static final float[] GREEN = {0, 1, 0};
	public static final float[] BLUE = {0, 0, 1};
	public static final float[] WHITE = {1, 1, 1};
	
	public static float[] createColor(float red, float green, float blue) {
		return new float[]{red, green, blue};
	}
	
}
