package com.kermekx.engine.drawable;

import java.awt.Rectangle;

import com.kermekx.engine.position.Vector;

public interface Drawable {
	
	public Vector[] getVertex();
	
	public float[] getColor();
	
	public int getTextureId();
	public void setTexture(int textureId);
	
	public float getRotation();
	public void setRotation(float angle);
	
	public Vector getPosition();
	
	public boolean shouldRender(Rectangle bounds);
	public void update(int delta);
	
	public void translate(float tx, float ty);

}
