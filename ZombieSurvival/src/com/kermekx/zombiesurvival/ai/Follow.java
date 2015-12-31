package com.kermekx.zombiesurvival.ai;

import com.kermekx.zombiesurvival.entity.Entity;
import com.kermekx.zombiesurvival.entity.Zombie;

public class Follow extends BaseAI {

	private Entity follow;
	private LookAt lookAt;
	private final int damage;

	public Follow(Entity entity, Entity follow) {
		super(entity);
		this.follow = follow;
		lookAt = new LookAt(entity, follow);
		damage = 0;
	}

	public Follow(Entity entity, Entity follow, int damage) {
		super(entity);
		this.follow = follow;
		lookAt = new LookAt(entity, follow);
		this.damage = damage;
	}

	@Override
	public void update(int delta) {
		if (follow != null) {
			lookAt.update(delta);

			Entity d;
			if ((d = entity.translate(delta * Zombie.MOVEMENT_SPEED, 0)) != null && damage != 0)
				d.damage(delta * damage * 0.001f);
		}
	}

	public void setFollow(Entity follow) {
		this.follow = follow;
		this.lookAt = new LookAt(entity, follow);
	}
}
