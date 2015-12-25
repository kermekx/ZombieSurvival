package com.kermekx.zombiesurvival.scene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.kermekx.engine.drawable.Drawable;
import com.kermekx.engine.keyboard.Key;
import com.kermekx.engine.scene.Scene;
import com.kermekx.engine.texture.TextureManager;
import com.kermekx.zombiesurvival.ai.AIZombie;
import com.kermekx.zombiesurvival.ai.DropOnDeath;
import com.kermekx.zombiesurvival.entity.Entity;
import com.kermekx.zombiesurvival.entity.Player;
import com.kermekx.zombiesurvival.entity.Zombie;
import com.kermekx.zombiesurvival.hud.Life;
import com.kermekx.zombiesurvival.hud.ShortInventory;
import com.kermekx.zombiesurvival.terrain.World;
import com.kermekx.zombiesurvival.texture.PlayerTextures;

public class GameScene extends Scene {

	private Player player;

	private List<Entity> entities = new ArrayList<Entity>();

	public GameScene() {

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
		kevin.addAI(new DropOnDeath(kevin, PlayerTextures.PLAYER_RIFLE_MOVE.getTextureIds(0)));

		for (Drawable d : player.getDrawables())
			addDrawable(d);

		try {
			TextureManager.getTexture("assets/misc/bullet/bullet.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		addHud(new Life(player));
		addHud(new ShortInventory(player.getInventory()));

	}

	public List<Entity> getEntities() {
		return entities;
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
					getDrawables().remove(drawable);
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

	}
}
