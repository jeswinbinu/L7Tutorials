package com.litmus7.rental.dto;


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

	@Override
	public String toString() {
		return "Car [numberOfDoors=" + numberOfDoors + ", isAutomatic=" + isAutomatic + ", getBrand()=" + getBrand()
				+ ", getModel()=" + getModel() + ", getRentalPricePerDay()=" + getRentalPricePerDay() + ", isRented()="
				+ isRented() + "]";
	}

	/**
	 * @return the numberOfDoors
	 */
	public int getNumberOfDoors() {
		return numberOfDoors;
	}

	/**
	 * @param numberOfDoors the numberOfDoors to set
	 */
	public void setNumberOfDoors(int numberOfDoors) {
		this.numberOfDoors = numberOfDoors;
	}

	/**
	 * @return the isAutomatic
	 */
	public boolean isAutomatic() {
		return isAutomatic;
	}

	/**
	 * @param isAutomatic the isAutomatic to set
	 */
	public void setAutomatic(boolean isAutomatic) {
		this.isAutomatic = isAutomatic;
	}
	
	
}
