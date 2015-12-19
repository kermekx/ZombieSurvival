package com.kermekx.zombiesurvival.entity;

import java.io.IOException;

import com.kermekx.engine.drawable.Drawable;
import com.kermekx.engine.drawable.SwitchableAnimatedRectangle2D;
import com.kermekx.engine.position.Vector;
import com.kermekx.engine.texture.TextureManager;

public class Player extends Entity {

	public Player() {
		setPosition(new Vector(0, 0));
		
		int[][] texturesPlayer = new int[3][20];
		int[][] texturesPlayerFeet = new int[2][20];
		for (int i = 0; i < 20; i++)
			try {
				texturesPlayerFeet[0][i] = TextureManager.getTexture("assets/person/player/feet/idle/survivor-idle_0.png");
				texturesPlayer[0][i] = TextureManager.getTexture("assets/person/player/rifle/idle/survivor-idle_rifle_" + i + ".png");
				texturesPlayerFeet[1][i] = TextureManager.getTexture("assets/person/player/feet/run/survivor-run_" + i + ".png");
				texturesPlayer[1][i] = TextureManager.getTexture("assets/person/player/rifle/move/survivor-move_rifle_" + i + ".png");
				texturesPlayer[2][i] = TextureManager.getTexture("assets/person/player/rifle/shoot/survivor-shoot_rifle_" + i%3 + ".png");
			} catch (IOException e) {
				e.printStackTrace();
			}
		addDrawable(new SwitchableAnimatedRectangle2D(0, 0, 102, 62, texturesPlayerFeet));
		addDrawable(new SwitchableAnimatedRectangle2D(0, 0, 253 / 2, 108, texturesPlayer));
	}
	
	

	public void walk(boolean walk) {
		if(walk)
			for(Drawable d : getDrawables())
				((SwitchableAnimatedRectangle2D)d).setTextureGroupe(1);
		else
			for(Drawable d : getDrawables())
				((SwitchableAnimatedRectangle2D)d).setTextureGroupe(0);
	}
	
	public void fire() {
		((SwitchableAnimatedRectangle2D)getDrawables().get(0)).setTextureGroupe(0);
		((SwitchableAnimatedRectangle2D)getDrawables().get(1)).setTextureGroupe(2);
	}
}
