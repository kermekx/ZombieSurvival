package com.kermekx.zombiesurvival.hud;

import java.awt.Rectangle;

import com.kermekx.engine.drawable.Rectangle2D;
import com.kermekx.engine.hud.HUD;
import com.kermekx.engine.mouse.MouseEvent;
import com.kermekx.zombiesurvival.inventory.Inventory;
import com.kermekx.zombiesurvival.texture.HudTextures;

public class OpenInventoryHUD extends HUD {
	
	private boolean isOpen = false;
	private ShortInventory inventory;
	private Rectangle hitbox = new Rectangle(1820, 980, 100, 100);

	public OpenInventoryHUD(Inventory inv) {
		addDrawable(new Rectangle2D(1870, 1030, 100, 100, HudTextures.BAG.getTextureId()));
		inventory = new ShortInventory(inv);
	}
	
	@Override
	public void update(int delta) {
		if (isOpen)
			inventory.update(delta);
	}

	@Override
	public void mouseEvent(MouseEvent mouseEvent) {
		if(mouseEvent.isButtonDown(MouseEvent.LEFT_BUTTON) && hitbox.contains(mouseEvent.getPosition().toPoint())) {
			isOpen = !isOpen;
			if(isOpen) {
				this.addDisplayList(inventory.getDisplayLists());
				this.addDrawable(inventory.getDrawables());
			} else {
				this.getDisplayLists().removeAll(inventory.getDisplayLists());
				this.getDrawables().removeAll(inventory.getDrawables());
			}
		}
	}

}
