package com.litmus7.rental.dto;

import java.util.List;

public class Response {
	private ResponseStatus statusCode;
	private String errorMessage;
	private List<Vehicle> vehicles;
		
	/**
	 * @return the statusCode
	 */
	public ResponseStatus getStatusCode() {
		return statusCode;
	}
	
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(ResponseStatus statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	
	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	/**
	 * @return the vehicles
	 */
	public List<Vehicle> getVehicles() {
		return vehicles;
	}
	
	/**
	 * @param vehicles the vehicles to set
	 */
	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
	
	
	
}