package com.kermekx.zombiesurvival.texture;

import java.io.IOException;

import com.kermekx.engine.texture.LoadableTexturePack;
import com.kermekx.engine.texture.TextureManager;

public enum PlayerTextures implements LoadableTexturePack {

	PLAYER_FEET_IDLE(1, "feet/idle/survivor-idle_", 1),
	PLAYER_FEET_RUN(2, "feet/run/survivor-run_", 20),
	PLAYER_RIFLE_IDLE(41, "rifle/idle/survivor-idle_rifle_", 20),
	PLAYER_RIFLE_MOVE(43, "rifle/move/survivor-move_rifle_", 20),
	PLAYER_RIFLE_SHOOT(45, "rifle/shoot/survivor-shoot_rifle_", 3),
	PLAYER_DEATH(60, "dead/player_dead_",1);

	private static final String TEXTURES_PATH = "assets/person/player/";
	private static final String IMAGE_FORMAT = ".png";
	public static final PlayerTextures[] TEXTURES = new PlayerTextures[100];
	public static double progress = 0;

	private final int id;
	private final String name;
	private int textureIds[];

	private PlayerTextures(int id, String name, int size) {
		this.id = id;
		this.name = name;
		textureIds = new int[size];
	}

	@Override
	public double loadTextures() {
		double fail = 0;
		int size = PlayerTextures.values().length;
		double progressPerTexture = 1.0 / size;
		progress = 0;
		for (PlayerTextures texture : PlayerTextures.values()) {
			try {
				double progressPerFrame = progressPerTexture / texture.textureIds.length;
				for (int i = 0; i < texture.textureIds.length; i++) {
					texture.textureIds[i] = TextureManager.getTexture(TEXTURES_PATH + texture.name + i + IMAGE_FORMAT);
					progress += progressPerFrame;
				}
				PlayerTextures.TEXTURES[texture.id] = texture;
			} catch (IOException e) {
				fail++;
				e.printStackTrace();
			}
		}
		progress = 1;
		return fail / size;
	}

	@Override
	public double getProgress() {
		return progress;
	}
	
	public int getId(){
		return this.id;
	}
	
	public int getTextureIds(int id){
		return this.textureIds[id];
	}
}
