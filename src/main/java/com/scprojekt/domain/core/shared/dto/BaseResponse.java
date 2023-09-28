package com.scprojekt.domain.core.shared.dto;

import com.scprojekt.domain.core.shared.misc.BaseEventType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {

    @NotNull(message = "Please provide an event type")
    public BaseEventType baseEventType;

    @NotNull(message = "Please provide a response type")
    public ResponseType responseType;
}
