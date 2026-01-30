package com.scprojekt.domain.core.model.banking.exception;

/**
 * Exception thrown when account creation fails due to validation errors
 */
public class AccountCreationException extends RuntimeException {
    
    public AccountCreationException(String message) {
        super(message);
    }
    
    public AccountCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
