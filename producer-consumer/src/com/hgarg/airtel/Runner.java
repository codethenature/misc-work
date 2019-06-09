package com.hgarg.airtel;

public class Runner {
	
	public static void main(String[] args) {
		// Common Item
		Item item = new Item();
		
		Thread producer= new Thread(new Producer(item));
		Thread consumer= new Thread(new Consumer(item));
		producer.start();
		consumer.start();
	}
}
