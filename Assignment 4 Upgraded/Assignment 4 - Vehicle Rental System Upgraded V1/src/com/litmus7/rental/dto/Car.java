package com.litmus7.rental.dto;

import java.util.Scanner;

/**
 * A DTO build upon Vehicle representing a car with information number of doors
 * and whether car is automatic or not.
 */
public class Car extends Vehicle {
	private int numberOfDoors;
	private boolean isAutomatic;
	
	/**
	 * Default constructor
	 */
	public Car() {
		super();
		this.numberOfDoors = 2;
		this.isAutomatic = false;
	}

	/**
	 * @param brand the brand of vehicle 
	 * @param model the model of vehicle
	 * @param rentalPricePerDay rent per day for vehicle
	 * @param numberOfDoors number of doors of car
	 * @param isAutomatic whether car is automatic or not
	 */
	public Car(String brand, String model, double rentalPricePerDay, int numberOfDoors, boolean isAutomatic) {
		super(brand, model, rentalPricePerDay);
		this.numberOfDoors = numberOfDoors;
		this.isAutomatic = isAutomatic;
		
	}
	
	/**
	 * Overrides the inputDetails method to include additional details.
	 * Prompts the user to enter the number of doors and whether the vehicle is automatic,
	 * then stores these values.
	 * Calls the superclass's inputDetails() method to handle Vehicle attributes.
	 */
	@Override
	public void inputDetails() {
		super.inputDetails();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of doors: ");
        this.numberOfDoors = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Is it automatic (true/false)? ");
        this.isAutomatic = scanner.nextBoolean();
        scanner.nextLine();
	}
	
	/**
	 * Overrides the displayDetails method to include additional details.
	 * Calls the superclass's displayDetails() method to print the common vehicle attributes.
	 * Displays the number of doors and whether the vehicle is automatic.
	 */	
	@Override
	public void displayDetails() {
		super.displayDetails();
        System.out.println("Number of Doors: " + numberOfDoors);
        System.out.println("Automatic: " + isAutomatic);
	}
}
