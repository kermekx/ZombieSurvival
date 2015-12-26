package com.kermekx.zombiesurvival.entity;

import com.kermekx.engine.drawable.SwitchableAnimatedRectangle2D;
import com.kermekx.engine.position.Vector;
import com.kermekx.engine.scene.Scene;
import com.kermekx.zombiesurvival.ai.DropOnDeath;
import com.kermekx.zombiesurvival.inventory.Inventory;
import com.kermekx.zombiesurvival.inventory.ItemStack;
import com.kermekx.zombiesurvival.item.Item;
import com.kermekx.zombiesurvival.item.Item.ItemList;
import com.kermekx.zombiesurvival.texture.PlayerTextures;

public class Player extends Entity {

	public static int LIFE = 100;
	public static float MOVEMENT_SPEED = 0.5f;
	public static float ROTATION_SPEED = 0.2f;

	private final Inventory inventory = new Inventory(16);
	private int slot = 0;
	private boolean walking = false;
	private int using = 0;
	
	private final SwitchableAnimatedRectangle2D feet;
	private final SwitchableAnimatedRectangle2D body;

	public Player(Scene context, int x, int y, String name) {
		super(context, new Vector(x, y), new Vector(85, 55), LIFE);
		int[][] texturesPlayer = new int[3][20];
		int[][] texturesPlayerFeet = new int[2][20];

		for (int i = 0; i < 20; i++) {
			texturesPlayerFeet[0][i] = PlayerTextures.PLAYER_FEET_IDLE.getTextureIds(0);
			texturesPlayer[0][i] = PlayerTextures.PLAYER_RIFLE_IDLE.getTextureIds(i);
			texturesPlayerFeet[1][i] = PlayerTextures.PLAYER_FEET_RUN.getTextureIds(i);
			texturesPlayer[1][i] = PlayerTextures.PLAYER_RIFLE_MOVE.getTextureIds(i);
			texturesPlayer[2][i] = PlayerTextures.PLAYER_RIFLE_SHOOT.getTextureIds(i % 3);
		}
		
		feet = new SwitchableAnimatedRectangle2D(x, y, 102, 62, -5f, texturesPlayerFeet);
		body = new SwitchableAnimatedRectangle2D(x, y, 126, 107, -6f, texturesPlayer);
				
		addDrawable(feet);
		addDrawable(body);

		inventory.addItem(new ItemStack(ItemList.AK47.getItem().getId()));
		inventory.addItem(new ItemStack(ItemList.AMMO.getItem().getId(), 16));

		this.addAI(new DropOnDeath(this, PlayerTextures.PLAYER_DEATH.getTextureIds(0)));
	}

	public void walk(float delta) {
		if (using > 0)
			return;

		feet.setTextureGroupe(1);
		body.setTextureGroupe(1);
		
		translate(delta * MOVEMENT_SPEED, 0);
		
		walking = true;
	}

	@Override
	public boolean rotate(float delta) {
		return super.rotate(delta * ROTATION_SPEED);
	}

	@Override
	public void update(int delta) {
		super.update(delta);
		if (walking)
			walking = false;
		else if (using >= 0)
			using -= delta;
		else {
			feet.setTextureGroupe(0);
			body.setTextureGroupe(0);
		}
	}

	public void use() {
		if (using > 0)
			return;
		Item.items[inventory.getSlot(slot).getItemId()].use(this);
	}

	public void fire() {
		// .get(0) == hitbox
		feet.setTextureGroupe(0);
		body.setTextureGroupe(2);
		using = 150;
	}

	public Inventory getInventory() {
		return inventory;
	}
}
