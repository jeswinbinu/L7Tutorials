package com.litmus7.rental.controller;


import java.util.List;
import com.litmus7.rental.dto.Vehicle;
import com.litmus7.rental.dto.Response;
import com.litmus7.rental.dto.ResponseStatus;
import com.litmus7.rental.exception.VehicleServiceException;
import com.litmus7.rental.service.VehicleService;


public class VehicleController {
	private VehicleService vehicleService = null;
	private Response<List<Vehicle>> response;
	
	public VehicleController(String filePath) {
		this.response = new Response<>();
        try {
			this.vehicleService = new VehicleService(filePath);
			response.setStatusCode(ResponseStatus.SUCCESS_STATUS_CODE);
			response.setData(vehicleService.getAllVehicles());
		} catch (VehicleServiceException e) {
			response.setStatusCode(ResponseStatus.ERROR_STATUS_CODE);
			response.setErrorMessage("Failed to load vehicle data!");
			response.setErrorDetails(getStackTraceAsString(e));
		}
    }
	
    /**
     * @return
     */
    public Response<List<Vehicle>> getResponse() {
        return response;
    }
	
	/**
	 * @return
	 */
    public Response<List<Vehicle>> getAllVehicles() {
        Response<List<Vehicle>> response = new Response<>();
        try {
            List<Vehicle> vehicles = vehicleService.getAllVehicles();
            response.setData(vehicles);
            response.setStatusCode(ResponseStatus.SUCCESS_STATUS_CODE);
        } catch (Exception e) {
            response.setStatusCode(ResponseStatus.ERROR_STATUS_CODE);
            response.setErrorMessage("Failed to retrieve vehicle list.");
            response.setErrorDetails(getStackTraceAsString(e));
        }
        return response;
    }
	/**
	 * @return
	 */
    public Response<List<Vehicle>> getAvailableVehicles() {
        Response<List<Vehicle>> response = new Response<>();
        try {
            List<Vehicle> availableVehicles = vehicleService.returnAvailableVehicles();
            response.setData(availableVehicles);
            response.setStatusCode(ResponseStatus.SUCCESS_STATUS_CODE);
        } catch (Exception e) {
            response.setStatusCode(ResponseStatus.ERROR_STATUS_CODE);
            response.setErrorMessage("Failed to retrieve available vehicles.");
            response.setErrorDetails(getStackTraceAsString(e));
        }
        return response;
    }
	 
	/**
	 * @param query
	 * @return
	 */
    public Response<List<Vehicle>> searchVehicles(String query) {
        Response<List<Vehicle>> response = new Response<>();

        if (query == null || query.trim().isEmpty()) {
            response.setStatusCode(ResponseStatus.ERROR_STATUS_CODE);
            response.setErrorMessage("Search query cannot be empty.");
            return response;
        }

        try {
            List<Vehicle> results = vehicleService.searchVehicles(query.trim());
            response.setData(results);
            response.setStatusCode(ResponseStatus.SUCCESS_STATUS_CODE);
        } catch (Exception e) {
            response.setStatusCode(ResponseStatus.ERROR_STATUS_CODE);
            response.setErrorMessage("Error occurred while searching vehicles.");
            response.setErrorDetails(getStackTraceAsString(e));
        }

        return response;
    }
	
	/**
	 * @param index
	 * @return
	 */
	public Response<Boolean> rentVehicle(int index) {
	    Response<Boolean> response = new Response<>();
	    
	    try {
	        if (!isValidIndex(index)) {
	            response.setStatusCode(ResponseStatus.ERROR_STATUS_CODE);
	            response.setErrorMessage("Invalid vehicle index.");
	            return response;
	        }

	        boolean isRented = vehicleService.rentVehicle(index);
	        if (!isRented) {
	            response.setStatusCode(ResponseStatus.ERROR_STATUS_CODE);
	            response.setErrorMessage("Vehicle at index " + (index + 1) + " is already rented.");
	        } else {
	            response.setStatusCode(ResponseStatus.SUCCESS_STATUS_CODE);
	            response.setData(true);
	        }
	        
	    } catch (Exception e) {
	        response.setStatusCode(ResponseStatus.ERROR_STATUS_CODE);
	        response.setErrorMessage("An unexpected error occurred while renting the vehicle.");
	        response.setErrorDetails(getStackTraceAsString(e));
	    }

	    return response;
	}

	/**
	 * @param index
	 * @return
	 */
	public Response<Boolean> returnVehicle(int index) {
	    Response<Boolean> response = new Response<>();

	    if (!isValidIndex(index)) {
	        response.setStatusCode(ResponseStatus.ERROR_STATUS_CODE);
	        response.setErrorMessage("Invalid vehicle index: " + (index + 1));
	        return response;
	    }

	    try {
	        boolean hasBeenReturned = vehicleService.returnVehicle(index);
	        if (!hasBeenReturned) {
	            response.setStatusCode(ResponseStatus.ERROR_STATUS_CODE);
	            response.setErrorMessage("Vehicle at index " + (index + 1) + " was not rented.");
	        } else {
	            response.setData(true);
	            response.setStatusCode(ResponseStatus.SUCCESS_STATUS_CODE);
	        }
	    } catch (Exception e) {
	        response.setStatusCode(ResponseStatus.ERROR_STATUS_CODE);
	        response.setErrorMessage("An unexpected error occurred while returning the vehicle.");
	        response.setErrorDetails(getStackTraceAsString(e));
	    }

	    return response;
	}
	/**
	 * @param vehicle
	 */
	public Response<Vehicle> addVehicle(Vehicle vehicle) {
	    Response<Vehicle> response = new Response<>();

	    if (vehicle == null) {
	        response.setStatusCode(ResponseStatus.ERROR_STATUS_CODE);
	        response.setErrorMessage("Cannot add null vehicle.");
	        return response;
	    }

	    try {
	        vehicleService.addVehicle(vehicle);
	        response.setData(vehicle);
	        response.setStatusCode(ResponseStatus.SUCCESS_STATUS_CODE);
	    } catch (Exception e) {
	        response.setStatusCode(ResponseStatus.ERROR_STATUS_CODE);
	        response.setErrorMessage("Failed to add vehicle.");
	        response.setErrorDetails(getStackTraceAsString(e));
	    }

	    return response;
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

    private String getStackTraceAsString(Exception ex) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : ex.getStackTrace()) {
            sb.append(element.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
