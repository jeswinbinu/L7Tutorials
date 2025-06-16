package com.jb.school.dto;


/**
 * A Data Transfer Object (DTO) representing a student with information such as
 * name, rollNo and marks of 5 subjects.
 */
public class StudentDTO {
    private String name;
    private int rollNo;
    private int[] marks = new int[5];

    /**
     * Default constructor.
     */
    public StudentDTO() {
    	
    }
    
    /**
     * Gets the name of the student.
     * @return the student's name
     */ 
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the student.
     * @param name the name to set as student's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the rollNo of the student.
     * @return the student's rollNo
     */
    public int getRollNo() {
        return rollNo;
    }

    /**
     * Sets the rollNo of the student.
     * @param rollNo the rollNo to set as student's rollNo
     */
    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    /**
     * Gets the array of marks obtained by the student in 5 subjects.
     * @return an array of integers representing subject marks
     */
    public int[] getMarks() {
        return marks;
    }

    /**
     * Sets the marks for the student in 5 subjects.
     * @param marks an array of integers representing subject marks
     */
    public void setMarks(int[] marks) {
        this.marks = marks;
    }
}