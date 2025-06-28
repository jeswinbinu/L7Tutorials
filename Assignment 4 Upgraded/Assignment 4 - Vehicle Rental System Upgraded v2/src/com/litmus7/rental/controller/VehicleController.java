package com.litmus7.rental.controller;


import java.util.List;
import com.litmus7.rental.dto.Vehicle;
import com.litmus7.rental.dto.Response;
import com.litmus7.rental.dto.ResponseStatus;
import com.litmus7.rental.exception.VehicleServiceException;
import com.litmus7.rental.service.VehicleService;


public class VehicleController {
	private VehicleService vehicleService = null;
	private Response response;
	
	public VehicleController(String filePath) {
		this.response = new Response();
        try {
			this.vehicleService = new VehicleService(filePath);
			response.setStatusCode(ResponseStatus.SUCCESS_STATUS_CODE);
			response.setVehicles(getAllVehicles());
		} catch (VehicleServiceException e) {
			response.setStatusCode(ResponseStatus.ERROR_STATUS_CODE);
			response.setErrorMessage("Failed to load vehicle data!");
		}
    }
	
    /**
     * @return
     */
    public Response getResponse() {
        return response;
    }
	
	/**
	 * @return
	 */
	public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

	/**
	 * @return
	 */
	public List<Vehicle> getAvailableVehicles() {
        return vehicleService.returnAvailableVehicles();
    }
	 
	/**
	 * @param query
	 * @return
	 */
	public List<Vehicle> searchVehicles(String query) {
        if (query == null || query.trim().isEmpty()) {
            throw new IllegalArgumentException("Search query cannot be empty.");
        }
        return vehicleService.searchVehicles(query.trim());
	}
	
	/**
	 * @param index
	 * @return
	 */
	public boolean rentVehicle(int index) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException("Invalid vehicle index.");
        }
        return vehicleService.rentVehicle(index);
    }

	/**
	 * @param index
	 * @return
	 */
	public boolean returnVehicle(int index) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException("Invalid vehicle index.");
        }
        return vehicleService.returnVehicle(index);
    }

	/**
	 * @param vehicle
	 */
	public void addVehicle(Vehicle vehicle) {
        vehicleService.addVehicle(vehicle);
    }
	
	/**
	 * @return
	 */
	public double getTotalRentalPrice() {
        return vehicleService.calculateTotalRentalPrice();
    }

    /**
     * @param index
     * @return
     */
    private boolean isValidIndex(int index) {
        return index >= 0 && index < vehicleService.getAllVehicles().size();
    }

}
