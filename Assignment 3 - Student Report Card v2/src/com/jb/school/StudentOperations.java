package com.jb.school;

import com.jb.school.dto.StudentDTO;
import com.jb.school.dto.Grade;
import java.util.Scanner;

/**
 * A utility class that performs certain operations on a StudentDTO object which includes:
 * Inputting student details
 * Calculating total and average marks
 * Determining the grade
 * Printing the student report card
 */
public class StudentOperations {

	/**
	 * Default constructor
	 */
	public StudentOperations() {
		
	}
	
    /**
     * Prompts the user to input student details
     * (name, roll number, and subject marks for 5 subjects)
     * and sets those values in the provided StudentDTO object.
     *
     * scanner is used to take user input
     * @param student the StudentDTO object in which the input data will be stored
     */
    public void inputDetails(StudentDTO student) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student name: ");
        student.setName(scanner.nextLine());

        System.out.print("Enter student rollNo: ");
        student.setRollNo(scanner.nextInt());

        System.out.println("\nEnter marks of the 5 subjects");
        int[] marks = new int[5];
        for (int i = 0; i < 5; i++) {
            System.out.print("Subject " + (i + 1) + " mark: ");
            marks[i] = scanner.nextInt();
        }
        student.setMarks(marks);
    }

    /**
     * Calculates the total marks obtained by the student across all subjects.
     *
     * @param student the StudentDTO object containing the student's marks
     * @return the total marks as an integer
     */
    public int calculateTotal(StudentDTO student) {
        int totalMarks = 0;
        for (int mark : student.getMarks()) {
            totalMarks += mark;
        }
        return totalMarks;
    }

    /**
     * Calculates the average mark obtained by the student.
     *
     * @param student the StudentDTO object containing the student's marks
     * @return the average mark as a double
     */
    public double calculateAverage(StudentDTO student) {
        return (double) calculateTotal(student) / student.getMarks().length;
    }

    /**
     * Determines the grade based on the average mark of the student.
     * The grading scale used is:
     * A: 90 or above
     * B: 75 to 89
     * C: 60 to 74
     * D: 50 to 59
     * F: Below 50
     *
     * @param student the StudentDTO object containing the student's marks
     * @return the corresponding Grade enum value
     */
    public Grade getGrade(StudentDTO student) {
        double averageMark = calculateAverage(student);
        if (averageMark >= 90) {
            return Grade.A;
        } else if (averageMark >= 75) {
            return Grade.B;
        } else if (averageMark >= 60) {
            return Grade.C;
        } else if (averageMark >= 50) {
            return Grade.D;
        } else {
            return Grade.F;
        }
    }

    /**
     * Prints the formatted report card of the student including:
     * Name
     * Roll Number
     * Total Marks
     * Average Mark
     * Grade
     * Grade Description
     *
     * @param student the StudentDTO object containing the student's information
     */
    public void printReportCard(StudentDTO student) {
        String report = String.format(
            "Student Name: %s%n" +
            "Roll Number: %d%n" +
            "Total marks earned: %d%n" +
            "Average mark: %.2f%n" +
            "Grade: %s (%s)",

            student.getName(),
            student.getRollNo(),
            calculateTotal(student),
            calculateAverage(student),
            getGrade(student),
            getGrade(student).getDescription()
        );

        System.out.println(report);
    }
}