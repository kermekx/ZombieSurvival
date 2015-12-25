package com.kermekx.zombiesurvival.terrain;

import com.kermekx.engine.drawable.Drawable;
import com.kermekx.engine.drawable.Rectangle2D;
import com.kermekx.engine.position.Vector;
import com.kermekx.zombiesurvival.texture.TerrainTextures;

public class Block {

	public static final Vector size = new Vector(128, 128);

	private final Vector position;
	private TerrainTextures texture;
	private final Drawable drawable;

	public Block(int x, int y, TerrainTextures texture) {
		this.position = new Vector(x * size.getX(), y * size.getY());
		this.texture = texture;
		
		drawable = new Rectangle2D(position.getX(), position.getY(), size.getX(), size.getY(), 9, texture.getTextureId());
	}
	
	public Vector getPosition() {
		return position;
	}
	
	public TerrainTextures getTexture() {
		return texture;
	}
	
	public void setTexture(TerrainTextures texture) {
		this.texture = texture;
		drawable.setTexture(texture.getTextureId());
	}
	
	public Drawable getDrawable() {
		return drawable;
	}

}
