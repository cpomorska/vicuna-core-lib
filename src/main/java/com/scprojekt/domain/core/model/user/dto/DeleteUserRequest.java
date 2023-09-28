package com.scprojekt.domain.core.model.user.dto;

import com.scprojekt.domain.core.model.user.event.UserEventType;
import com.scprojekt.domain.core.shared.dto.BaseRequest;
import com.scprojekt.domain.core.model.user.entity.UserNumber;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class DeleteUserRequest extends BaseRequest {

    UserEventType userEventType = UserEventType.DELETE;

    @NotBlank(message = "Please provide username")
    @Size(min = 5, max = 255, message = "Please provide a valid username")
    String userName;

    @NotNull(message = "Please provide a user number")
    UserNumber userNumber;
}