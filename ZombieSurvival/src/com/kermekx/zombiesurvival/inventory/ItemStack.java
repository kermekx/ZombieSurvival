package com.kermekx.zombiesurvival.inventory;

import com.kermekx.zombiesurvival.item.Item;

public class ItemStack {

	public static final int maxStack = 99;

	private final int itemId;
	private int amount = 1;

	public ItemStack(int id) {
		itemId = id;
	}

	public ItemStack(int id, int amount) {
		itemId = id;
		this.amount = (!getItem().isStackable()) ? 1 : (amount > maxStack) ? maxStack : amount;
	}
	
	public int getItemId() {
		return itemId;
	}

	public Item getItem() {
		return Item.items[itemId];
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int add(int amount) {
		if (!getItem().isStackable())
			return amount;
		this.amount = this.amount + amount;
		if(this.amount <= maxStack)
			return 0;
		int left = this.amount - maxStack;
		this.amount = maxStack;
		return left;
	}
	
	public int remove(int amount) {
		if (this.amount > amount) {
			this.amount -= amount;
			return 0;
		} else {
			int left = amount - this.amount;
			this.amount = 0;
			return left;
		}
	}
	
}
