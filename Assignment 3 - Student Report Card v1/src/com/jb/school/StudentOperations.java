package com.jb.school;

import com.jb.school.dto.StudentDTO;
import java.util.Scanner;

public class StudentOperations {

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

    public int calculateTotal(StudentDTO student) {
        int totalMarks = 0;
        for (int mark : student.getMarks()) {
            totalMarks += mark;
        }
        return totalMarks;
    }

    public double calculateAverage(StudentDTO student) {
        return (double) calculateTotal(student) / student.getMarks().length;
    }

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

    public void printReportCard(StudentDTO student) {
        System.out.println("Student Name: " + student.getName());
        System.out.println("Roll Number: " + student.getRollNo());
        System.out.println("Total marks earned: " + calculateTotal(student));
        System.out.printf("Average mark: %.2f%n", calculateAverage(student));
        System.out.println("Grade: " + getGrade(student));
    }

    public enum Grade {
        A, B, C, D, F
    }
}