package com.kermekx.zombiesurvival.entity;

import java.io.IOException;

import com.kermekx.engine.drawable.Drawable;
import com.kermekx.engine.drawable.SwitchableAnimatedRectangle2D;
import com.kermekx.engine.position.Vector;
import com.kermekx.engine.scene.Scene;
import com.kermekx.engine.texture.TextureManager;
import com.kermekx.zombiesurvival.inventory.Inventory;
import com.kermekx.zombiesurvival.inventory.ItemStack;
import com.kermekx.zombiesurvival.item.Item;
import com.kermekx.zombiesurvival.item.Item.ItemList;

public class Player extends Entity {

	public static int LIFE = 100;
	public static float MOVEMENT_SPEED = 0.5f;
	public static float ROTATION_SPEED = 0.2f;

	private final Inventory inventory = new Inventory(16);
	private int slot = 0;
	private boolean walking = false;
	private int using = 0;

	public Player(Scene context, int x, int y, String name) {
		super(context, new Vector(x, y), new Vector(85, 55), LIFE);
		int[][] texturesPlayer = new int[3][20];
		int[][] texturesPlayerFeet = new int[2][20];
		for (int i = 0; i < 20; i++)
			try {
				texturesPlayerFeet[0][i] = TextureManager
						.getTexture("assets/person/player/feet/idle/survivor-idle_0.png");
				texturesPlayer[0][i] = TextureManager
						.getTexture("assets/person/player/rifle/idle/survivor-idle_rifle_" + i + ".png");
				texturesPlayerFeet[1][i] = TextureManager
						.getTexture("assets/person/player/feet/run/survivor-run_" + i + ".png");
				texturesPlayer[1][i] = TextureManager
						.getTexture("assets/person/player/rifle/move/survivor-move_rifle_" + i + ".png");
				texturesPlayer[2][i] = TextureManager
						.getTexture("assets/person/player/rifle/shoot/survivor-shoot_rifle_" + i % 3 + ".png");
			} catch (IOException e) {
				e.printStackTrace();
			}

		addDrawable(new SwitchableAnimatedRectangle2D(x, y, 102, 62, texturesPlayerFeet));
		addDrawable(new SwitchableAnimatedRectangle2D(x, y, 126, 107, texturesPlayer));
		
		//addDrawable(new Empty);

		inventory.addItem(new ItemStack(ItemList.AK47.getItem().getId()));
		inventory.addItem(new ItemStack(ItemList.AMMO.getItem().getId(), 16));
	}

	public void walk(float delta) {
		if (using > 0)
			return;

		for (Drawable d : getDrawables())
			if (d instanceof SwitchableAnimatedRectangle2D)
				((SwitchableAnimatedRectangle2D) d).setTextureGroupe(1);
		translate(delta * MOVEMENT_SPEED, 0);
		getContext().getCamera().setPosition(getPosition());
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
		else
			for (Drawable d : getDrawables())
				if (d instanceof SwitchableAnimatedRectangle2D)
					((SwitchableAnimatedRectangle2D) d).setTextureGroupe(0);
	}

	public void use() {
		if (using > 0)
			return;
		Item.items[inventory.getSlot(slot).getItemId()].use(this);
	}

	public void fire() {
		// .get(0) == hitbox
		((SwitchableAnimatedRectangle2D) getDrawables().get(1)).setTextureGroupe(0);
		((SwitchableAnimatedRectangle2D) getDrawables().get(2)).setTextureGroupe(2);
		using = 150;
	}

	public Inventory getInventory() {
		return inventory;
	}
}
