package org.launchcode.projectmanagement.models;

public enum Status {

    BACKLOG("Backlog"),
    READY("Ready"),
    INPROGRES("In-Progress"),
    TESTING("Testing"),
    COMPLETE("Complete");


    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}

