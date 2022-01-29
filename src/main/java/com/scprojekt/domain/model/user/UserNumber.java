package com.scprojekt.domain.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserNumber implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name="benutzernummer", unique = true, updatable = false, columnDefinition = "BINARY(16)")
    @NotEmpty(message = "benutzernummer cannot be empty.")
    private UUID uuid;
}
