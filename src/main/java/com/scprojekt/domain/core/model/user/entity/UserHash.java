package com.scprojekt.domain.core.model.user.entity;

import com.scprojekt.domain.core.shared.database.SQLInjectionSafe;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserHash {

    @NotNull
    @SQLInjectionSafe
    @Column(name="benutzerhash")
    String hashField;

    @NotNull
    @SQLInjectionSafe
    @Column(name="benutzersalz")
    String saltField;
}
