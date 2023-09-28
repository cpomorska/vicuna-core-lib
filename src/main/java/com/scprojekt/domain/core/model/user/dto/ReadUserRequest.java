package com.scprojekt.domain.core.model.user.dto;

import com.scprojekt.domain.core.model.user.entity.UserType;
import com.scprojekt.domain.core.model.user.event.UserEventType;
import com.scprojekt.domain.core.shared.dto.BaseRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;
import java.util.*;

@NoArgsConstructor
class ReadUserRequest extends BaseRequest {

    UserEventType userEventType = UserEventType.READ;

    UserType userType = new UserType();

    @Size(min = 0, max = 255, message = "Please provide a valid username")
    String userName;

    @NotNull(message = "Please provide a user number")
    UUID userNumber;

    @Size(min = 0, max = 255, message = "Please provide a valid userdescription")
    String userDescription;
}