package com.kermekx.zombiesurvival.hud;

import java.util.ArrayList;
import java.util.List;

import com.kermekx.engine.color.ColorBuilder;
import com.kermekx.engine.drawable.Drawable;
import com.kermekx.engine.drawable.Rectangle2D;
import com.kermekx.engine.drawable.list.DisplayList;
import com.kermekx.engine.hud.HUD;
import com.kermekx.engine.position.Vector;
import com.kermekx.zombiesurvival.inventory.Inventory;
import com.kermekx.zombiesurvival.inventory.ItemStack;
import com.kermekx.zombiesurvival.item.Item;
import com.kermekx.zombiesurvival.item.Item.ItemList;

public class ShortInventory extends HUD {

	private final Inventory inventory;

	public ShortInventory(Inventory inventory) {
		List<Drawable> drawables = new ArrayList<Drawable>();
		drawables.add(new Rectangle2D(960, 975, 960, 10, ColorBuilder.BLACK));
		drawables.add(new Rectangle2D(960, 1065, 960, 10, ColorBuilder.BLACK));

		for (int i = 0; i < 11; i++)
			drawables.add(new Rectangle2D(480 + 96 * i, 1020, 10, 100, ColorBuilder.BLACK));
		addDisplayList(new DisplayList(drawables, new Vector(0, 0), new Vector(1920, 1080)));

		for (int i = 0; i < 10; i++)
			addDrawable(new Rectangle2D(528 + 96 * i, 1020, 86, 80, 0));

		this.inventory = inventory;
	}

	@Override
	public void update(int delta) {
		for (int i = 0; i < 10; i++) {
			ItemStack is = inventory.getSlot(i);
			if (is != null)
				getDrawables().get(i).setTexture(Item.items[is.getItemId()].getTextureID());
			else
				getDrawables().get(i).setTexture(ItemList.VOID.getItem().getTextureID());
		}
	}

}
