package com.kermekx.zombiesurvival.ai;

import com.kermekx.zombiesurvival.damage.Damage;
import com.kermekx.zombiesurvival.damage.DamageSource;
import com.kermekx.zombiesurvival.entity.Entity;
import com.kermekx.zombiesurvival.entity.Zombie;

public class Follow extends BaseAI {

	private Entity follow;
	private LookAt lookAt;
	private final int damage;
	private final int damageSpeed;
	private int damageCountDown;

	public Follow(Entity entity, Entity follow) {
		super(entity);
		this.follow = follow;
		lookAt = new LookAt(entity, follow);
		damage = 0;
		damageSpeed = 0;
	}

	public Follow(Entity entity, Entity follow, int damage, int damageSpeed) {
		super(entity);
		this.follow = follow;
		lookAt = new LookAt(entity, follow);
		this.damage = damage;
		this.damageSpeed = damageSpeed;
		damageCountDown = damageSpeed;
	}

	@Override
	public void update(int delta) {
		if (follow != null) {
			lookAt.update(delta);

			Entity d;
			if ((d = entity.translate(delta * Zombie.MOVEMENT_SPEED, 0)) != null && damage != 0) {
				damageCountDown -= delta;
				if (damageCountDown <= 0) {
					d.damage(new Damage(entity, DamageSource.SCRATCH, damage));
					damageCountDown += damageSpeed;
				}
			}
		}
	}

	public void setFollow(Entity follow) {
		this.follow = follow;
		this.lookAt = new LookAt(entity, follow);
	}
}
