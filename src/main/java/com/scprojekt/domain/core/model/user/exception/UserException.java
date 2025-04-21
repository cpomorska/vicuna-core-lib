package com.scprojekt.domain.core.model.user.exception;

import lombok.NoArgsConstructor;

import java.io.Serial;

@NoArgsConstructor
public class UserException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserException(Exception e){
        super(e);
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }
}
