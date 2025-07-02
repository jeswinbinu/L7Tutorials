package com.litmus7.rental.exception;

public class VehicleServiceException extends Exception{
	
	/**
	 * @param message
	 */
	public VehicleServiceException(String message) {
		super(message);
	}
	
	/**
	 * @param message
	 * @param cause
	 */
	public VehicleServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
