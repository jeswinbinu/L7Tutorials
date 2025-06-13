package com.jb.school.dto;

import java.util.Scanner;

public class Student {
	private String name;
	private int rollNo;
	private int[] marks = new int[5];
	
	public void inputDetails() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter student name: ");
		this.name = scanner.nextLine();
		
		System.out.print("Enter student rollNo: ");
		this.rollNo = scanner.nextInt();
		
		System.out.print("\nEnter marks of the 5 subjects \n");
		for (int i = 0; i < 5; i++) {
			System.out.print("Subject " + (i + 1) + " mark: " );
			this.marks[i] = scanner.nextInt();
		}
//		scanner.close();
	}
	
	public int calculateTotal() {
		int totalMarks = 0;
		for (int mark : marks) {
			totalMarks += mark;
		}
		return totalMarks;
	}
	
	public double calculateAverage() {
		return (double) calculateTotal() / marks.length;
	}
	
	public char getGrade() {
		double averageMark = calculateAverage();
		if (averageMark >= 90) {
			return 'A';
		}
		else if (averageMark >= 75) {
			return 'B';
		}
		else if (averageMark >= 60) {
			return 'C';
		}
		else if (averageMark >= 50) {
			return 'D';
		}
		else {
			return 'F';
		}
	}
	
	public void printReportCard() {
		System.out.println("Student Name:" + name);
		System.out.println("Roll Number: " + rollNo);
		System.out.println("Total marks earned: " + calculateTotal());
		System.out.printf("Average mark: %.2f%n",calculateAverage());
		System.out.println("Grade: " + getGrade());
	}
}
