package com.scprojekt.domain.core.shared.dto;

public enum RequestType {
    USER("User"),
    CUSTOMER("Customer"),
    ACCOUNT("Account"),
    ASSURANCE("Assurance");

    private final String request;

    RequestType(String r) {
        request = r;
    }

    public boolean equalsEvent(String otherRequest) {
        return request.equals(otherRequest);
    }

    public String getValue() {
        return this.request;
    }
    @Override
    public String toString() {
        return this.getValue();
    }
}
