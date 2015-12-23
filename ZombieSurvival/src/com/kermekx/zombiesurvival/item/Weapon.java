package com.kermekx.zombiesurvival.item;

import com.kermekx.zombiesurvival.entity.Bullet;
import com.kermekx.zombiesurvival.entity.Player;
import com.kermekx.zombiesurvival.inventory.ItemStack;
import com.kermekx.zombiesurvival.scene.GameScene;
import com.kermekx.zombiesurvival.texture.ItemTextures;

public class Weapon extends Item {

	public Weapon(int id, ItemTextures texture) {
		super(id, texture, false);
	}
	
	 @Override
	public void use(Player p) {
		if (p.getInventory().removeItem(new ItemStack(ItemList.AMMO.getItem().getId())) == null) {
			p.fire();
			Bullet b = new Bullet(p.getContext(), p.getPosition(), p.getRotation());
			p.getContext().addDrawable(b.getDrawables());
			((GameScene) p.getContext()).getEntities().add(b);
		}
	}

}
