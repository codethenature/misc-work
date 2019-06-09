package com.hgarg.airtel;

public class Producer implements Runnable{
	Item item;
	
	Producer(Item item) {
		super();
		this.item = item;
	}


	@Override
	public void run() {
		for(int i=0; i<5; i++) {
			item.put(i);
		}
	}

}
