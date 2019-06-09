package com.he.shoppingCart;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	/**/
	
	private HashMap<Product, Integer> items;
	private Inventory inventory;
	
	public Cart() {
		items = new HashMap<>();
		inventory = Inventory.getInstance();
	}
	
	public void addItem(Product product, Integer amount) throws Exception {
		try {
			if(amount < 1) {
				throw new Exception("Amount can't be less than 1");
			} else {
				if(inventory.getAvailableAmount(product) >= amount) {
					items.put(product, amount);
				} else {
					throw new Exception("Amount can't be more than Inventory");
				}
			}
		} catch(Exception e) {
			throw e;
		}
		
	}
	
	public void removeItem(Product product) throws Exception {
		try {
			if(items.containsKey(product)) {
				items.remove(product);
			} else {
				throw new Exception("Item not found in Cart");
			}
		} catch(Exception e) {
			throw e;
		}
		
	}
	
	public void generateInvoice() {
		double total = 0;
		DecimalFormat df =  new DecimalFormat("0.00");
		
		for(Map.Entry<Product, Integer> item : items.entrySet()) {
			Product product = item.getKey();
			int amount = item.getValue();
			double p_total = amount * product.getPrice();
					
			System.out.println(product.getName() 
					+ " " + amount
					+ " " + df.format(p_total)
					);
			total += p_total;
		}
		System.out.println("Total price: " + df.format(total));	
	}
	
	public void checkOut() throws Exception {
		try {
			inventory.removeItems(items);
			items.clear();
		} catch(Exception e) {
			throw e;
		}
		
		
	}
	public void emptyCart() {
		items.clear();
	}
	
}
