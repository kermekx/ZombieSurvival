package com.kermekx.engine.drawable;


public class AnimatedRectangle2D extends Rectangle2D {
	
	protected int[] textures;
	private final int frameUpdateTime;
	
	protected int frame = 0;
	protected int frameTime = 0;
	
	public AnimatedRectangle2D(float x, float y, float width, float height, int[] textures) {
		super(x, y, width, height, textures[0]);
		this.textures = textures;
		frameUpdateTime = 50;
	}
	
	public AnimatedRectangle2D(float x, float y, float width, float height, int[] textures, int frameUpdateTime) {
		super(x, y, width, height, textures[0]);
		this.textures = textures;
		this.frameUpdateTime = frameUpdateTime;
	}
	
	public AnimatedRectangle2D(float x, float y, float width, float height, float z, int[] textures) {
		super(x, y, width, height, z, textures[0]);
		this.textures = textures;
		frameUpdateTime = 50;
	}

	@Override
	public void update(int delta) {
		frameTime += delta;
		if(frameTime >= frameUpdateTime) {
			frameTime -= frameUpdateTime;
			frame = (frame + 1) % textures.length;
		}
		texture = textures[frame];
	}
}
