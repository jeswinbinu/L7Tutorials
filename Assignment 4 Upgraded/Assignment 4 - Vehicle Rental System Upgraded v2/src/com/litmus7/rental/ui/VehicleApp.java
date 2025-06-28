package com.litmus7.rental.ui;

import java.util.List;
import java.util.Scanner;

import com.litmus7.rental.controller.VehicleController;
import com.litmus7.rental.dto.*;
import com.litmus7.rental.exception.VehicleServiceException;

/**
 * The main application class to demonstrate functionality of the Vehicle, Car, and Bike classes.
 * This class uses both default and parameterized constructors to create instances of Car and Bike,
 * collects input from the user, and displays vehicle details to the console.
 */
public class VehicleApp {

    /**
     * Default constructor
     */
    public VehicleApp() {
    }

    /**
     * The entry point of the application.
     * Creates instances of Car and Bike using both default and parameterized constructors,
     * prompts the user for vehicle details where applicable, and displays the stored information.
     *
     * @param args Command-line arguments (unused here)
     */
    public static void main(String[] args) {
    	
        VehicleController controller = null;
		controller = new VehicleController("vehicles.txt");
		Response response = controller.getResponse();
		
		if (response.getStatusCode() == ResponseStatus.ERROR_STATUS_CODE) {
			System.err.println(response.getErrorMessage());
			System.out.println("Exiting");
			return;
		}
		
        System.out.println("Vehicle list loaded");
        

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Vehicle Rental System ---");
            System.out.println("1. View All Vehicles");
            System.out.println("2. Rent a Vehicle");
            System.out.println("3. Return a Vehicle");
            System.out.println("4. View Available Vehicles");
            System.out.println("5. Add a New Vehicle");
            System.out.println("6. Search Vehicles by Brand/Model");
            System.out.println("7. Calculate Total Rental Price");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                	List<Vehicle> vehicles = controller.getAllVehicles();
                	displayAllVehicles(vehicles);
                    break;
                case 2:
                    rentVehicle(scanner, controller);
                    break;
                case 3:
                    returnVehicle(scanner, controller);
                    break;
                case 4:
                    displayAvailableVehicles(controller.getAvailableVehicles());
                    break;
                case 5:
                    addNewVehicle(scanner, controller);
                    break;
                case 6:
                    searchVehicles(scanner, controller);
                    break;
                case 7:
                    System.out.println("Combined rental price of all vehicles per day: " + controller.getTotalRentalPrice());
                    break;
                case 8:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    /**
     * Displays all vehicles in the given list.
     *
     * @param vehicles List of vehicles to display
     */
    private static void displayAllVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles available for renting");
            return;
        }

