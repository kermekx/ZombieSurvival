package com.kermekx.zombiesurvival.item;

import com.kermekx.zombiesurvival.texture.ItemTextures;

public class Item {
	
	public static Item[] items = new Item[100];
	
	public enum ItemList {
		GLOCK(new Weapon(11, ItemTextures.GLOCK)),
		SAWEDOFF(new Weapon(12, ItemTextures.SAWEDOFF)),
		AK47(new Weapon(13, ItemTextures.AK47));
		
		private final Item item;
		
		ItemList(Item item) {
			this.item = item;
		}
		
		public Item getItem() {
			return item;
		}
	}
	
	private final int id;
	private final ItemTextures texture;
	private final boolean stackable;
	
	public Item(int id, ItemTextures texture) {
		this.id = id;
		items[id] = this;
		this.texture = texture;
		stackable = true;
	}
	
	public Item(int id, ItemTextures texture, boolean stackable) {
		this.id = id;
		items[id] = this;
		this.texture = texture;
		this.stackable = stackable;
	}
	
	public int getId() {
		return id;
	}
	
	public ItemTextures getTexture() {
		return texture;
	}
	
	public int getTextureID() {
		return texture.getTextureId();
	}
	
	public boolean isStackable() {
		return stackable;
	}

}
