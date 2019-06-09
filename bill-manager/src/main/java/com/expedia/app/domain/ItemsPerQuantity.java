package com.expedia.app.domain;

public class ItemsPerQuantity {
	Item item;
	
	int quantity;
	
	public ItemsPerQuantity(Item item, int quantity) {
		super();
		this.item = item;
		this.quantity = quantity;
	}
	
	public double getSalesTax() {
		return item.getSalesTax() * quantity;
	}
	
	public double getTotalPrice() {
		return item.getPrice() * quantity + getSalesTax();
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
