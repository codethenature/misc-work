package com.expedia.app.calc;

import com.expedia.app.domain.Bill;

public class BillManager {
	
	public static String[] calculate(Bill bill) {
		return bill.toStringArray();
	}
}
