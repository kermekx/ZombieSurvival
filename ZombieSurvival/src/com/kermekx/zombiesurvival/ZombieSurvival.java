package com.kermekx.zombiesurvival;

import com.kermekx.engine.KermekxEngine;
import com.kermekx.zombiesurvival.scene.GameScene;

public class ZombieSurvival extends KermekxEngine {
	
	public ZombieSurvival() {
		super("Zombie Survival");
		Thread game = new Thread(this);
		game.start();
	}

	@Override
	public void launch() {
		setScene(new GameScene());
	}
	
	public static void main(String[] args) {
		new ZombieSurvival();
	}
}
