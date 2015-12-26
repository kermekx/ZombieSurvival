package com.kermekx.zombiesurvival.scene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.kermekx.engine.drawable.Drawable;
import com.kermekx.engine.keyboard.Key;
import com.kermekx.engine.position.Vector;
import com.kermekx.engine.scene.Scene;
import com.kermekx.engine.texture.TextureManager;
import com.kermekx.zombiesurvival.entity.Decoration;
import com.kermekx.zombiesurvival.entity.Entity;
import com.kermekx.zombiesurvival.entity.Player;
import com.kermekx.zombiesurvival.entity.Zombie;
import com.kermekx.zombiesurvival.hud.Life;
import com.kermekx.zombiesurvival.hud.ShortInventory;
import com.kermekx.zombiesurvival.terrain.World;
import com.kermekx.zombiesurvival.texture.TerrainTextures;

public class GameScene extends Scene {

	private Player player;

	private List<Entity> entities = new ArrayList<Entity>();
	private List<Entity> entityTmp = new ArrayList<Entity>();

	public GameScene() {
		super();

		World world = new World(0, 0, 3, 3);
		addDisplayList(world.getDisplayList());

		player = new Player(this, 0, 0, "Kermekx");
		entities.add(player);

		Zombie paul = new Zombie(this, 500, 0);
		Player kevin = new Player(this, 200, 200, "Kevin");
		entities.add(paul);
		entities.add(kevin);
		addDrawable(paul.getDrawables());
		addDrawable(kevin.getDrawables());
		// paul.addAI(new AIZombie(this, paul));

		for (Drawable d : player.getDrawables())
			addDrawable(d);

		try {
			TextureManager.getTexture("assets/misc/bullet/bullet.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = -5; i < 6; i++) {
			addEntity(new Decoration(this, new Vector(64 * i, -512), new Vector(64, 64), 50,
					TerrainTextures.STONE_BRICK_WHITE.getTextureId()));
		}

		addHud(new Life(player));
		addHud(new ShortInventory(player.getInventory()));

	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void addEntity(Entity entity) {
		entityTmp.add(entity);
		addDrawable(entity.getDrawables());
	}

	@Override
	public void update(int delta) {
		super.update(delta);

		getHuds().get(0).update(delta);
		List<Entity> deadEntities = new ArrayList<Entity>();
		for (Entity entity : entities) {
			if (entity.isAlive())
				entity.update(delta);
			else {
				for (Drawable drawable : entity.getDrawables())
					removeDrawable(drawable);
				entity.update(delta);
				deadEntities.add(entity);
			}
		}
		entities.removeAll(deadEntities);

		if (player.isAlive()) {
			if (keyPressed(Key.KEY_ENTER))
				player.use();

			if (keyPressed(Key.KEY_Q) || keyPressed(Key.KEY_LEFT))
				player.rotate(-delta);
			if (keyPressed(Key.KEY_D) || keyPressed(Key.KEY_RIGHT))
				player.rotate(delta);
			if (keyPressed(Key.KEY_Z) || keyPressed(Key.KEY_UP))
				player.walk(delta);
			if (keyPressed(Key.KEY_S) || keyPressed(Key.KEY_DOWN))
				player.walk(-delta);
			if (keyPressed(Key.KEY_UP))
				player.damage(1);
		} else {
			// afficher le monsieur mort
		}

		entities.addAll(entityTmp);
		entityTmp = new ArrayList<Entity>();
	}
}
