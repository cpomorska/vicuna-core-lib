package com.scprojekt.domain.core.shared.misc;

public enum BaseEventType{
    CREATE("Create"),
    UPDATE("Update"),
    DELETE("Delete"),
    MANAGE("Manage"),
    DISABLE("Disable"),
    READ("Read"),
    NONE("None");

    private final String event;

    BaseEventType(String ev) {
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
