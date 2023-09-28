package com.scprojekt.domain.core.model.user.event;

public enum UserEventType {
    CREATE("Create"),
    UPDATE("Update"),
    DELETE("Delete"),
    MANAGE("Manage"),
    DISABLE("Disable"),
    READ("Read"),
    NONE("None");

    private final String event;

    UserEventType(String ev) {
        event = ev;
    }

    public boolean equalsEvent(String otherEvent) {
        return event.equals(otherEvent);
    }

    public String getValue() {
        return this.event;
    }
    @Override
    public String toString() {
        return this.getValue();
    }
}
