package com.kermekx.zombiesurvival.ai;

import java.util.Random;

import com.kermekx.zombiesurvival.entity.Entity;
import com.kermekx.zombiesurvival.entity.Zombie;
import com.kermekx.zombiesurvival.scene.GameScene;

public class RandomMouvements extends BaseAI {
	private float rotation;
	int timeRotate = 2000;
	private int delta;

	public RandomMouvements(Entity entity) {
		super(entity);
	}

	@Override
	public void update(int delta) {
		this.delta += delta;
		Random r = new Random();

		if (this.delta > timeRotate) {
			this.rotation = 180 - r.nextFloat() * 360;
			this.delta -= timeRotate;
			timeRotate = (r.nextInt(5) + 2) * 1000;
		}

		if (this.rotation > 0) {
			super.entity.rotate(-delta * Zombie.ROTATION_SPEED);
			this.rotation = (this.rotation - delta * Zombie.ROTATION_SPEED) < 0 ? 0
					: this.rotation - delta * Zombie.ROTATION_SPEED;
		} else if (this.rotation < 0) {
			super.entity.rotate(delta * Zombie.ROTATION_SPEED);
			this.rotation = this.rotation + delta * Zombie.ROTATION_SPEED > 0 ? 0
					: this.rotation - (-delta * Zombie.ROTATION_SPEED);
		}
		super.entity.translate(delta * Zombie.MOVEMENT_WALK, 0);
	}
}
