package com.kermekx.zombiesurvival.ai;

import java.util.Random;

import com.kermekx.engine.position.Vector;
import com.kermekx.zombiesurvival.entity.Entity;
import com.kermekx.zombiesurvival.entity.Zombie;
import com.kermekx.zombiesurvival.hitbox.Hitbox;
import com.kermekx.zombiesurvival.scene.GameScene;

public class RandomMouvements extends BaseAI {
	private GameScene context;
	private int delta;

	public RandomMouvements(GameScene context, Entity entity) {
		super(entity);
		this.context = context;
	}

	@Override
	public void update(int delta) {
		this.delta += delta;
		Random r = new Random();
		entity.translate(delta * Zombie.MOVEMENT_WALK, 0);

		if (this.delta > 1000) {
			int i = 0;
			entity.setRotation(i = (r.nextInt(2) * 90));
			this.delta -= 1000;
		}

		for (Entity e : ((GameScene) entity.getContext()).getEntities()) {
			if (entity.contains(e) && !(e instanceof Zombie)) {
				entity.translate(-delta * (Zombie.MOVEMENT_SPEED + 0.005f), 0);
			}
		}
	}
}
