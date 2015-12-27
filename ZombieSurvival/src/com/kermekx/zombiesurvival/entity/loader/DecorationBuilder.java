package com.kermekx.zombiesurvival.entity.loader;

import com.kermekx.engine.position.Vector;
import com.kermekx.engine.scene.Scene;
import com.kermekx.zombiesurvival.entity.Decoration;
import com.kermekx.zombiesurvival.entity.Entity;
import com.kermekx.zombiesurvival.scene.GameScene;
import com.kermekx.zombiesurvival.texture.TerrainTextures;

public class DecorationBuilder {
	
	public static Scene context = GameScene.getContext();
	private static DecorationList[] decorations = new DecorationList[100];
	
	public enum DecorationList {
		WALL_STONE_BRICK_WHITE(42, new Vector(64, 64), 50, TerrainTextures.STONE_BRICK_WHITE.getTextureId());
		
		private final int id;
		private final Vector size;
		private final int life;
		private final int textureId;
		
		private DecorationList(int id, Vector size, int life, int textureId) {
			this.id = id;
			this.size = size;
			this.life = life;
			this.textureId = textureId;
			decorations[this.id] = this;
		}
	}
	
	public static void load() {
		DecorationList.values();
	}
	
	public static Entity createDecoration(DecorationList entity, Vector position) {
		return new Decoration(context, position, entity.size, entity.life, entity.textureId);
	}
	
	public static Entity createDecoration(int id, Vector position) {
		return createDecoration(decorations[id], position);
	}

}
