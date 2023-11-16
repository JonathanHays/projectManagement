package org.launchcode.projectmanagement.models;

public enum Size {

    SMALL("Small"),
    MEDIUM("Medium"),
    LARGE("Large"),
    XLARGER("X-Large");

    private final String displayName;

    Size(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
