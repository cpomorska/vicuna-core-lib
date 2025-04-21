package com.scprojekt.domain.core.model.customer.exception;

/**
 * Exception thrown when there is an error creating a customer.
 */
public class CustomerCreationException extends RuntimeException {

    /**
     * Constructs a new CustomerCreationException with the specified detail message.
     *
     * @param message the detail message
     */
    public CustomerCreationException(String message) {
        super(message);
    }

    /**
     * Constructs a new CustomerCreationException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public CustomerCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}