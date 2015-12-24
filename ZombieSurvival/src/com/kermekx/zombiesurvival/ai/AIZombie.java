package com.kermekx.zombiesurvival.ai;

import com.kermekx.engine.position.Vector;
import com.kermekx.zombiesurvival.entity.Entity;
import com.kermekx.zombiesurvival.entity.Player;
import com.kermekx.zombiesurvival.hitbox.Hitbox;
import com.kermekx.zombiesurvival.scene.GameScene;

public class AIZombie extends BaseAI {

	private GameScene context;
	private Hitbox hitbox;
	private RandomMouvements randomMouvements;
	private boolean inHitbox;
	private Follow follow;
	private Vector hitboxSize = new Vector(500, 500);

	public AIZombie(GameScene context, Entity entity) {
		super(entity);
		this.context = context;
		randomMouvements = new RandomMouvements(entity);
		follow = new Follow(entity, null);
	}

	@Override
	public void update(int delta) {
		hitbox = new Hitbox(entity.getPosition(), hitboxSize);
		hitbox.setBounds();
		for (Entity e : context.getEntities()) {
			if (hitbox.contains(e.getHitbox()) && e instanceof Player) {
				inHitbox = true;
				hitboxSize.setX(1500);
				hitboxSize.setY(1500);
				follow.setFollow(e);
			}
		}
		if (inHitbox) {
			follow.update(delta);

		} else {
			hitboxSize.setX(500);
			hitboxSize.setY(500);
			randomMouvements.update(delta);
		}

		inHitbox = false;
	}
}
