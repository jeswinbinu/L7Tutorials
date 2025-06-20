package com.litmus7.rental.dto;

import java.util.Scanner;

public class Bike extends Vehicle {
	private boolean hasGear;
	private int engineCapacity;
	
	/**
	 * Default constructor
	 */
	public Bike() {
		this.hasGear = true;
		this.engineCapacity = 200;
	}

	/**
	 * @param brand the brand of vehicle
	 * @param model the model of vehicle 
	 * @param rentalPricePerDay rent per day for vehicle
	 * @param hasGear whether bike has manual gear or not
	 * @param engineCapacity engine capacity in CC
	 */
	public Bike(String brand, String model, double rentalPricePerDay, boolean hasGear, int engineCapacity) {
		super(brand, model, rentalPricePerDay);
		this.hasGear = hasGear;
		this.engineCapacity = engineCapacity;		
	}

	/**
	 * Overrides the inputDetails method to include specific details for Bike.
	 * Prompts the user to enter whether the vehicle has gears and its engine capacity,
	 * then stores these values.
	 * Calls the superclass's inputDetails() method to handle common vehicle attributes first.
	 */
	@Override
	public void inputDetails() {
		super.inputDetails();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Does it have gears (true/false)? ");
        this.hasGear = scanner.nextBoolean();
        scanner.nextLine();
        System.out.print("Enter engine capacity (in cc): ");
        this.engineCapacity = scanner.nextInt();
        scanner.nextLine();
	}

	/**
	 * Overrides the displayDetails method to include specific details for Bike type.
	 * Displays whether the vehicle has gears and its engine capacity along with
	 * the common vehicle details from the superclass.
	 */
	@Override
	public void displayDetails() {
		super.displayDetails();
        System.out.println("Has Gear: " + hasGear);
        System.out.println("Engine Capacity: " + engineCapacity + " cc");
	}
	
	
	
}
