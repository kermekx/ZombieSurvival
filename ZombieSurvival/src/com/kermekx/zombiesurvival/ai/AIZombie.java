package com.kermekx.zombiesurvival.ai;

import com.kermekx.engine.position.Vector;
import com.kermekx.zombiesurvival.entity.Bullet;
import com.kermekx.zombiesurvival.entity.Entity;
import com.kermekx.zombiesurvival.entity.Zombie;
import com.kermekx.zombiesurvival.hitbox.Hitbox;
import com.kermekx.zombiesurvival.scene.GameScene;

public class AIZombie extends BaseAI {

	private GameScene context;
	private Hitbox hitbox;
	private RandomMouvements randomMouvements;
	private boolean inHitbox;
	private Follow follow;

	public AIZombie(GameScene context, Entity entity) {
		super(entity);
		this.context = context;
		hitbox = new Hitbox(entity.getPosition(), new Vector(500, 500));
		randomMouvements = new RandomMouvements(context, entity);
		follow = new Follow(context, entity, null);
	}

	@Override
	public void update(int delta) {
		for (Entity e : context.getEntities()) {
			if (hitbox.contains(e.getHitbox()) && (!(e instanceof Zombie)) && !(e instanceof Bullet)) {
				inHitbox = true;
				follow.setFollow(e);
			}
		}
		if (inHitbox) {
			hitbox.setHitbox(entity.getPosition(), new Vector(1500, 1500));
			follow.update(delta);

		} else {
			hitbox.setHitbox(entity.getPosition(), new Vector(500, 500));
			randomMouvements.update(delta);
		}

		inHitbox = false;
	}
}
