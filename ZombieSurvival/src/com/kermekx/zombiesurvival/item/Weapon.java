package com.kermekx.zombiesurvival.item;


import com.kermekx.engine.position.Vector;
import com.kermekx.zombiesurvival.entity.Bullet;
import com.kermekx.zombiesurvival.entity.Player;
import com.kermekx.zombiesurvival.inventory.ItemStack;
import com.kermekx.zombiesurvival.scene.GameScene;
import com.kermekx.zombiesurvival.sound.Sounds;
import com.kermekx.zombiesurvival.texture.ItemTextures;

public class Weapon extends Item {

	private final int ballNumbers = 4;
	private int countShot = 0;

	public Weapon(int id, ItemTextures texture) {
		super(id, texture, false);
	}

	@Override
	public void use(Player p) {
		if (p.getInventory().getSlot(p.getSlotActualWeapon()) == null)
			return;

		if (countShot < ballNumbers) {
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

	public void setCountShot(int countShot) {
		this.countShot = countShot;
	}

}
