package com.litmus7.rental.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.litmus7.rental.dto.Bike;
import com.litmus7.rental.dto.Car;
import com.litmus7.rental.dto.Vehicle;
import com.litmus7.rental.exception.VehicleDataAccessException;

public class VehicleDao {
	
	private List<Vehicle> vehicles = new ArrayList<>();
	
	public List<Vehicle> loadVehiclesFromFile(String filePath) throws VehicleDataAccessException {
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
			throw new VehicleDataAccessException("Error processing vehicle data from file: " + filePath, e);
		}
		
		catch (NumberFormatException e) {
			throw new VehicleDataAccessException("Invalid data format: " + filePath, e);
		}
		
		return vehicles;
	}
	

}
