package com.scprojekt.domain.core.model.user.entity;

import com.scprojekt.domain.core.shared.database.BaseEntity;
import com.scprojekt.domain.core.shared.database.NoSQLInjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "benutzertyp")
public class UserType extends BaseEntity {

    @Id
    @Column(name="benutzertypid")
    long userTypeId;

    @NoSQLInjection
    @Column(name="benutzerrolle")
    String userRoleType;

    @NoSQLInjection
    @Column(name="benutzertypbeschreibung")
    String userTypeDescription;
}
