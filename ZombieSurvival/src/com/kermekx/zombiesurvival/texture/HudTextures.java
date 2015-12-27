package com.kermekx.zombiesurvival.texture;

import java.io.IOException;

import com.kermekx.engine.texture.LoadableTexturePack;
import com.kermekx.engine.texture.TextureManager;

public enum HudTextures implements LoadableTexturePack {
	WEAPON_WITHOUT_AMMO(0, "weapon_without_ammo"), WEAPON_WITH_AMMO(1, "weapon_with_ammo"), AMMO(2, "ammo");

	private static final String TEXTURES_PATH = "assets/hud/";
	private static final String IMAGE_FORMAT = ".png";
	private static final HudTextures[] TEXTURES = new HudTextures[100];

	private static double progress;

	private String name;
	private int id;
	private int textureId;

	private HudTextures(int id, String name) {
		this.name = name;
		this.id = id;
	}

	public int getTextureId() {
		return textureId;
	}

	@Override
	public double loadTextures() {
		double fail = 0;
		int size = HudTextures.values().length;
		double progressPerTexture = 1.0 / size;
		progress = 0;
		for (HudTextures texture : HudTextures.values())
			try {
				texture.textureId = TextureManager.getTexture(TEXTURES_PATH + texture.name + IMAGE_FORMAT);
				TEXTURES[texture.id] = texture;
			} catch (IOException e) {
				fail++;
				e.printStackTrace();
			} finally {
				progress += progressPerTexture;
			}
		progress = 1;
		return fail / size;
	}

	@Override
	public double getProgress() {
		return progress;
	}
}
