package com.kermekx.zombiesurvival.texture;

import java.io.IOException;

import com.kermekx.engine.texture.LoadableTexturePack;
import com.kermekx.engine.texture.TextureManager;

public enum ItemTextures implements LoadableTexturePack {

	VOID(0, "void"), AMMO(10, "ammo"), KNIFE(10, "knife"), HANDGUN(11, "handgun"), SAWEDOFF(12, "sawed_off"), AK47(13,
			"ak_47");

	private static final String TEXTURES_PATH = "assets/items/";
	private static final String IMAGE_FORMAT = ".png";
	public static final ItemTextures[] TEXTURES = new ItemTextures[100];
	public static double progress = 0;

	private final int id;
	private final String name;
	private int textureId;

	private ItemTextures(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getTextureId() {
		return textureId;
	}

	@Override
	public double loadTextures() {
		double fail = 0;
		int size = ItemTextures.values().length;
		double progressPerTexture = 1.0 / size;
		progress = 0;
		for (ItemTextures texture : ItemTextures.values())
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

	public static void unloadTextures() {
		// TODO : unload textures
	}

}
