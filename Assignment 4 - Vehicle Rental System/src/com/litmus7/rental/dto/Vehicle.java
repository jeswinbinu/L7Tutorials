package com.litmus7.rental.dto;

import java.util.Scanner;

/**
 * A Data Transfer Object (DTO) representing a vehicle for rent with information such as
 * brand, model and rental price per day.
 */
public class Vehicle {
	private String brand;
	private String model;
	private double rentalPricePerDay;
	
	/**
	 * Default constructor
	 */
	public Vehicle() {
		this.brand = "Sample Vehicle";
		this.model = "Sample Model";
		this.rentalPricePerDay = 1000.0;
	}
	
	/**
	 * @param brand the brand of vehicle 
	 * @param model the model of vehicle
	 * @param rentalPricePerDay rent per day for vehicle
	 */
	public Vehicle(String brand, String model, double rentalPricePerDay) {
		this.brand = brand;
		this.model = model;
		this.rentalPricePerDay = rentalPricePerDay;
	}
	
	/**
	 * Collects details of the vehicle from user input.
	 * Prompts the user to enter the brand, model, and rental price per day
	 * of the vehicle and stores them.
	 */
	public void inputDetails() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter brand of vehicle: ");
		this.brand = scanner.nextLine();
		System.out.print("Enter vehicle model: ");
		this.model = scanner.nextLine();
		System.out.print("Enter rent per day of vehicle: ");
		this.rentalPricePerDay = scanner.nextDouble();	
	}
	
	/**
	 * Displays vehicle details.
	 * Outputs the brand, model, and rental price per day of the vehicle.
	 */
	public void displayDetails() {
		String vehicleDetails = String.format(
				"Brand: %s%n" + 
				"Model: %s%n" +
				"Rental Price/Day: %s%n",
				brand,
				model,
				rentalPricePerDay
		);
		System.out.print("----VEHICLE DETAILS----");
		System.out.println(vehicleDetails);		
	}
	
}
