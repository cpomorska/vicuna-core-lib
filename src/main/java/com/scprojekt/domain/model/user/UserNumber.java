package com.scprojekt.domain.model.user;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserNumber implements Serializable {
    private static final long serialVersionUID = 1L;

    @Lob
    @Column(name="benutzernummer", unique = true, updatable = false, columnDefinition = "BINARY(255)")
    @NotEmpty(message = "benutzernummer cannot be empty.")
    private UUID uuid;
}
