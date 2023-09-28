package com.scprojekt.domain.core.model.user.dto;

import com.scprojekt.domain.core.model.user.entity.UserNumber;
import com.scprojekt.domain.core.model.user.entity.UserType;
import com.scprojekt.domain.core.model.user.event.UserEventType;
import com.scprojekt.domain.core.shared.dto.BaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
class CreateUserRequest extends BaseRequest {

    UserEventType userEventType = UserEventType.CREATE;

    @NotNull(message = "Please provide a user type")
    UserType userType = new UserType();

    @NotBlank(message = "Please provide username")
    @Size(min = 5, max = 255, message = "Please provide a valid username")
    String userName;

    @NotNull(message = "Please provide a user number")
    UserNumber userNumber;

    @NotBlank(message = "Please provide a user description")
    @Size(min = 5, max = 255, message = "Please provide a valid userdescription")
    String userDescription;
}