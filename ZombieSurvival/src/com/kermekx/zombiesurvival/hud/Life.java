package com.kermekx.zombiesurvival.hud;

import java.util.ArrayList;
import java.util.List;

import com.kermekx.engine.color.ColorBuilder;
import com.kermekx.engine.drawable.Drawable;
import com.kermekx.engine.drawable.Rectangle2D;
import com.kermekx.engine.drawable.list.DisplayList;
import com.kermekx.engine.hud.HUD;
import com.kermekx.engine.position.Vector;
import com.kermekx.zombiesurvival.entity.Entity;
import com.kermekx.zombiesurvival.entity.Player;

public class Life extends HUD {

	private Entity player;
	private int startLife;
	private int life;

	public Life(Player player) {
		List<Drawable> drawables = new ArrayList<Drawable>();
		drawables.add(new Rectangle2D(100, 3, 200, 5, ColorBuilder.BLACK));
		drawables.add(new Rectangle2D(100, 25, 200, 5, ColorBuilder.BLACK));
		drawables.add(new Rectangle2D(2, 15, 5, 20, ColorBuilder.BLACK));
		drawables.add(new Rectangle2D(198, 15, 5, 20, ColorBuilder.BLACK));

		addDisplayList(new DisplayList(drawables, new Vector(0, 0), new Vector(1920, 1080)));
		addDrawable(new Rectangle2D(100, 14, 191, 17, ColorBuilder.RED));

		this.startLife = player.getLife();
		this.life = player.getLife();
		this.player = player;
	}

	@Override
	public void update(int delta) {
		if (player.getLife() != life) {
			int percentLife = 100 * life / startLife;
			life = player.getLife();
			getDrawables().remove(0);
			getDrawables().add(new Rectangle2D(100, 14, 100 - 50 + percentLife/2, 17));
		}
	}
}
