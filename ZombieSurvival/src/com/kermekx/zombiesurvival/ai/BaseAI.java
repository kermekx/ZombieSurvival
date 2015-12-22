package com.kermekx.zombiesurvival.ai;

import com.kermekx.zombiesurvival.entity.Entity;

public abstract class BaseAI implements AI {
	
	protected final Entity entity;
	
	public BaseAI(Entity entity) {
		this.entity = entity;
	}

}
