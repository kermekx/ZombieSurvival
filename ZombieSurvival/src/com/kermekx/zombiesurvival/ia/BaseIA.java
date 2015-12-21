package com.kermekx.zombiesurvival.ia;

import com.kermekx.zombiesurvival.entity.Entity;

public abstract class BaseIA implements IA {
	
	protected final Entity entity;
	
	public BaseIA(Entity entity) {
		this.entity = entity;
	}

}
