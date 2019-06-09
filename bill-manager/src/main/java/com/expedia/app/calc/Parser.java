package com.expedia.app.calc;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.expedia.app.domain.Bill;
import com.expedia.app.domain.ContainerType;
import com.expedia.app.domain.Item;
import com.expedia.app.domain.ItemsPerQuantity;

public class Parser {
	
	// TODO: Put this into seprate file and add config file to this.
	static HashMap<String, ContainerType> map = new HashMap<String, ContainerType>();
	static {
		map.put("pens", ContainerType.BOX);
		map.put("wine", ContainerType.BOTTLE);
		map.put("headache pills", ContainerType.BOX);
	}
	
	public static Bill parse(String[] lines) {
		Bill bill =new Bill();
		
		for(String line : lines) {
			
			String[] arr = line.split(":");
			
			double price = Double.parseDouble(arr[1].trim());
			
			String name;
			ContainerType containerType;
			int quantity;
			boolean taxFree;
			
			String[] arr2 = arr[0].split(" ");
			
			quantity = Integer.valueOf(arr2[0].trim());
			
			if(arr2.length >2) {
				
				name = arr2[3].trim();
				if(arr2.length>4) {
					name += " " + arr2[4];
				}
				boolean success = verifyContainerName(arr2[1], name);
				if(success) {
					containerType = map.get(name);
				}
				else {
					// Error in parsing. TODO: log the record.
					continue;
				}
				
				
				
			} else {
				name = arr2[1].trim();
				containerType = ContainerType.NA;
			}
			
			taxFree = checkTaxFree(name);
			Item item = new Item(price, name, containerType, taxFree);
			ItemsPerQuantity itemsPerQuantity = new ItemsPerQuantity(item, quantity);
			
			bill.addItemToBill(itemsPerQuantity);
		}
		return bill;
	}
	public static boolean checkTaxFree(String name) {
		return name.contains("pill") ? true : false;
	}
	public static boolean verifyContainerName(String container, String name) {
		if(map.containsKey(name)) {
			if(map.get(name).getValue().equals(container)) {
				return true;
			}
			else {
				return false;
			}
		} else {
			return false;
		}
	}
}
