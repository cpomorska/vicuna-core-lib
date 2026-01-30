package com.scprojekt.domain.core.model.banking.exception;

/**
 * Base exception for banking domain operations
 */
public abstract class BankingDomainException extends RuntimeException {
    
    protected BankingDomainException(String message) {
        super(message);
    }
    
    protected BankingDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
