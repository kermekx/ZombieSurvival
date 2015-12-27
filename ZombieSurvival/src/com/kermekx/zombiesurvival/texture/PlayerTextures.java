package com.kermekx.zombiesurvival.texture;

import java.io.IOException;

import com.kermekx.engine.texture.LoadableTexturePack;
import com.kermekx.engine.texture.TextureManager;

public enum PlayerTextures implements LoadableTexturePack {

	PLAYER_DEATH(60, "dead/player_dead_",1),
	
	PLAYER_FEET_IDLE(1, "feet/idle/survivor-idle_", 1),
	PLAYER_FEET_WALK(2, "feet/walk/survivor-walk_", 20),
	PLAYER_FEET_RUN(3, "feet/run/survivor-run_", 20),
	
	PLAYER_FLASHLIGHT_IDLE(41, "flashlight/idle/survivor-idle_flashlight_", 20),
	PLAYER_FLASHLIGHT_MOVE(42, "flashlight/move/survivor-move_flashlight_", 20),
	PLAYER_FLASHLIGHT_MELEEATTACK(43, "flashlight/meleeattack/survivor-meleeattack_flashlight_", 15),
	
	PLAYER_HANDGUN_IDLE(51, "handgun/idle/survivor-idle_handgun_", 20),
	PLAYER_HANDGUN_MOVE(52, "handgun/move/survivor-move_handgun_", 20),
	PLAYER_HANDGUN_SHOOT(53, "handgun/shoot/survivor-shoot_handgun_", 3),
	PLAYER_HANDGUN_RELOAD(54, "handgun/reload/survivor-reload_handgun_", 15),
	
	PLAYER_KNIFE_IDLE(61, "knife/idle/survivor-idle_knife_", 20),
	PLAYER_KNIFE_MOVE(62, "knife/move/survivor-move_knife_", 20),
	PLAYER_KNIFE_MELEEATTACK(63, "knife/meleeattack/survivor-meleeattack_knife_", 15),
	
	PLAYER_RIFLE_IDLE(71, "rifle/idle/survivor-idle_rifle_", 20),
	PLAYER_RIFLE_MOVE(72, "rifle/move/survivor-move_rifle_", 20),
	PLAYER_RIFLE_SHOOT(73, "rifle/shoot/survivor-shoot_rifle_", 3),
	PLAYER_RIFLE_RELOAD(74, "rifle/reload/survivor-reload_rifle_", 20),
	
	PLAYER_SHOTGUN_IDLE(81, "shotgun/idle/survivor-idle_shotgun_", 20),
	PLAYER_SHOTGUN_MOVE(82, "shotgun/move/survivor-move_shotgun_", 20),
	PLAYER_SHOTGUN_SHOOT(83, "shotgun/shoot/survivor-shoot_shotgun_", 3),
	PLAYER_SHOTGUN_RELOAD(84, "shotgun/reload/survivor-reload_shotgun_", 20)
	
	;

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
