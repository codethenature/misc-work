package com.expedia.app.domain;

import java.util.ArrayList;

public class Bill {
	ArrayList<ItemsPerQuantity> items;
	
	public Bill() {
		items = new ArrayList<ItemsPerQuantity>();
	}
	
	public void addItemToBill(ItemsPerQuantity item) {
		items.add(item);
	}
	
	public double getSaleTax() {
		double sum = 0;
		
		for(ItemsPerQuantity item : items) {
			sum += item.getSalesTax();
		}
		
		return sum;
	}
	
	public double getTotalBill() {
		double sum = 0;
		
		for(ItemsPerQuantity item : items) {
			sum += item.getTotalPrice();
		}
		
		return sum;
	}
	
	public String[] toStringArray() {
		
		String[] lines = new String[items.size()+2];
		int i=0;
		
		for(ItemsPerQuantity item : items) {
			String line;
			
			if(item.getItem().getContainerType() == ContainerType.NA) {
				line = String.format("%d %s : %.2f",
						item.getQuantity(), 
						item.getItem().getName(), 
						item.getTotalPrice());
			} else {
				line = String.format("%d %s of %s : %.2f ", 
						item.getQuantity(),
						item.getItem().getContainerType().getValue(),
						item.getItem().getName(),
						item.getTotalPrice());
			}
			lines [i++] = line;
		}
		if(i==0) {
			lines[i++] = "Error Message";
		}else {
			lines [i++] = String.format("Sales Taxes : %.2f", getSaleTax());
			lines [i++] = String.format("Total : %.2f", getTotalBill());
		}
		
		
		return lines;
	}
}
