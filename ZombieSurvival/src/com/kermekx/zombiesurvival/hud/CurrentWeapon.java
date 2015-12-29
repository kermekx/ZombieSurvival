package com.kermekx.zombiesurvival.hud;

import com.kermekx.engine.drawable.Rectangle2D;
import com.kermekx.engine.hud.HUD;
import com.kermekx.engine.mouse.MouseEvent;
import com.kermekx.zombiesurvival.entity.Player;
import com.kermekx.zombiesurvival.item.Item;
import com.kermekx.zombiesurvival.item.SecondaryWeapon;
import com.kermekx.zombiesurvival.item.Weapon;
import com.kermekx.zombiesurvival.texture.HudTextures;
import com.kermekx.zombiesurvival.texture.ItemTextures;

public class CurrentWeapon extends HUD {

	private Player player;

	public CurrentWeapon(Player player) {
		this.player = player;

		addDrawable(new Rectangle2D(70, 1010, 140, 140,
				Item.items[player.getInventory().getSlot(player.getSlotActualWeapon()).getItemId()].getTextureID()));

		addDrawable(new Rectangle2D(75, 1005, 150, 150, HudTextures.WEAPON_WITHOUT_AMMO.getTextureId()));
		addDrawable(new Rectangle2D(75, 913, 150, 35, HudTextures.AMMO.getTextureId()));

	}

	@Override
	public void update(int delta) {
		if (Item.items[player.getInventory().getSlot(player.getSlotActualWeapon()).getItemId()] instanceof Weapon) {
			getDrawables().get(0).setTexture(
					Item.items[player.getInventory().getSlot(player.getSlotActualWeapon()).getItemId()].getTextureID());
			getDrawables().get(1).setTexture(HudTextures.WEAPON_WITH_AMMO.getTextureId());
			getDrawables().get(2).setTexture(HudTextures.AMMO.getTextureId());
		} else if (Item.items[player.getInventory().getSlot(player.getSlotActualWeapon())
				.getItemId()] instanceof SecondaryWeapon) {
			getDrawables().get(0).setTexture(
					Item.items[player.getInventory().getSlot(player.getSlotActualWeapon()).getItemId()].getTextureID());
			getDrawables().get(1).setTexture(HudTextures.WEAPON_WITHOUT_AMMO.getTextureId());
			getDrawables().get(2).setTexture(ItemTextures.VOID.getTextureId());
		}
	}

	@Override
	public void mouseEvent(MouseEvent mouseEvent) {

	}

}
