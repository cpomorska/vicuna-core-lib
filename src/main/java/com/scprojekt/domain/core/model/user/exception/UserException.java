package com.scprojekt.domain.core.model.user.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserException extends Exception {

    private static final long serialVersionUID = 1L;

    public UserException(Exception e){
        super(e);
    }
}
