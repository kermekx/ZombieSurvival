package com.kermekx.zombiesurvival.entity;

import com.kermekx.engine.drawable.Rectangle2D;
import com.kermekx.engine.position.Vector;
import com.kermekx.engine.scene.Scene;

public class DeathEntity extends Entity{

	public DeathEntity(Scene context, Vector position, Vector size, int texture) {
		super(context, position, new Vector());
		addDrawable(new Rectangle2D(position.getX(), position.getY(), size.getX(), size.getY(), texture));
	}

	
}
