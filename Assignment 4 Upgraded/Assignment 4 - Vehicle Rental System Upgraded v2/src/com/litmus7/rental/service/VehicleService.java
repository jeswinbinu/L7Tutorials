package com.litmus7.rental.service;


import java.util.ArrayList;
import java.util.List;
import com.litmus7.rental.dao.VehicleDao;
import com.litmus7.rental.dto.*;
import com.litmus7.rental.exception.VehicleDataAccessException;
import com.litmus7.rental.exception.VehicleServiceException;

/**
 * Service class to manage vehicle operations.
 */
public class VehicleService {
	
	private List<Vehicle> vehicles;
	
    /**
     * Constructor to load vehicles using the DAO
     * 
     * @param filePath Path to the file containing vehicle data
     * @throws VehicleDataAccessException 
     */
    public VehicleService(String filePath) throws VehicleServiceException {
        
        try {
        	VehicleDao vehicleDao = new VehicleDao();
        	this.vehicles = vehicleDao.loadVehiclesFromFile(filePath);
        }
        catch (VehicleDataAccessException e) {
        	throw new VehicleServiceException("Unable to load vehicle data. Please check the file format and try again.", e);
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
	 * Returns the list of all vehicles
	 *
	 * @return List of vehicles
	 */
	public List<Vehicle> getAllVehicles() {
	    return vehicles;
	}
	
	/**
	 * Searches for vehicle based on model or brand query given 
	 * 
	 * @param query The search query : either brand or model 
	 */
	public List<Vehicle> searchVehicles (String query) {
		List<Vehicle> results = new ArrayList<>();
		for (Vehicle vehicle : vehicles) {
			if (vehicle.getBrand().equalsIgnoreCase(query) 
					|| vehicle.getModel().equalsIgnoreCase(query)) {
				results.add(vehicle); 
			}
		}
		return results;
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
    public boolean rentVehicle(int index) {
        if (index >= 0 && index < vehicles.size()) {
            Vehicle v = vehicles.get(index);
            if (!v.isRented()) {
                v.setRented(true);
                return true;
            } 
        } 
        return false;  
    }
    
    /**
     * Returns a vehicle based on the vehicle number/index in display
     * 
	 * @param index The index of the vehicle from the list of all vehicles
     */
    public boolean returnVehicle(int index) {
        if (index >= 0 && index < vehicles.size()) {
            Vehicle v = vehicles.get(index);
            if (v.isRented()) {
                v.setRented(false);
                return true;
            }
        } 
        return false;
    }
    
    /**
     * @return the list of available vehicles
     */
    public List<Vehicle> returnAvailableVehicles() {
    	List<Vehicle> availableVehicles = new ArrayList<>();
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle v = vehicles.get(i);
            if (!v.isRented()) {
            	availableVehicles.add(v);
            }
        }
        return availableVehicles;
    }
}
