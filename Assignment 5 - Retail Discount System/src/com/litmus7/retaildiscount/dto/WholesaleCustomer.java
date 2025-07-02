package com.litmus7.retaildiscount.dto;

public class WholesaleCustomer implements Discountable {
	@Override
	public double applyDiscount(double totalAmount) {
		if (totalAmount > 10000) {
			return totalAmount * 0.85;
		}
		return totalAmount * 0.90;
	}
}
