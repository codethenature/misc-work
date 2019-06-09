package com.he.shoppingCart;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Inventory {

	static Inventory inventory;
	private ConcurrentHashMap<Product,Integer> items;
	
	private Inventory() {
		items = new ConcurrentHashMap<Product, Integer>();
	}
	
	public static Inventory getInstance() {
		if(inventory == null) {
		    inventory = new Inventory();
		}
		return inventory;
	}
	
	public void addItems(Map<Product, Integer> items) throws Exception {
		
		for(Map.Entry<Product, Integer> item : items.entrySet()) {
			if(item.getValue() <= 0) {
			    throw new Exception("Items can't be less than 1");
			}
			else {
			    if(this.items.containsKey(item.getKey())) {
			    	this.items.put(item.getKey(), this.items.get(item.getKey()) + item.getValue());
			    } else {
			    	this.items.put(item.getKey(), item.getValue());
			    }
			}
		}
		
	}
	
	public void removeItems(Map<Product, Integer> items) throws Exception {
		
		for(Map.Entry<Product, Integer> item : items.entrySet()) {
			
			if(item.getValue() <= 0) {
			    throw new Exception("Item's quantity can't be less than 1");
			}
			else {
			    if(this.items.containsKey(item.getKey())) {
			    	if(this.items.get(item.getKey()) < item.getValue()) {
			    		throw new Exception("Item's quantity can't be more than existing value");
			    		
			    	} else if(this.items.get(item.getKey()) == item.getValue()){
			    		this.items.remove(item.getKey());
			    		
			    	} else {
			    		this.items.put(item.getKey(), this.items.get(item.getKey()) - item.getValue());
			    	}
			    	
			    } else {
			    	throw new Exception("Item not found in Inventory");
			    }
			}
		}
		
	}
	
	public Integer getAvailableAmount(Product product) throws Exception {
		
		if(this.items.containsKey(product)) {
	    	return this.items.get(product);
	    } else {
	    	throw new Exception("Item not found in Inventory");
	    }
	}
	
	
	
}
