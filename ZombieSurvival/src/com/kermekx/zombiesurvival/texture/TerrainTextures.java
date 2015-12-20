package com.kermekx.zombiesurvival.texture;

import java.io.IOException;

import com.kermekx.engine.texture.TextureManager;

public enum TerrainTextures {

	DIRT(3, "dirt"), GRASS(2, "grass");

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

	public static double loadTextures() {
		double fail = 0;
		int size = TerrainTextures.values().length;
		double progressPerTexture = 1.0 / size;
		progress = 0;
		for (TerrainTextures texture : TerrainTextures.values())
			try {
				texture.textureId = TextureManager.getTexture(TEXTURES_PATH + texture.name + IMAGE_FORMAT);
				TerrainTextures.TEXTURES[texture.id] = texture;
				progress += progressPerTexture;
			} catch (IOException e) {
				fail++;
				e.printStackTrace();
			}
		return fail / size;
	}

	public static void unloadTextures() {
		// TODO : unload textures
	}

}
