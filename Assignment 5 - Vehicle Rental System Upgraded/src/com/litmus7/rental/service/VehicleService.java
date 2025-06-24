package com.litmus7.rental.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.litmus7.rental.dto.*;


/**
 * Service class to manage vehicle operations.
 */
public class VehicleService {
	private List<Vehicle> vehicles = new ArrayList<>();
	
	public void loadVehiclesFromFile(String filePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] lineParts = line.split(",");
				if (lineParts.length >= 5) {
					String type = lineParts[0];
					String brand = lineParts[1];
					String model = lineParts[2];
					double rentalPricePerDay = Double.parseDouble(lineParts[3]);
					
					if ("Car".equals(type)) {
						int numberOfDoors = Integer.parseInt(lineParts[4]);
						boolean isAutomatic = Boolean.parseBoolean(lineParts[5]);
						vehicles.add(new Car(brand, model, rentalPricePerDay, numberOfDoors, isAutomatic));
					}
					else if ("Bike".equals(type)) {
						boolean hasGear = Boolean.parseBoolean(lineParts[4]);
						int engineCapacity = Integer.parseInt(lineParts[5]);
						vehicles.add(new Bike(brand, model, rentalPricePerDay, hasGear, engineCapacity));
					}
				}
			}
		}
		
		catch (IOException e) {
			System.err.println("Error reading file -> " + e.getMessage());
		}
	}
	
	/**
	 * Adds a new vehicle to the collection
	 * 
	 * @param vehicle the vehicle to add
	 */
	public void addVehicle (Vehicle vehicle) {
		vehicles.add(vehicle);
	}
	
	/**
	 * Displays all vehicles available for renting
	 */
	public void displayAllVehicles() {
		if (vehicles.isEmpty()) {
			System.out.println("No vehicles available for renting");
			return;
		}
		
		for (int i = 0; i < vehicles.size(); i++) {
            System.out.println("Vehicle " + (i + 1) + ":");
            vehicles.get(i).displayDetails();
            System.out.println();
		}
		
	}
	
	/**
	 * Searches for vehicle based on model or brand query given 
	 * 
	 * @param query The search query : either brand or model 
	 */
	public void searchVehicles (String query) {
		boolean foundVehicle = false;
		for (Vehicle vehicle : vehicles) {
			if (vehicle.getBrand().equalsIgnoreCase(query) || vehicle.getModel().equalsIgnoreCase(query)) {
				vehicle.displayDetails();
				System.out.println();
				foundVehicle = true;
			}
		}
		if (!foundVehicle) {
			System.out.println("Vehicle not found!");
		}
	}
	
	/**
	 * Calculates the total rental price for the all the vehicles per day
	 * 
	 * @return Total rental price
	 */
	public double calculateTotalRentalPrice() {
		double total = 0.0;
		for (Vehicle vehicle : vehicles) {
			total += vehicle.getRentalPricePerDay();
		}
		return total;
	}
	
	/**
	 * Rents a vehicle based on the vehicle number/index in display
	 * 
	 * @param index The index of the vehicle from the list of all vehicles
	 */
    public void rentVehicle(int index) {
        if (index >= 0 && index < vehicles.size()) {
            Vehicle v = vehicles.get(index);
            if (!v.isRented()) {
                v.setRented(true);
                System.out.println("Vehicle " + (index + 1) + " has been rented.");
            } else {
                System.out.println("Vehicle " + (index + 1) + " is not available.");
            }
        } else {
            System.out.println("Invalid vehicle index.");
        }
    }
    
    /**
     * Returns a vehicle based on the vehicle number/index in display
     * 
	 * @param index The index of the vehicle from the list of all vehicles
     */
    public void returnVehicle(int index) {
        if (index >= 0 && index < vehicles.size()) {
            Vehicle v = vehicles.get(index);
            if (v.isRented()) {
                v.setRented(false);
                System.out.println("Vehicle " + (index + 1) + " has been returned.");
            } else {
                System.out.println("Vehicle " + (index + 1) + " is not currently rented.");
            }
        } else {
            System.out.println("Invalid vehicle index.");
        }
    }
    
    
    public void displayAvailableVehicles() {
    	boolean found = false;
    	System.out.println("Available Vehicles");
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle v = vehicles.get(i);
            if (!v.isRented()) {
                System.out.println("Vehicle " + (i + 1) + ":");
                v.displayDetails();
                System.out.println();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No vehicles are currently available.");
        }
    }
}
