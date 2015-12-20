package com.kermekx.zombiesurvival.entity;

import java.io.IOException;

import com.kermekx.engine.drawable.Rectangle2D;
import com.kermekx.engine.position.Vector;
import com.kermekx.engine.scene.Scene;
import com.kermekx.engine.texture.TextureManager;
import com.kermekx.zombiesurvival.scene.GameScene;

public class Bullet extends Entity {

	private static final Vector bulletSize = new Vector(32, 32);
	private static final Vector bulletHitboxSize = new Vector(32,8);
	private static final int DAMAGE = 20;
	public int lifeTime = 0;

	public Bullet(Scene context, Vector position, float rotation) {
		super(context, position, bulletHitboxSize);
		try {
			addDrawable(new Rectangle2D(position.getX(), position.getY(), bulletSize.getX(), bulletSize.getY(),
					TextureManager.getTexture("assets/misc/bullet/bullet.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		rotate(rotation);
		translate(65, 27);
	}

	@Override
	public void update(int delta) {
		super.update(delta);

		lifeTime += delta;
		if (lifeTime > 5000)
			kill();

		translate(delta, 0f);
		
		for(Entity entity : ((GameScene)getContext()).getEntities())
			if(contains(entity) && entity != this) {
				entity.damage(DAMAGE);
				this.kill();
			}
				
	}

}
