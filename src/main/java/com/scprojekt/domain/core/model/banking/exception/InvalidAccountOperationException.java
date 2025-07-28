package com.scprojekt.domain.core.model.banking.exception;

public class InvalidAccountOperationException extends RuntimeException  {
    public InvalidAccountOperationException(String message) {
        super(message);
    }

    protected InvalidAccountOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
