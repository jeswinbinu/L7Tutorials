package com.jb.school;

import com.jb.school.dto.StudentDTO;
import com.jb.school.StudentOperations;
import java.util.Scanner;

public class StudentReportApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentOperations operations = new StudentOperations();

        System.out.print("Number of students to enter: ");
        int studentCount = scanner.nextInt();
        StudentDTO[] students = new StudentDTO[studentCount];

        for (int i = 0; i < studentCount; i++) {
            System.out.println("Enter details for student #" + (i + 1));
            students[i] = new StudentDTO();
            operations.inputDetails(students[i]);
            System.out.println();
        }

        System.out.println("\nSTUDENT MARKLIST\n");
        for (StudentDTO student : students) {
            operations.printReportCard(student);
            System.out.println("-----------------------------");
        }

        scanner.close();
    }
}