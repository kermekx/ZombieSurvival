package com.kermekx.zombiesurvival;

import com.kermekx.engine.KermekxEngine;
import com.kermekx.engine.listener.LoadingListener;
import com.kermekx.engine.scene.Scene;
import com.kermekx.engine.texture.LoadableTexturePack;
import com.kermekx.engine.texture.TextureLoader;
import com.kermekx.zombiesurvival.scene.GameScene;
import com.kermekx.zombiesurvival.scene.LoadingScene;
import com.kermekx.zombiesurvival.sound.Sounds;
import com.kermekx.zombiesurvival.texture.HudTextures;
import com.kermekx.zombiesurvival.texture.ItemTextures;
import com.kermekx.zombiesurvival.texture.PlayerTextures;
import com.kermekx.zombiesurvival.texture.TerrainTextures;

public class ZombieSurvival extends KermekxEngine {
	
	public static final boolean DEBUG = false;
	
	public ZombieSurvival() {
		super("Zombie Survival");
		Thread game = new Thread(this);
		game.start();
	}

	@Override
	public void launch() {
		Scene loadingScene = new LoadingScene();
		setScene(loadingScene);
		TextureLoader.loadTexture(new LoadableTexturePack[] { ItemTextures.HANDGUN, TerrainTextures.DIRT,
				PlayerTextures.PLAYER_FEET_IDLE , HudTextures.WEAPON_WITHOUT_AMMO}, (LoadingListener) loadingScene);
		Sounds.loadSounds();
		setScene(new GameScene());
	}
	
	public static void main(String[] args) {
		new ZombieSurvival();
	}
}
