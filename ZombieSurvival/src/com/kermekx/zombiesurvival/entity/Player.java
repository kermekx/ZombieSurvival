package com.kermekx.zombiesurvival.entity;

import com.kermekx.engine.drawable.SwitchableAnimatedRectangle2D;
import com.kermekx.engine.position.Vector;
import com.kermekx.engine.scene.Scene;
import com.kermekx.zombiesurvival.ai.DropOnDeath;
import com.kermekx.zombiesurvival.inventory.Inventory;
import com.kermekx.zombiesurvival.inventory.ItemStack;
import com.kermekx.zombiesurvival.item.Item;
import com.kermekx.zombiesurvival.item.Item.ItemList;
import com.kermekx.zombiesurvival.item.SecondaryWeapon;
import com.kermekx.zombiesurvival.item.Weapon;
import com.kermekx.zombiesurvival.texture.PlayerTextures;

public class Player extends Entity {

	public static int LIFE = 100;
	public static float MOVEMENT_SPEED = 0.5f;
	public static float ROTATION_SPEED = 0.2f;

	private final Inventory inventory = new Inventory(16);
	private int actualWeapon = 0;
	private boolean walking = false;
	private boolean run = false;
	private int using = 0;

	private final SwitchableAnimatedRectangle2D feet;
	private final SwitchableAnimatedRectangle2D body;

	public Player(Scene context, int x, int y, String name) {
		super(context, new Vector(x, y), new Vector(85, 55), LIFE);
		int[][] texturesPlayer = new int[18][20];
		int[][] texturesPlayerFeet = new int[3][20];

		for (int i = 0; i < 20; i++) {
			texturesPlayerFeet[0][i] = PlayerTextures.PLAYER_FEET_IDLE.getTextureIds(0);
			texturesPlayerFeet[1][i] = PlayerTextures.PLAYER_FEET_RUN.getTextureIds(i);
			texturesPlayerFeet[2][i] = PlayerTextures.PLAYER_FEET_WALK.getTextureIds(i);

			texturesPlayer[0][i] = PlayerTextures.PLAYER_FLASHLIGHT_IDLE.getTextureIds(i);
			texturesPlayer[1][i] = PlayerTextures.PLAYER_FLASHLIGHT_MELEEATTACK.getTextureIds(i % 15);
			texturesPlayer[2][i] = PlayerTextures.PLAYER_FLASHLIGHT_MOVE.getTextureIds(i);

			texturesPlayer[3][i] = PlayerTextures.PLAYER_HANDGUN_IDLE.getTextureIds(i);
			texturesPlayer[4][i] = PlayerTextures.PLAYER_HANDGUN_MOVE.getTextureIds(i);
			texturesPlayer[5][i] = PlayerTextures.PLAYER_HANDGUN_RELOAD.getTextureIds(i % 15);
			texturesPlayer[6][i] = PlayerTextures.PLAYER_HANDGUN_SHOOT.getTextureIds(i % 3);

			texturesPlayer[7][i] = PlayerTextures.PLAYER_KNIFE_IDLE.getTextureIds(i);
			texturesPlayer[8][i] = PlayerTextures.PLAYER_KNIFE_MELEEATTACK.getTextureIds(i % 15);
			texturesPlayer[9][i] = PlayerTextures.PLAYER_KNIFE_MOVE.getTextureIds(i);

			texturesPlayer[10][i] = PlayerTextures.PLAYER_RIFLE_IDLE.getTextureIds(i);
			texturesPlayer[11][i] = PlayerTextures.PLAYER_RIFLE_MOVE.getTextureIds(i);
			texturesPlayer[12][i] = PlayerTextures.PLAYER_RIFLE_RELOAD.getTextureIds(i);
			texturesPlayer[13][i] = PlayerTextures.PLAYER_RIFLE_SHOOT.getTextureIds(i % 3);

			texturesPlayer[14][i] = PlayerTextures.PLAYER_SHOTGUN_IDLE.getTextureIds(i);
			texturesPlayer[15][i] = PlayerTextures.PLAYER_SHOTGUN_MOVE.getTextureIds(i);
			texturesPlayer[16][i] = PlayerTextures.PLAYER_SHOTGUN_RELOAD.getTextureIds(i);
			texturesPlayer[17][i] = PlayerTextures.PLAYER_SHOTGUN_SHOOT.getTextureIds(i % 3);
		}

		feet = new SwitchableAnimatedRectangle2D(x, y, 102, 62, -5f, texturesPlayerFeet);
		body = new SwitchableAnimatedRectangle2D(x, y, 126, 107, -6f, texturesPlayer);

		addDrawable(feet);
		addDrawable(body);

		inventory.addItem(new ItemStack(ItemList.AK47.getItem().getId()));
		inventory.addItem(new ItemStack(ItemList.HANDGUN.getItem().getId()));
		inventory.addItem(new ItemStack(ItemList.KNIFE.getItem().getId()));
		inventory.addItem(new ItemStack(ItemList.AMMO.getItem().getId(), 32));

		this.addAI(new DropOnDeath(this, PlayerTextures.PLAYER_DEATH.getTextureIds(0), new Vector(100, 100)));
	}

