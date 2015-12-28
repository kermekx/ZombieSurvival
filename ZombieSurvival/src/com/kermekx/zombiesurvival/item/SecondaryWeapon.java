package com.kermekx.zombiesurvival.item;

import java.util.List;

import com.kermekx.engine.drawable.EmptyRectangle2D;
import com.kermekx.engine.position.Vector;
import com.kermekx.zombiesurvival.ZombieSurvival;
import com.kermekx.zombiesurvival.entity.Entity;
import com.kermekx.zombiesurvival.entity.Player;
import com.kermekx.zombiesurvival.entity.Zombie;
import com.kermekx.zombiesurvival.hitbox.Hitbox;
import com.kermekx.zombiesurvival.scene.GameScene;
import com.kermekx.zombiesurvival.texture.ItemTextures;

public class SecondaryWeapon extends Item {

	private Hitbox hitbox;

	public SecondaryWeapon(int id, ItemTextures texture) {
		super(id, texture, false);
	}

	public void use(Player p) {
		p.battle();
		hitbox = new Hitbox(new Vector(p.getPosition().getX()+60, p.getPosition().getY()+40), new Vector(40, 40));
		
		hitbox.rotate(p.getRotation());
		hitbox.setBounds();

		GameScene context = ((GameScene) p.getContext());
		List<Entity> entities = context.getEntities();

		for (Entity entity : entities) {
			if (hitbox.contains(entity.getHitbox()) && entity instanceof Zombie) {
				entity.damage(50);
				System.out.println(Zombie.class.getSimpleName() + ", vie : " + entity.getLife());
			}
		}
		if (ZombieSurvival.DEBUG)
			p.getContext().addDrawable(
					new EmptyRectangle2D(hitbox.getBounds().getBounds().x, hitbox.getBounds().getBounds().y,
							hitbox.getBounds().getBounds().width, hitbox.getBounds().getBounds().height));

	}

}
