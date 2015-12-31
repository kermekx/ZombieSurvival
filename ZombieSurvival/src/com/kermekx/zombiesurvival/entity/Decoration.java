package com.kermekx.zombiesurvival.entity;

import com.kermekx.engine.drawable.Rectangle2D;
import com.kermekx.engine.position.Vector;
import com.kermekx.engine.scene.Scene;

public class Decoration extends Entity {

	public Decoration(Scene context, Vector position, Vector size, int textureId) {
		super(context, position, size);
		addDrawable(new Rectangle2D(position.getX(), position.getY(), size.getX(), size.getY(), -7, textureId));
	}
	
	public Decoration(Scene context, Vector position, Vector size, Vector hitboxSize, int textureId) {
		super(context, position, hitboxSize);
		addDrawable(new Rectangle2D(position.getX(), position.getY(), size.getX(), size.getY(), -7, textureId));
	}

	public Decoration(Scene context, Vector position, Vector size, int life, int textureId) {
		super(context, position, size, life);
		addDrawable(new Rectangle2D(position.getX(), position.getY(), size.getX(), size.getY(), -7, textureId));
	}
	
	public Decoration(Scene context, Vector position, Vector size, Vector hitboxSize, int life, int textureId) {
		super(context, position, hitboxSize, life);
		addDrawable(new Rectangle2D(position.getX(), position.getY(), size.getX(), size.getY(), -7, textureId));
	}

}
