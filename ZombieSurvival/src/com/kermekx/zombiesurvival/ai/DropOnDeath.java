package com.kermekx.zombiesurvival.ai;

import com.kermekx.engine.drawable.EmptyRectangle2D;
import com.kermekx.engine.position.Vector;
import com.kermekx.zombiesurvival.ZombieSurvival;
import com.kermekx.zombiesurvival.entity.DeathEntity;
import com.kermekx.zombiesurvival.entity.Entity;
import com.kermekx.zombiesurvival.hitbox.Hitbox;
import com.kermekx.zombiesurvival.scene.GameScene;

public class DropOnDeath extends BaseAI {

	private Entity entity;
	private Hitbox hitbox;
	private final int texture;

	public DropOnDeath(Entity entity, int texture) {
		super(entity);
		this.texture = texture;
		this.entity = entity;
		this.hitbox = new Hitbox(entity.getPosition(), new Vector(150, 150));
		this.hitbox.setBounds();
	}

	@Override
	public void update(int delta) {
		if (!entity.isAlive()) {
			GameScene context = (GameScene) entity.getContext();
			context.addEntity(new DeathEntity(context, entity.getPosition(), new Vector(125, 125), entity.getRotation(),
					texture));
			if (ZombieSurvival.DEBUG)
				context.addDrawable(new EmptyRectangle2D(entity.getPosition().getX(), entity.getPosition().getY(),
						hitbox.getSize().getX(), hitbox.getSize().getY()));
		}
	}
}
