package com.scprojekt.domain.model.user;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserException extends Exception {

    private static final long serialVersionUID = 1L;

    public UserException(Exception e){
        super(e);
    }
}
