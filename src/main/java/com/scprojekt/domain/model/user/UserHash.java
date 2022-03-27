package com.scprojekt.domain.model.user;

import com.scprojekt.domain.validation.SQLInjectionSafe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import javax.validation.constraints.NotNull;

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
