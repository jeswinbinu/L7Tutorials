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
	private boolean isRented;
	
	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the rentalPricePerDay
	 */
	public double getRentalPricePerDay() {
		return rentalPricePerDay;
	}

	/**
	 * @param rentalPricePerDay the rentalPricePerDay to set
	 */
	public void setRentalPricePerDay(double rentalPricePerDay) {
		this.rentalPricePerDay = rentalPricePerDay;
	}
	
	/**
	 * @return the isRented
	 */
	public boolean isRented() {
		return isRented;
	}

	/**
	 * @param isRented the isRented to set
	 */
	public void setRented(boolean isRented) {
		this.isRented = isRented;
	}

	/**
	 * Default constructor
	 */
	public Vehicle() {
		this.brand = "Sample Vehicle";
		this.model = "Sample Model";
		this.rentalPricePerDay = 1000.0;
		this.isRented = false;
	}
	
	/**
	 * @param brand the brand of vehicle 
	 * @param model the model of vehicle
	 * @param rentalPricePerDay rent per day for vehicle
	 * @param isRented whether the vehicle is rented out or not
	 */
	public Vehicle(String brand, String model, double rentalPricePerDay) {
		this.brand = brand;
		this.model = model;
		this.rentalPricePerDay = rentalPricePerDay;
		this.isRented = false;		
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
		scanner.nextLine();
	}
	
	/**
	 * Displays vehicle details.
	 * Outputs the brand, model, and rental price per day of the vehicle along with it's availability.
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
		System.out.println("Status: " + (isRented ? "Rented" : "Available"));
	}
	
}
