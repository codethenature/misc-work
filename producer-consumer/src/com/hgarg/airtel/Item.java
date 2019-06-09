package com.hgarg.airtel;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;

public class Item {
	private int val;
	
	// 1st method --working
	
/*	static Semaphore prod = new Semaphore(1);
	static Semaphore cons = new Semaphore(0);
	
	public int get() {
		try {
			cons.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int temp = val;
		prod.release();
		return temp;
	}
	
	public void put(int val) {
		try {
			prod.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.val = val;
		cons.release();
	}*/
	
	
	// 2nd method --still needs to work on
	// locks same strategy as Semaphores
	
	boolean lockA = true;
	boolean lockB = false;
	
	public synchronized int get() {
		int temp;
		try {
			if(lockA) {
				wait();
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		temp = val;

		if(lockB) {
			notify();
		}
		lockA = true;
		return temp;
	}
	
	public synchronized void put(int val) {
		try {
			if(lockB) {
				wait();
			}
			   
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.val= val;
		
		if(lockA) {
			notify();
		}
		lockB = true;
		
	}
}
