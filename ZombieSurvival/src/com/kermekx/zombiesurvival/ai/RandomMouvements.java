package com.kermekx.zombiesurvival.ai;

import java.util.Random;

import com.kermekx.engine.position.Vector;
import com.kermekx.zombiesurvival.entity.Entity;
import com.kermekx.zombiesurvival.entity.Zombie;
import com.kermekx.zombiesurvival.hitbox.Hitbox;
import com.kermekx.zombiesurvival.scene.GameScene;

public class RandomMouvements extends BaseAI {
	private GameScene context;
	private float rotation;
	int timeRotate = 2000;
	private int delta;

	public RandomMouvements(GameScene context, Entity entity) {
		super(entity);
		this.context = context;
	}

	@Override
	public void update(int delta) {
		this.delta += delta;
		Random r = new Random();
		this.rotation = 180 - r.nextFloat() * 360;

		if (this.delta > timeRotate) {
			System.out.println(timeRotate);
			if (this.rotation > 0) {
				super.entity.rotate(rotation - delta * Zombie.ROTATION_SPEED);
				this.rotation = this.rotation - (delta * Zombie.ROTATION_SPEED) > 0 ? 0
						: this.rotation - delta * Zombie.ROTATION_SPEED;
			} else if (this.rotation < 0) {
				super.entity.rotate(rotation + delta * Zombie.ROTATION_SPEED);
				this.rotation = this.rotation - (-delta * Zombie.ROTATION_SPEED) < 0 ? 0
						: this.rotation - (-delta * Zombie.ROTATION_SPEED);
			}
			this.delta -= timeRotate;
			timeRotate = (r.nextInt(5) + 2) * 1000;
		}
		super.entity.translate(delta * Zombie.MOVEMENT_WALK, 0);

		for (Entity e : ((GameScene) super.entity.getContext()).getEntities()) {
			if (super.entity.contains(e) && !(e instanceof Zombie)) {
				super.entity.translate(-delta * (Zombie.MOVEMENT_SPEED + 0.005f), 0);
			}
		}
	}
}
