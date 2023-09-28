package com.scprojekt.domain.core.model.user.dto;

import com.scprojekt.domain.core.model.user.event.UserEventType;
import com.scprojekt.domain.core.shared.dto.BaseRequest;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import java.util.*;

@NoArgsConstructor
class UpdateUserRequest extends BaseRequest {

    UserEventType userEventType = UserEventType.UPDATE;
    @NotNull(message = "Please provide a usernumber")
    UUID userUpdate;
    String userName;
    Boolean userEnabled;
    String userDescription;
}