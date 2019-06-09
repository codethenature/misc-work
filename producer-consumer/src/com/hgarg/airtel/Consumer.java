package com.hgarg.airtel;

public class Consumer implements Runnable{
	Item item;
	
	Consumer(Item item) {
		super();
		this.item = item;
	}
	@Override
	public void run() {
		for(int i=0; i<5; i++) {
			System.out.println(item.get());
		}
	}

}
