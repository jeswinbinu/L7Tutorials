package com.litmus7.rental;

import java.util.Scanner;
import com.litmus7.rental.dto.Bike;
import com.litmus7.rental.dto.Car;


/**
 * 
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
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("\nUsing parameterized constructor of Car");
		System.out.println("---Enter Car Details---");
		Car car1 = new Car();
		car1.inputDetails();
		car1.displayDetails();
		

		System.out.println("\nUsing default constructor of Car");
		Car car2 = new Car();
		car2.displayDetails();
		
		

		System.out.println("\nUsing parameterized constructor of Bike");
		System.out.println("---Enter Bike Details---");
		Bike bike1 = new Bike();
		bike1.inputDetails();
		bike1.displayDetails();
		

		System.out.println("\nUsing default constructor of Bike");
		Bike bike2 = new Bike();
		bike2.displayDetails();
	}
}
