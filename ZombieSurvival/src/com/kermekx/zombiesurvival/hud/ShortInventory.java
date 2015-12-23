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

public class ShortInventory extends HUD {
	
	public ShortInventory(Inventory inventory) {
		List<Drawable> drawables = new ArrayList<Drawable>();
		drawables.add(new Rectangle2D(960, 975, 960, 10, ColorBuilder.WHITE));
		drawables.add(new Rectangle2D(960, 1065, 960, 10, ColorBuilder.WHITE));
		
		for (int i = 0; i < 11; i ++)
			drawables.add(new Rectangle2D(480 + 96 * i, 1020, 10, 100, ColorBuilder.WHITE));
		addDisplayList(new DisplayList(drawables, new Vector(0, 0), new Vector(1920, 1080)));
		
		for (int i = 0; i < 10; i ++)
			addDrawable(new Rectangle2D(528 + 96 * i, 1020, 86, 80, ColorBuilder.RED));
	}
	
	

}
