package com.example.education_spring_boot.enums;

public enum PriorityGroupEnum {
    NONE("None"),
    CON_THUONG_BINH("Con thương binh"),
    VUNG_SAU("Vùng sâu");

    private String priorityGroup;

    PriorityGroupEnum(String priorityGroup) {
        this.priorityGroup = priorityGroup;
    }
}
