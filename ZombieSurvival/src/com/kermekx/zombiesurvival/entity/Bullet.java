package com.kermekx.zombiesurvival.entity;

import java.io.IOException;

import com.kermekx.engine.drawable.Rectangle2D;
import com.kermekx.engine.position.Vector;
import com.kermekx.engine.texture.TextureManager;

public class Bullet extends Entity {

	private static Vector bulletSize = new Vector(32, 32);
	private static Vector bulletHitboxSize = new Vector(32,8);
	public int lifeTime = 0;

	public Bullet(Vector position, float rotation) throws IOException {
		super(position, bulletHitboxSize);
		addDrawable(new Rectangle2D(position.getX(), position.getY(), bulletSize.getX(), bulletSize.getY(),
				TextureManager.getTexture("assets/misc/bullet/bullet.png")));
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
	}

}
