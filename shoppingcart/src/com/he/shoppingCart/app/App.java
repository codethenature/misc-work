package com.he.shoppingCart.app;

import java.util.HashMap;

import com.he.shoppingCart.Cart;
import com.he.shoppingCart.Inventory;
import com.he.shoppingCart.Product;

public class App {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Inventory inventory = Inventory.getInstance();
		
		Product p1 = new Product("Pen", 1.1);
		Product p2 = new Product("Pencil", 1.5);
		Product p3 = new Product("Pencil", 1.5);
		
		HashMap<Product, Integer> map = new HashMap<>();
		HashMap<Product, Integer> map2 = new HashMap<>();
		map.put(p1, 10);
		map.put(p2, 10);
		
		inventory.addItems(map);
		
		Cart c1 = new Cart();
		
		c1.addItem(p1, 3);
		c1.addItem(p2, 4);
		
		c1.generateInvoice();
		c1.checkOut();
		
		System.out.println(inventory.getAvailableAmount(p1));
		System.out.println(inventory.getAvailableAmount(p2));
		
		Cart c2 = new Cart();
		
		c1.addItem(p1, 3);
		c1.addItem(p2, 4);
		
		c1.generateInvoice();
		c1.checkOut();
		
		System.out.println(inventory.getAvailableAmount(p1));
		System.out.println(inventory.getAvailableAmount(p2));
		
		map2.put(p3, 10);
		inventory.addItems(map2);
		
		System.out.println(inventory.getAvailableAmount(p1));
		System.out.println(inventory.getAvailableAmount(p2));
		System.out.println(inventory.getAvailableAmount(p3));
		System.out.println(p2.equals(p3));
		
		int arr[] = new int [3];
		   System.out.print(arr);
		
	}

}
