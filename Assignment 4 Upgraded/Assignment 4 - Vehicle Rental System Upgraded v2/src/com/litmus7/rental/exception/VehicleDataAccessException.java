package com.litmus7.rental.exception;

public class VehicleDataAccessException extends Exception {
	
	/**
	 * @param message
	 */
	public VehicleDataAccessException(String message) {
		super(message);
	}
	
	/**
	 * @param message
	 * @param cause
	 */
	public VehicleDataAccessException(String message, Throwable cause) {
		super(message, cause);
	}
}
