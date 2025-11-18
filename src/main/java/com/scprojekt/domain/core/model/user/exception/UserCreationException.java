package com.scprojekt.domain.core.model.user.exception;

import com.scprojekt.domain.core.model.user.entity.UserNumber;

/**
 * Exception thrown when there is an error creating a user.
 * This can be due to validation errors or other business rule violations.
 */
public class UserCreationException extends UserException {

    private static final long serialVersionUID = 1L;
    private UserNumber userNumber;

    /**
     * Creates a new exception with a specific user number
     * 
     * @param userNumber The user number that caused the exception
     */
    public UserCreationException(UserNumber userNumber) {
        this.userNumber = userNumber;
    }

    /**
     * Creates a new exception with a custom message
     * 
     * @param message The error message
     */
    public UserCreationException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with a custom message and a cause
     * 
     * @param message The error message
     * @param cause The cause of the exception
     */
    public UserCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        if (userNumber != null) {
            return String.format("Cannot create user with %s id", this.userNumber);
        }
        return super.getMessage();
    }
}
