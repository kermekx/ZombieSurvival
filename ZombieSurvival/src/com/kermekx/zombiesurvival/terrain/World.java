package com.kermekx.zombiesurvival.terrain;

import java.util.ArrayList;
import java.util.List;

import com.kermekx.engine.drawable.list.DisplayList;

public class World {
	
	private final Terrain[][] chunks;
	
	public World(int x, int y, int width, int height) {
		chunks = new Terrain[width][height];
		
		for(int i = 0; i < width; i ++)
			for(int j = 0; j < height; j ++)
				chunks[i][j] = new Chunk(i + x - width / 2, j + y - height / 2);
	}
	
	public List<DisplayList> getDisplayList() {
		List<DisplayList> dl = new ArrayList<DisplayList>();
		
		for (Terrain[] chunksRow : chunks)
			for (Terrain terrain : chunksRow)
				dl.add(new DisplayList(terrain.getDrawables(), terrain.getPosition(), terrain.getSize()));
		
		return dl;
	}

}
