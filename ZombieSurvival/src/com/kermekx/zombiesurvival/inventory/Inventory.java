package com.kermekx.zombiesurvival.inventory;

public class Inventory {
	
	private final ItemStack[] items;
	
	public Inventory(int size) {
		items = new ItemStack[size];
	}
	
	public ItemStack addItem(ItemStack is) {
		int i = 0;
		int amount = is.getAmount();
		while(amount > 0 && i < items.length) {
			if(items[i] == null) {
				items[i] = is;
				return null;
			} else if (items[i].getItemId() == is.getItemId()) {
				amount = items[i].add(amount);
				is.setAmount(amount);
			}
			i++;
		}
		return (amount == 0) ? null : is;
	}
	
	public ItemStack removeItem(ItemStack is) {
		int i = 0;
		int amount = is.getAmount();
		while(amount > 0 && i < items.length) {
			if (items[i] != null && items[i].getItemId() == is.getItemId()) {
				amount = items[i].remove(amount);
				is.setAmount(amount);
			}
			i++;
		}
		return (amount == 0) ? null : is;
	}

	public ItemStack getSlot(int slot) {
		return items[slot];
	}

}
