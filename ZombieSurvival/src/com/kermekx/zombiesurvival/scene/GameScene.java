package com.kermekx.zombiesurvival.scene;

import java.util.ArrayList;
import java.util.List;

import com.kermekx.engine.drawable.Drawable;
import com.kermekx.engine.keyboard.Key;
import com.kermekx.engine.scene.Scene;
import com.kermekx.zombiesurvival.entity.Bullet;
import com.kermekx.zombiesurvival.entity.Entity;
import com.kermekx.zombiesurvival.entity.Player;
import com.kermekx.zombiesurvival.terrain.World;
import com.kermekx.zombiesurvival.texture.TerrainTextures;

public class GameScene extends Scene {

	private Player player;
	private int lastFire;

	private List<Entity> entities = new ArrayList<Entity>();

	public GameScene() {
		TerrainTextures.loadTextures();
		World world = new World(0, 0, 3, 3);
		addDrawable(world.getDrawables());

		player = new Player(this, 0, 0);
		entities.add(player);
		Player p = new Player(this, 500, 0);
		entities.add(p);
		addDrawable(p.getDrawables());
		for (Drawable d : player.getDrawables())
			addDrawable(d);
	}
	
	public List<Entity> getEntities() {
		return entities;
	}

	@Override
	public void update(int delta) {
		super.update(delta);

		List<Entity> deadEntities = new ArrayList<Entity>();
		for (Entity entity : entities) {
			if (entity.isAlive())
				entity.update(delta);
			else {
				for (Drawable drawable : entity.getDrawables())
					getDrawables().remove(drawable);
				deadEntities.add(entity);
			}
		}
		entities.removeAll(deadEntities);

		if (lastFire > 0) {
			if (keyPressed(Key.KEY_Q))
				player.rotate(delta * -0.1f);
			if (keyPressed(Key.KEY_D))
				player.rotate(delta * 0.1f);
			lastFire -= delta;
		}

		if (lastFire <= 0 && keyPressed(Key.KEY_ENTER)) {
			player.fire();
			lastFire = 150;

			Bullet b = new Bullet(this, player.getPosition(), player.getRotation());
			getDrawables().addAll(b.getDrawables());
			entities.add(b);
		}

		if (lastFire <= 0) {
			boolean walk = false;
			if (keyPressed(Key.KEY_Z)) {
				player.translate(delta * 0.5f, 0);
				walk = true;
			}
			if (keyPressed(Key.KEY_S)) {
				player.translate(delta * -0.5f, 0f);
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
