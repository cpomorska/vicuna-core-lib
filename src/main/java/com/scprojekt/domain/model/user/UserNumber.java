package com.scprojekt.domain.model.user;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserNumber implements Serializable {
    private static final long serialVersionUID = 1L;

    @Lob
    @Column(name="benutzernummer", unique = true, updatable = false, length = 36)
    @NotEmpty(message = "benutzernummer cannot be empty.")
    private UUID uuid;
}
