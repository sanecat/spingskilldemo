package com.example.skillsdemo.model;

/**
 * Enum representing the proficiency level of a skill
 */
public enum ProficiencyLevel {
    BEGINNER("初学者"),
    INTERMEDIATE("中级"),
    ADVANCED("高级"),
    EXPERT("专家");
    
    private final String displayName;
    
    ProficiencyLevel(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
