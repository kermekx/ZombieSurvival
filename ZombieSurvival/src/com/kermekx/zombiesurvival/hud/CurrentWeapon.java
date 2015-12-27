package com.kermekx.zombiesurvival.hud;

import com.kermekx.engine.drawable.Rectangle2D;
import com.kermekx.engine.hud.HUD;
import com.kermekx.engine.mouse.MouseEvent;
import com.kermekx.zombiesurvival.entity.Player;
import com.kermekx.zombiesurvival.item.Item;
import com.kermekx.zombiesurvival.item.Weapon;
import com.kermekx.zombiesurvival.texture.HudTextures;
import com.kermekx.zombiesurvival.texture.ItemTextures;

public class CurrentWeapon extends HUD {

	private Player player;

	public CurrentWeapon(Player player) {
		this.player = player;
		addDrawable(new Rectangle2D(70, 1010, 140, 140, ItemTextures.AK47.getTextureId()));
		addDrawable(new Rectangle2D(75, 1005, 150, 150, HudTextures.WEAPON_WITHOUT_AMMO.getTextureId()));
	}

	@Override
	public void update(int delta) {
		if (Item.items[player.getInventory().getSlot(player.getSlotActualWeapon()).getItemId()] instanceof Weapon) {
			
		}

	}

	@Override
	public void mouseEvent(MouseEvent mouseEvent) {

	}

}
