package com.kermekx.zombiesurvival.hud;

import java.util.ArrayList;
import java.util.List;

import com.kermekx.engine.drawable.Drawable;
import com.kermekx.engine.drawable.Rectangle2D;
import com.kermekx.engine.drawable.list.DisplayList;
import com.kermekx.engine.hud.HUD;
import com.kermekx.engine.mouse.MouseEvent;
import com.kermekx.engine.position.Vector;
import com.kermekx.zombiesurvival.entity.Player;
import com.kermekx.zombiesurvival.item.Item;
import com.kermekx.zombiesurvival.item.SecondaryWeapon;
import com.kermekx.zombiesurvival.item.Weapon;
import com.kermekx.zombiesurvival.texture.HudTextures;

public class CurrentWeapon extends HUD {

	private Player player;

	public CurrentWeapon(Player player) {
		this.player = player;
		checkWeapon();
	}

	private void checkWeapon() {
		if (Item.items[player.getInventory().getSlot(player.getSlotActualWeapon()).getItemId()] instanceof Weapon) {
			addDrawable(new Rectangle2D(70, 1010, 140, 140,
					Item.items[player.getInventory().getSlot(player.getSlotActualWeapon()).getItemId()]
							.getTextureID()));
			addDrawable(new Rectangle2D(75, 1005, 150, 150, HudTextures.WEAPON_WITH_AMMO.getTextureId()));
		} else if (Item.items[player.getInventory().getSlot(player.getSlotActualWeapon())
				.getItemId()] instanceof SecondaryWeapon) {
			addDrawable(new Rectangle2D(70, 1010, 140, 140,
					Item.items[player.getInventory().getSlot(player.getSlotActualWeapon()).getItemId()]
							.getTextureID()));
			addDrawable(new Rectangle2D(75, 1005, 150, 150, HudTextures.WEAPON_WITHOUT_AMMO.getTextureId()));
		}
	}

	@Override
	public void update(int delta) {
		this.checkWeapon();
	}

	@Override
	public void mouseEvent(MouseEvent mouseEvent) {

	}

}
