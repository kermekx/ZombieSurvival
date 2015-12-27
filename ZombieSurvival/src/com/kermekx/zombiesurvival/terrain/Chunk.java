package com.kermekx.zombiesurvival.terrain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.kermekx.zombiesurvival.texture.TerrainTextures;

public class Chunk extends Terrain {

	private static final String CHUNK_PATH = "assets/chunk/";
	private static final String CSV_FORMAT = ".csv";
	private static final String XY_SEPARATOR = ",";

	public final static int width = 16;
	public final static int height = 16;

	public Chunk(int x, int y) {
		super(x * width, y * height, width, height, loadChunk(x, y));
	}

	private static TerrainTextures[][] loadChunk(int x, int y) {
		TerrainTextures[][] datas = new TerrainTextures[width][height];
		int k = 0;

		try {
			Scanner scanner = new Scanner(new File(CHUNK_PATH + x + XY_SEPARATOR + y + CSV_FORMAT));

			scanner.useDelimiter(",");

			while (scanner.hasNext()) {
				datas[k / height][k % height] = TerrainTextures.TEXTURES[Integer.parseInt(scanner.next())];
				k ++;
			}

			scanner.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return datas;
	}
}
