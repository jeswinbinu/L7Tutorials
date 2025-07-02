package com.litmus7.retaildiscount.dto;

public class PremiumCustomer implements Discountable{
	@Override
	public double applyDiscount(double totalAmount) {
		if (totalAmount > 5000) {
			return totalAmount * 0.90;
		}
		
		return totalAmount * 0.93;
	}
}
