package com.kermekx.zombiesurvival.ai;

import com.kermekx.engine.drawable.Rectangle2D;
import com.kermekx.zombiesurvival.entity.Entity;
import com.kermekx.zombiesurvival.entity.Player;
import com.kermekx.zombiesurvival.texture.PlayerTextures;

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
		if (!entity.isAlive())
				System.out.println("IA DropOndeath" + texture);
				((GameScene)entity.getContext())
				entity.addDrawable(new Rectangle2D(entity.getPosition().getX(), entity.getPosition().getY(), 80, 0,
						texture));
			
	}
}
