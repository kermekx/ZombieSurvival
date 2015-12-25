package com.kermekx.zombiesurvival.ai;

import com.kermekx.engine.position.Vector;
import com.kermekx.zombiesurvival.entity.DeathEntity;
import com.kermekx.zombiesurvival.entity.Entity;
import com.kermekx.zombiesurvival.scene.GameScene;

public class DropOnDeath extends BaseAI {

	private Entity entity;
	private final int texture;

	public DropOnDeath(Entity entity, int texture) {
		super(entity);
		this.texture = texture;
		this.entity = entity;
	}

	@Override
	public void update(int delta) {
		if (!entity.isAlive()) {
			GameScene context = (GameScene) entity.getContext();
			context.addEntity(new DeathEntity(context, entity.getPosition(), new Vector(100, 100), texture));
		}
	}
}
