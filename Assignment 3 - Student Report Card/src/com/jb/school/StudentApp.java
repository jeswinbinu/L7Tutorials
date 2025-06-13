package com.jb.school;

import com.jb.school.dto.Student;
import java.util.Scanner;

public class StudentApp {
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Number of students to enter: ");
		int studentCount = scanner.nextInt();
		scanner.nextLine();
		
//		scanner.close();
		
		Student[] students = new Student[studentCount];
		
		for (int i = 0; i < studentCount; i++) {
			students[i] = new Student();
			students[i].inputDetails();
		}

		System.out.println("\nSTUDENT MARKLIST\n");
		for (Student student : students) {
			student.printReportCard();
		}
	}
}	
