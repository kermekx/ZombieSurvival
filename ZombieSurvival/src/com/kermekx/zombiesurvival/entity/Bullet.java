package com.kermekx.zombiesurvival.entity;

import java.io.IOException;

import com.kermekx.engine.drawable.Rectangle2D;
import com.kermekx.engine.position.Vector;
import com.kermekx.engine.texture.TextureManager;

public class Bullet extends Entity {

	public int lifeTime = 0;

	public Bullet(Vector position, float rotation) throws IOException {
		addDrawable(new Rectangle2D(position.getX(), position.getY(), 32, 32,
				TextureManager.getTexture("assets/misc/bullet/bullet.png")));
		setPosition(position);
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
