package com.scprojekt.domain.core.model.user.dto;

import com.scprojekt.domain.core.shared.dto.BaseResponse;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UuidResponse extends BaseResponse {

    @NotNull(message = "Please provide a usernumber")
    UUID uuid;
}