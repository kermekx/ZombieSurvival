package com.kermekx.zombiesurvival.texture;

import java.io.IOException;

import com.kermekx.engine.texture.LoadableTexturePack;
import com.kermekx.engine.texture.TextureManager;

public enum TerrainTextures implements LoadableTexturePack {

	GRASS(2, "grass"), DIRT(3, "dirt"), PATH(10, "path"),
	STONE_BRICK_WHITE(42, "stone_brick_white");

	private static final String TEXTURES_PATH = "assets/textures/";
	private static final String IMAGE_FORMAT = ".png";
	public static final TerrainTextures[] TEXTURES = new TerrainTextures[100];
	public static double progress = 0;

	private final int id;
	private final String name;
	private int textureId;

	private TerrainTextures(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getTextureId() {
		return textureId;
	}

	@Override
	public double loadTextures() {
		double fail = 0;
		int size = TerrainTextures.values().length;
		double progressPerTexture = 1.0 / size;
		progress = 0;
		for (TerrainTextures texture : TerrainTextures.values()) {
			try {
				texture.textureId = TextureManager.getTexture(TEXTURES_PATH + texture.name + IMAGE_FORMAT);
				TerrainTextures.TEXTURES[texture.id] = texture;
			} catch (IOException e) {
				fail++;
				e.printStackTrace();
			} finally {
				progress += progressPerTexture;
			}
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
