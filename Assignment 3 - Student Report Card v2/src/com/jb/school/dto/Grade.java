package com.jb.school.dto;

/**
 * Represents the letter grades along with their descriptions.
 */
public enum Grade {
    /**
     * Grade A - Indicates excellent performance.
     */
    A("Excellent"),

    /**
     * Grade B - Indicates good performance.
     */
    B("Good"),

    /**
     * Grade C - Indicates average performance.
     */
    C("Average"),

    /**
     * Grade D - Indicates passing performance.
     */
    D("Pass"),

    /**
     * Grade F - Indicates failing performance.
     */
    F("Fail");


    private final String description;

    /**
     * default set of grade description
     * 
     * @param description
     */
    Grade(String description) {
        this.description = description;
    }

    /**
     * use to return grade description
     * 
     * @return description of a grade
     */
    public String getDescription() {
        return description;
    }
}