package com.scprojekt.domain.core.model.user.entity;

import com.scprojekt.domain.core.shared.database.NoSQLInjection;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_hash_table")
public class UserHash {
    @NotNull
    @NoSQLInjection
    @Column(name = "user_hash")
    private String hash;

    @NotNull
    @NoSQLInjection
    @Column(name = "user_salt")
    private String salt;
}
