package com.kermekx.zombiesurvival.terrain;

import java.util.ArrayList;
import java.util.List;

import com.kermekx.engine.drawable.Drawable;
import com.kermekx.engine.position.Vector;
import com.kermekx.zombiesurvival.texture.TerrainTextures;

public class Terrain {

	private final Vector position;
	private final Vector size;
	private final Block[][] blocks;

	public Terrain(int x, int y, int width, int height) {
		position = new Vector(x * Block.size.getX(), y * Block.size.getY());
		size = new Vector(width * Block.size.getX(), height * Block.size.getY());
		blocks = new Block[width][height];
		
		int baseX = x - width / 2;
		int baseY = y - height / 2;
		
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				blocks[i][j] = new Block(i + baseX , j + baseY, TerrainTextures.DIRT);
	}
	
	public Terrain(int x, int y, int width, int height, TerrainTextures[][] datas) {
		position = new Vector(x * Block.size.getX(), y * Block.size.getY());
		size = new Vector((1 + width) * Block.size.getX(), (1 + height) * Block.size.getY());
		blocks = new Block[width][height];

		int baseX = x - width / 2;
		int baseY = y - height / 2;
		
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				blocks[i][j] = new Block(i + baseX , j + baseY, datas[i][j]);
	}

	public Vector getPosition() {
		return position;
	}
	
	public Vector getSize() {
		return size;
	}
	
	public List<Drawable> getDrawables() {
		List<Drawable> drawables = new ArrayList<Drawable>();

		for (Block[] blocksRow : blocks)
			for (Block block : blocksRow)
				drawables.add(block.getDrawable());

		return drawables;
	}

}
