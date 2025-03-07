package com.example.education_spring_boot.enums;

public enum ParentRelationshipEnum {
    MOTHER("Mother"),
    FATHER("Father"),
    GUARDIAN("Guardian");

    private String parentRelationship;

    ParentRelationshipEnum(String parentRelationship) {
        this.parentRelationship = parentRelationship;
    }
}
