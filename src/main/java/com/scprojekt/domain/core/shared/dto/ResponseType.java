package com.scprojekt.domain.core.shared.dto;

public enum ResponseType {
    USER("User"),
    CUSTOMER("Customer"),
    ACCOUNT("Account"),
    ASSURANCE("Assurance");

    private final String response;

    ResponseType(String r) {
        response = r;
    }

    public boolean equalsEvent(String otherResponse) {
        return response.equals(otherResponse);
    }

    public String getValue() {
        return this.response;
    }
    @Override
    public String toString() {
        return this.getValue();
    }
}
