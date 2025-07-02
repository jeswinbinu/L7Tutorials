package com.litmus7.rental.dto;


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
	
	@Override
	public String toString() {
		return "Bike [hasGear=" + hasGear + ", engineCapacity=" + engineCapacity + ", getBrand()=" + getBrand()
				+ ", getModel()=" + getModel() + ", getRentalPricePerDay()=" + getRentalPricePerDay() + ", isRented()="
				+ isRented() + "]";
	}

	/**
	 * @return the hasGear
	 */
	public boolean isHasGear() {
		return hasGear;
	}

	/**
	 * @param hasGear the hasGear to set
	 */
	public void setHasGear(boolean hasGear) {
		this.hasGear = hasGear;
	}

	/**
	 * @return the engineCapacity
	 */
	public int getEngineCapacity() {
		return engineCapacity;
	}

	/**
	 * @param engineCapacity the engineCapacity to set
	 */
	public void setEngineCapacity(int engineCapacity) {
		this.engineCapacity = engineCapacity;
	}
	
	
}