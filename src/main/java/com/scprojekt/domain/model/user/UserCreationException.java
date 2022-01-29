package com.scprojekt.domain.model.user;

import lombok.NoArgsConstructor;

public class UserCreationException extends UserException {
    private final UserNumber userNumber;

    public UserCreationException(UserNumber userNumber){
        this.userNumber = userNumber;
    }

    @Override
    public String getMessage(){
        return(String.format("Can not create user with %s id", this.userNumber));
    }
}
