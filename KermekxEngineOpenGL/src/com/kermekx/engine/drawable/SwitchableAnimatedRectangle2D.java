package com.kermekx.engine.drawable;

public class SwitchableAnimatedRectangle2D extends AnimatedRectangle2D {

	private int[][] texturesGroupes;
	private int currentTexture = 0;

	public SwitchableAnimatedRectangle2D(float x, float y, float width,
			float height, int[][] texturesGroupes) {
		super(x, y, width, height, texturesGroupes[0]);
		this.texturesGroupes = texturesGroupes;
	}

	public void setTextureGroupe(int texture) {
		if (currentTexture != texture) {
			currentTexture = texture;
			textures = texturesGroupes[texture];
			frame = 0;
			frameTime = 0;
		}
	}

}
