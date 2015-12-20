package com.kermekx.zombiesurvival.terrain;

import java.util.ArrayList;
import java.util.List;

import com.kermekx.engine.drawable.Drawable;

public class World {
	
	private final Terrain[][] chunks;
	
	public World(int x, int y, int width, int height) {
		chunks = new Terrain[width][height];
		
		for(int i = 0; i < width; i ++)
			for(int j = 0; j < height; j ++)
				chunks[i][j] = new Chunk(i + x - width / 2, j + y - height / 2);
	}
	
	public List<Drawable> getDrawables() {
		List<Drawable> drawables = new ArrayList<Drawable>();
		
		for (Terrain[] chunksRow : chunks)
			for (Terrain terrain : chunksRow)
				drawables.addAll(terrain.getDrawables());
		
		return drawables;
	}

}
