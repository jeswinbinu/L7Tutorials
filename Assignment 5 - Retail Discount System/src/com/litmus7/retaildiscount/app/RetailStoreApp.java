package com.litmus7.retaildiscount.app;

import java.util.Scanner;

import com.litmus7.retaildiscount.dto.Discountable;
import com.litmus7.retaildiscount.dto.*;

public class RetailStoreApp {
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter customer type (1 -> Regular Customer"
				+ "2 -> Premium Customer"
				+ "3 -> Wholesale Customer");
		
		int customerType = scanner.nextInt();
		
		Discountable customer = null;
		
		switch (customerType) {
			case 1:
				customer = new RegularCustomer();
                System.out.println("Customer Type: Regular Customer");
                break;
            
			case 2:
				customer = new PremiumCustomer();
                System.out.println("Customer Type: Premium Customer");
                break;
            
			case 3:
				customer = new WholesaleCustomer();
                System.out.println("Customer Type: Wholesale Customer");
                break;
               
            default:
                System.out.println("Invalid customer type.");
                scanner.close();
                return;
            	
		}
		
		System.out.println("Enter total purchase amount: ");
		double totalAmount = scanner.nextDouble();
		
		double discountedAmount = customer.applyDiscount(totalAmount);
		double discountAmountApplied = totalAmount - discountedAmount;
		
        System.out.printf("Original Amount: ₹%.2f%n", totalAmount);
        System.out.printf("Discount Applied: ₹%.2f%n", discountAmountApplied);
        System.out.printf("Final Payable Amount: ₹%.2f%n", discountedAmount);

        scanner.close();
		
		
	}
}
