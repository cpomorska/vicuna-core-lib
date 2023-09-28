package com.scprojekt.domain.core.model.user.exception;

import com.scprojekt.domain.core.model.user.entity.UserNumber;

public class UserCreationException extends UserException {

	private static final long serialVersionUID = 1L;
	private final UserNumber userNumber;

    public UserCreationException(UserNumber userNumber){
        this.userNumber = userNumber;
    }

    @Override
    public String getMessage(){
        return(String.format("Can not create user with %s id", this.userNumber));
    }
}