	public void walk(float delta) {
		if (using > 0)
			return;

		feet.setTextureGroupe(2);
		body.setTextureGroupe(getTextureGroupe(actualWeapon, 2));

		translate(delta * MOVEMENT_SPEED, 0);

		walking = true;
	}

	public void run(float delta) {
		if (using > 0)
			return;

		feet.setTextureGroupe(1);
		body.setTextureGroupe(getTextureGroupe(actualWeapon, 2));

		translate(delta * (MOVEMENT_SPEED + 0.2f), 0);

		run = true;
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
		else if (run)
			run = false;
		else if (using >= 0)
			using -= delta;
		else {
			feet.setTextureGroupe(0);
			body.setTextureGroupe(getTextureGroupe(actualWeapon, 0));
		}
	}

	public void use() {
		if (using > 0)
			return;
		Item.items[inventory.getSlot(actualWeapon).getItemId()].use(this);
	}

	public void changeWeapon(int key) {
		System.out.println(key);
		if (inventory.getSlot(key) != null && (Item.items[inventory.getSlot(key).getItemId()] instanceof Weapon
				|| Item.items[inventory.getSlot(key).getItemId()] instanceof SecondaryWeapon)) {
			actualWeapon = key;
		}
	}

	public int getTextureGroupe(int slot, int action) {
		// knife - 10
		// handgun - 11
		// ak 47 - 13
		Item is = Item.items[inventory.getSlot(slot).getItemId()];
		switch (action) {
		case 0:
			if (is.getId() == 10)
				return 7;
			if (is.getId() == 11)
				return 3;
			if (is.getId() == 13)
				return 10;
			break;
		case 1:
			if (is.getId() == 10)
				return 8;
			break;
		case 2:
			if (is.getId() == 10)
				return 9;
			if (is.getId() == 11)
				return 4;
			if (is.getId() == 13)
				return 11;
			break;
		case 3:
			if (is.getId() == 11)
				return 5;
			if (is.getId() == 13)
				return 12;
			break;
		case 4:
			if (is.getId() == 11)
				return 6;
			if (is.getId() == 13)
				return 13;
			break;
		}
		return -1;
	}

	public int getSlotActualWeapon() {
		return actualWeapon;
	}

	public void meleeAtack() {
		feet.setTextureGroupe(0);
		body.setTextureGroupe(getTextureGroupe(actualWeapon, 1));
		using = 700;
	}

	public void fire() {
		feet.setTextureGroupe(0);
		body.setTextureGroupe(getTextureGroupe(actualWeapon, 4));
		using = 150;
	}

	public void reload() {
		Weapon w = (Weapon) inventory.getSlot(actualWeapon).getItem();
		w.setCountShot(0);
		feet.setTextureGroupe(0);
		body.setTextureGroupe(getTextureGroupe(actualWeapon, 3));
		using = 600;
	}

	public Inventory getInventory() {
		return inventory;
	}
}
