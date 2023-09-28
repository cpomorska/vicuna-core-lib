package com.scprojekt.domain.core.shared.dto;

import com.scprojekt.domain.core.shared.misc.BaseEventType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseRequest {

    @NotNull(message = "Please provide an event type")
    public BaseEventType baseEventType;

    @NotNull(message = "Please provide an event type")
    public RequestType requestType;
}