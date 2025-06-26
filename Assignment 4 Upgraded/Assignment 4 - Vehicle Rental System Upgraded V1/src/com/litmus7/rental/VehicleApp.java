package com.litmus7.rental;

import java.util.List;
import java.util.Scanner;
import com.litmus7.rental.dto.*;
import com.litmus7.rental.service.VehicleService;

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
        VehicleService service = new VehicleService();

        service.loadVehiclesFromFile("vehicles.txt");
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
                	List<Vehicle> vehicles = service.getAllVehicles();
                	displayAllVehicles(vehicles);
                    break;
                case 2:
                    rentVehicle(scanner, service);
                    break;
                case 3:
                    returnVehicle(scanner, service);
                    break;
                case 4:
                    service.displayAvailableVehicles();
                    break;
                case 5:
                    addNewVehicle(scanner, service);
                    break;
                case 6:
                    searchVehicles(scanner, service);
                    break;
                case 7:
                    System.out.println("Combined rental price of all vehicles per day: " + service.calculateTotalRentalPrice());
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
            vehicles.get(i).displayDetails();
            System.out.println(); // Separator
        }
    }
    
    /**
     * Prompts the user to rent a vehicle by index.
     *
     * @param scanner Input scanner
     * @param service VehicleService instance
     */
    private static void rentVehicle(Scanner scanner, VehicleService service) {
        System.out.print("Enter vehicle number to rent: ");
        int rentIndex = scanner.nextInt() - 1;
        service.rentVehicle(rentIndex);
    }

    /**
     * Prompts the user to return a vehicle by index.
     *
     * @param scanner Input scanner
     * @param service VehicleService instance
     */
    private static void returnVehicle(Scanner scanner, VehicleService service) {
        System.out.print("Enter vehicle number to return: ");
        int returnIndex = scanner.nextInt() - 1;
        service.returnVehicle(returnIndex);
    }

    /**
     * Prompts the user to add a new vehicle.
     *
     * @param scanner Input scanner
     * @param service VehicleService instance
     */
    private static void addNewVehicle(Scanner scanner, VehicleService service) {
        System.out.println("Enter vehicle type (Car/Bike)");
        String type = scanner.next();
        if ("Car".equalsIgnoreCase(type)) {
            System.out.println("Enter car details: ");
            Car car = new Car();
            car.inputDetails();
            service.addVehicle(car);
        } else if ("Bike".equalsIgnoreCase(type)) {
            System.out.println("Enter bike details: ");
            Bike bike = new Bike();
            bike.inputDetails();
            service.addVehicle(bike);
        } else {
            System.out.println("Enter a valid vehicle type!");
        }
        System.out.println("Updated vehicle list");
        displayAllVehicles(service.getAllVehicles());
    }

    /**
     * Prompts the user to search for vehicles by brand or model.
     *
     * @param scanner Input scanner
     * @param service VehicleService instance
     */
    private static void searchVehicles(Scanner scanner, VehicleService service) {
        System.out.println("Enter search query as either the brand or model of vehicle");
        String query = scanner.next();
        service.searchVehicles(query);
    }
}