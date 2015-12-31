package com.kermekx.zombiesurvival.entity.loader;

import com.kermekx.engine.position.Vector;
import com.kermekx.engine.scene.Scene;
import com.kermekx.zombiesurvival.ai.DropOnDeath;
import com.kermekx.zombiesurvival.entity.Decoration;
import com.kermekx.zombiesurvival.entity.Entity;
import com.kermekx.zombiesurvival.scene.GameScene;
import com.kermekx.zombiesurvival.texture.TerrainTextures;

public class DecorationBuilder {
	
	public static Scene context = GameScene.getContext();
	private static DecorationList[] decorations = new DecorationList[100];
	
	public enum DecorationList {
		TREE(11, new Vector(128, 128), new Vector(64, 64), 25, TerrainTextures.TREE.getTextureId(), TerrainTextures.TRUNK.getTextureId()),
		WALL_STONE_BRICK_WHITE(42, new Vector(64, 64), 50, TerrainTextures.STONE_BRICK_WHITE.getTextureId(), 0);
		
		private final int id;
		private final Vector size;
		private final Vector hitboxSize;
		private final int life;
		private final int textureId;
		private final int deathTextureId;
		
		private DecorationList(int id, Vector size, int life, int textureId, int deathTextureId) {
			this.id = id;
			this.size = size;
			this.hitboxSize = size;
			this.life = life;
			this.textureId = textureId;
			this.deathTextureId = deathTextureId;
			decorations[this.id] = this;
		}
		
		private DecorationList(int id, Vector size, Vector hitboxSize, int life, int textureId, int deathTextureId) {
			this.id = id;
			this.size = size;
			this.hitboxSize = hitboxSize;
			this.life = life;
			this.textureId = textureId;
			this.deathTextureId = deathTextureId;
			decorations[this.id] = this;
		}
	}
	
	public static void load() {
		DecorationList.values();
	}
	
	public static Entity createDecoration(DecorationList entity, Vector position) {
		Decoration decoration = new Decoration(context, position, entity.size, entity.hitboxSize, entity.life, entity.textureId);
		if (entity.deathTextureId != 0)
			decoration.addAI(new DropOnDeath(decoration, entity.deathTextureId, entity.hitboxSize));
		return decoration;
	}
	
	public static Entity createDecoration(int id, Vector position) {
		return createDecoration(decorations[id], position);
	}

}
