package org.launchcode.projectmanagement.models;

public enum Priority {

    LOW("Low"),
    NORMAL("Normal"),
    HIGH("High"),
    URGENT("Urgent");


    private final String displayName;

    Priority(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
