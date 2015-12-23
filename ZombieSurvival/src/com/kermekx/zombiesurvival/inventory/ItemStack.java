package com.kermekx.zombiesurvival.inventory;

import com.kermekx.zombiesurvival.item.Item;

public class ItemStack {
	
	private final int itemId;
	private int amount = 1;
	
	public ItemStack(int id) {
		itemId = id;
	}
	
	public ItemStack(int id, int amount) {
		itemId = id;
		this.amount = amount;
	}
	
	public Item getItem() {
		return Item.items[itemId];
	}
	
	

}
