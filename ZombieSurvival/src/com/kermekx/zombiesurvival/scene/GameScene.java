package com.kermekx.zombiesurvival.scene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.kermekx.engine.drawable.Drawable;
import com.kermekx.engine.keyboard.Key;
import com.kermekx.engine.scene.Scene;
import com.kermekx.engine.texture.TextureManager;
import com.kermekx.zombiesurvival.entity.Entity;
import com.kermekx.zombiesurvival.entity.Player;
import com.kermekx.zombiesurvival.entity.Zombie;
import com.kermekx.zombiesurvival.entity.loader.DecorationLoader;
import com.kermekx.zombiesurvival.hud.CurrentWeapon;
import com.kermekx.zombiesurvival.hud.Life;
import com.kermekx.zombiesurvival.hud.OpenInventoryHUD;
import com.kermekx.zombiesurvival.terrain.World;

public class GameScene extends Scene {

	private static Scene INSTANCE;

	private Player player;

	private List<Entity> entities = new ArrayList<Entity>();
	private List<Entity> entityTmp = new ArrayList<Entity>();

	public GameScene() {
		super();

		INSTANCE = this;

		World world = new World(0, 0, 3, 3);
		addDisplayList(world.getDisplayList());

		player = new Player(this, 0, 0, "Kermekx");
		entities.add(player);

		Zombie paul = new Zombie(this, 500, 0);
		// Player kevin = new Player(this, 200, 200, "Kevin");
		entities.add(paul);
		// entities.add(kevin);
		addDrawable(paul.getDrawables());
		// addDrawable(kevin.getDrawables());
		// paul.addAI(new AIZombie(this, paul));

		for (Drawable d : player.getDrawables())
			addDrawable(d);

		try {
			TextureManager.getTexture("assets/misc/bullet/bullet.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DecorationLoader.load(this, 0, 0);
		DecorationLoader.load(this, 0, 1);

		addHud(new Life(player));
		addHud(new CurrentWeapon(player));
		addHud(new OpenInventoryHUD(player.getInventory()));

	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void addEntity(Entity entity) {
		entityTmp.add(entity);
		addDrawable(entity.getDrawables());
	}

	@Override
	public synchronized void update(int delta) {
		super.update(delta);

		List<Entity> deadEntities = new ArrayList<Entity>();
		for (Entity entity : entities) {
			if (entity.isAlive())
				entity.update(delta);
			else {
				for (Drawable drawable : entity.getDrawables())
					removeDrawable(drawable);
				entity.updateAI(delta);
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
			if (keyPressed(Key.KEY_Z) || keyPressed(Key.KEY_UP)) {
				if (keyPressed(Key.KEY_LSHIFT))
					player.run(delta);
				else
					player.walk(delta);
			}
			
			if (keyPressed(Key.KEY_S) || keyPressed(Key.KEY_DOWN))
				player.walk(-delta);

			if (keyPressed(Key.KEY_R))
				player.reload();

			if (keyPressed(Key.KEY_0))
				player.changeWeapon(0);
			if (keyPressed(Key.KEY_1))
				player.changeWeapon(1);
			if (keyPressed(Key.KEY_2))
				player.changeWeapon(2);
			if (keyPressed(Key.KEY_3))
				player.changeWeapon(3);
			if (keyPressed(Key.KEY_4))
				player.changeWeapon(4);
			if (keyPressed(Key.KEY_5))
				player.changeWeapon(5);
			if (keyPressed(Key.KEY_6))
				player.changeWeapon(6);
			if (keyPressed(Key.KEY_7))
				player.changeWeapon(7);
			if (keyPressed(Key.KEY_8))
				player.changeWeapon(8);
			if (keyPressed(Key.KEY_9))
				player.changeWeapon(9);

			getCamera().setPosition(player.getPosition());
		}

		entities.addAll(entityTmp);
		entityTmp = new ArrayList<Entity>();
	}

	@Override
	public synchronized void updateAI(int delta) {
		for (Entity entity : entities)
			entity.updateAI(delta);
	}

	public static Scene getContext() {
		return INSTANCE;
	}

}