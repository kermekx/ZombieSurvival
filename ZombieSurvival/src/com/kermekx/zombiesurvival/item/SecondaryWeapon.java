package com.kermekx.zombiesurvival.item;

import java.util.List;

import com.kermekx.engine.drawable.EmptyRectangle2D;
import com.kermekx.engine.position.Vector;
import com.kermekx.zombiesurvival.ZombieSurvival;
import com.kermekx.zombiesurvival.damage.Damage;
import com.kermekx.zombiesurvival.damage.DamageSource;
import com.kermekx.zombiesurvival.entity.Entity;
import com.kermekx.zombiesurvival.entity.Player;
import com.kermekx.zombiesurvival.hitbox.Hitbox;
import com.kermekx.zombiesurvival.scene.GameScene;
import com.kermekx.zombiesurvival.texture.ItemTextures;

public class SecondaryWeapon extends Item {

	private Hitbox hitbox;

	public SecondaryWeapon(int id, ItemTextures texture) {
		super(id, texture, false);
	}

	public void use(Player player) {
		player.meleeAtack();
		hitbox = new Hitbox(new Vector(player.getPosition().getX(), player.getPosition().getY()), new Vector(60, 60));
		
		hitbox.rotate(player.getRotation());
		hitbox.translate(40, 0);
		hitbox.setBounds();

		GameScene context = ((GameScene) player.getContext());
		
		List<Entity> entities = context.getEntities();

		for (Entity entity : entities) {
			if (hitbox.contains(entity.getHitbox()) && entity != player) {
				entity.damage(new Damage(player, DamageSource.CUT, 50));
			}
		}
		
		if (ZombieSurvival.DEBUG)
			context.addDrawable(
					new EmptyRectangle2D(hitbox.getBounds().getBounds().x, hitbox.getBounds().getBounds().y,
							hitbox.getBounds().getBounds().width, hitbox.getBounds().getBounds().height));

	}

}
