package com.scprojekt.domain.core.model.user.exception;


import com.scprojekt.domain.core.model.user.entity.UserNumber;

public class UserDeletionException extends UserException {

	private static final long serialVersionUID = 1L;
	private final UserNumber userNumber;

    public UserDeletionException(UserNumber userNumber) {
        this.userNumber = userNumber;
    }

    @Override
    public String getMessage(){
        return(String.format("Can not delete user with %s id", this.userNumber));
    }
}
