package com.jb.school;

import com.jb.school.dto.StudentDTO;
import com.jb.school.StudentOperations;
import java.util.Scanner;

/**
 * The main application class for managing student report card generation.
 * This class allows the user to input details for multiple students,
 * and then displays a formatted report card for each student including:
 * Name
 * Roll number
 * Total marks
 * Average mark
 * Grade
 */
public class StudentReportApp {
	
	/**
	 * Default constructor 
	 */
	public StudentReportApp() {
		
	}
    /**
     * The entry point of the application.
     * Prompts the user to enter the number of students, followed by each student's details.
     * After collecting all data, it generates and prints the report card for each student.
     *
     * @param args command-line arguments (unused though)
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentOperations operations = new StudentOperations();

        System.out.print("Number of students to enter: ");
        int studentCount = scanner.nextInt();
        StudentDTO[] students = new StudentDTO[studentCount];

        for (int i = 0; i < studentCount; i++) {
            System.out.println("Enter details for student " + (i + 1));
            students[i] = new StudentDTO();
            operations.inputDetails(students[i]);
            System.out.println();
        }

        System.out.println("\nSTUDENT MARKLIST\n");
        for (StudentDTO student : students) {
            operations.printReportCard(student);
            System.out.println("--------------------------------");
        }

        scanner.close();
    }
}