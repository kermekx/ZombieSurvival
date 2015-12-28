package com.kermekx.zombiesurvival.item;

import com.kermekx.engine.drawable.Rectangle2D;
import com.kermekx.engine.position.Vector;
import com.kermekx.zombiesurvival.ZombieSurvival;
import com.kermekx.zombiesurvival.entity.Player;
import com.kermekx.zombiesurvival.hitbox.Hitbox;
import com.kermekx.zombiesurvival.texture.ItemTextures;

public class SecondaryWeapon extends Item {

	public SecondaryWeapon(int id, ItemTextures texture) {
		super(id, texture, false);
	}

	@Override
	public void use(Player p) {
		p.battle();
		Hitbox hitbox = new Hitbox(new Vector(p.getPosition().getX(), p.getPosition().getY()), new Vector(100, 100));
		if (ZombieSurvival.DEBUG)
			p.getContext().addDrawable(new Rectangle2D(p.getPosition().getX(), p.getPosition().getY(), 100, 100));

	}

}