        for (int i = 0; i < vehicles.size(); i++) {
            System.out.println("Vehicle " + (i + 1) + ":");
            displayVehicleDetails(vehicles.get(i));
            System.out.println();
        }
    }
    
    /**
     * Displays all vehicles in the given list which are not rented out.
     *
     * @param vehicles List of vehicles to display
     */
    private static void displayAvailableVehicles(List<Vehicle> vehicles) {
    	if (vehicles.isEmpty()) {
            System.out.println("No vehicles available.");
            return;
        }

        for (int i = 0; i < vehicles.size(); i++) {
            System.out.println("Vehicle " + (i + 1) + ":");
            displayVehicleDetails(vehicles.get(i));
            System.out.println();
        }

    }
    
    /**
     * Prompts the user to rent a vehicle by index.
     *
     * @param scanner Input scanner
     * @param service VehicleController instance
     */
    private static void rentVehicle(Scanner scanner, VehicleController controller) {
        System.out.print("Enter vehicle number to rent: ");
        int rentIndex = scanner.nextInt() - 1;
        boolean isRented = controller.rentVehicle(rentIndex);
        
        if(isRented) {
            System.out.println("Vehicle " + (rentIndex + 1) + " has been successfully rented.");
        } else {
            System.out.println("Vehicle " + (rentIndex + 1) + " not available.");
        }
    }

    /**
     * Prompts the user to return a vehicle by index.
     *
     * @param scanner Input scanner
     * @param service VehicleController instance
     */
    private static void returnVehicle(Scanner scanner, VehicleController controller) {
        System.out.print("Enter vehicle number to return: ");
        int returnIndex = scanner.nextInt() - 1;
        boolean hasBeenReturned = controller.returnVehicle(returnIndex);
        
        if(hasBeenReturned) {
        	System.out.println("Vehicle " + (returnIndex + 1) + " has been successfully returned.");
        } else {
        	System.out.println("Vehicle " + (returnIndex + 1) + " is already available (not rented out).");
        }
    }

    /**
     * Prompts the user to add a new vehicle.
     *
     * @param scanner Input scanner
     * @param service VehicleController instance
     */
    private static void addNewVehicle(Scanner scanner, VehicleController controller) {
        System.out.println("Enter vehicle type (Car/Bike)");
        String type = scanner.next();
        if ("Car".equalsIgnoreCase(type)) {
            System.out.println("Enter car details: ");
            Car car = getCarInput(scanner);
            controller.addVehicle(car);
        } else if ("Bike".equalsIgnoreCase(type)) {
            System.out.println("Enter bike details: ");
            Bike bike = getBikeInput(scanner);
            controller.addVehicle(bike);
        } else {
            System.out.println("Enter a valid vehicle type!");
        }
        System.out.println("Updated vehicle list");
        displayAllVehicles(controller.getAllVehicles());
    }

    /**
     * Prompts the user to search for vehicles by brand or model.
     *
     * @param scanner Input scanner
     * @param service VehicleController instance
     */
    private static void searchVehicles(Scanner scanner, VehicleController controller) {
        System.out.println("Enter search query as either the brand or model of vehicle");
        String query = scanner.nextLine();
        List<Vehicle> results = controller.searchVehicles(query);
        if (!results.isEmpty()) {
            for (Vehicle vehicle : results) {
            	displayVehicleDetails(vehicle);
                System.out.println();
            }
        } else {
            System.out.println("0 matches found.");
        }
    }
    
    /**
     * Displays vehicle details to the console.
     */
    private static void displayVehicleDetails(Vehicle vehicle) {
        if (vehicle == null) {
            System.out.println("Vehicle is null.");
            return;
        }

        System.out.println("Brand: " + vehicle.getBrand());
        System.out.println("Model: " + vehicle.getModel());
        System.out.println("Rental Price Per Day: " + vehicle.getRentalPricePerDay());

        if (vehicle instanceof Car) {
            Car car = (Car) vehicle;
            System.out.println("Number of Doors: " + car.getNumberOfDoors());
            System.out.println("Automatic: " + car.isAutomatic());
        } else if (vehicle instanceof Bike) {
            Bike bike = (Bike) vehicle;
            System.out.println("Has Gear: " + bike.isHasGear());
            System.out.println("Engine Capacity: " + bike.getEngineCapacity() + " cc");
        }

        System.out.println();
    }
    
    /**
     * Prompts the user for vehicle details and returns a Car object.
     */
    private static Car getCarInput(Scanner scanner) {
    	
    	scanner.nextLine();
        System.out.print("Enter brand: ");
        String brand = scanner.nextLine();
        
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        
        System.out.print("Enter rental price per day: ");
        double rentalPricePerDay = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("Enter number of doors: ");
        int numberOfDoors = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Is it automatic (true/false)? ");
        boolean isAutomatic = scanner.nextBoolean();
        scanner.nextLine();

        return new Car(brand, model, rentalPricePerDay, numberOfDoors, isAutomatic);
    }
    
    /**
     * Prompts the user for vehicle details and returns a Bike object.
     */
    private static Bike getBikeInput(Scanner scanner) {
        System.out.print("Enter brand: ");
        String brand = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        System.out.print("Enter rental price per day: ");
        double rentalPricePerDay = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Does it have gears (true/false)? ");
        boolean hasGear = scanner.nextBoolean();
        scanner.nextLine();
        System.out.print("Enter engine capacity (in cc): ");
        int engineCapacity = scanner.nextInt();
        scanner.nextLine();

        return new Bike(brand, model, rentalPricePerDay, hasGear, engineCapacity);
    }
    
    
}