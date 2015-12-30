package com.kermekx.zombiesurvival.item;

import com.kermekx.engine.position.Vector;
import com.kermekx.zombiesurvival.entity.Bullet;
import com.kermekx.zombiesurvival.entity.Player;
import com.kermekx.zombiesurvival.inventory.ItemStack;
import com.kermekx.zombiesurvival.scene.GameScene;
import com.kermekx.zombiesurvival.sound.Sounds;
import com.kermekx.zombiesurvival.texture.ItemTextures;

public class Weapon extends Item {

	private final int charger;
	private int countShot = 0;

	public Weapon(int id, ItemTextures texture, int charger) {
		super(id, texture, false);
		this.charger = charger;
	}

	@Override
	public void use(Player p) {
		if (p.getInventory().getSlot(p.getSlotActualWeapon()) == null)
			return;

		if (countShot < charger) {
			if (p.getInventory().removeItem(new ItemStack(ItemList.AMMO.getItem().getId())) == null) {
				p.fire();
				Bullet b = new Bullet(p.getContext(), p.getPosition(), p.getRotation());

				((GameScene) p.getContext()).addEntity(b);
				Sounds.GUNSHOT.play(new Vector(0, 0));
				countShot++;
			} else {
				Sounds.TRIGGER.play();
			}

		} else {
			p.reload();
			countShot = 0;
			Sounds.TRIGGER.play();
		}
	}

	public int getCharger() {
		return charger;
	}

	public void setCountShot(int countShot) {
		this.countShot = countShot;
	}

}
