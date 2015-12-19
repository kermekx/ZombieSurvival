package com.kermekx.zombiesurvival.scene;

import java.io.IOException;
import java.util.Random;

import com.kermekx.engine.drawable.Drawable;
import com.kermekx.engine.drawable.Rectangle2D;
import com.kermekx.engine.keyboard.Key;
import com.kermekx.engine.scene.Scene;
import com.kermekx.engine.texture.TextureManager;
import com.kermekx.zombiesurvival.entity.Player;

public class GameScene extends Scene {

	Player player;
	int lastFire;

	public GameScene() {
		try {
			Random r = new Random();
			int grass = TextureManager.getTexture("assets/textures/grass.png");
			int dirt = TextureManager.getTexture("assets/textures/dirt.png");
			for (int i = 0; i < 128; i++)
				for (int j = 0; j < 128; j++)
					addDrawable(new Rectangle2D(-64 * 128 + 128 * i, -64 * 128
							+ 128 * j, 128, 128, (r.nextBoolean()) ? dirt : grass));
		} catch (IOException e) {
			e.printStackTrace();
		}
		player = new Player();
		for (Drawable d : player.getDrawables())
			addDrawable(d);
	}

	@Override
	public void update(int delta) {
		super.update(delta);

		if (lastFire > 0)
			lastFire -= delta;

		if (lastFire <= 0 && keyPressed(Key.KEY_ENTER)) {
			player.fire();
			lastFire = 150;
		}

		if (lastFire <= 0) {
			boolean walk = false;
			if (keyPressed(Key.KEY_Z)) {
				player.translate(delta * 0.2f, 0);
				walk = true;
			}
			if (keyPressed(Key.KEY_S)) {
				player.translate(delta * -0.2f, 0f);
				walk = true;
			}
			if (keyPressed(Key.KEY_Q))
				player.rotate(delta * -0.1f);
			if (keyPressed(Key.KEY_D))
				player.rotate(delta * 0.1f);
			getCamera().setPosition(player.getPosition());
			player.walk(walk);
		}
	}

}
