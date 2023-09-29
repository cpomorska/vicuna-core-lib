package com.scprojekt.domain.core.model.user.entity;

import com.scprojekt.domain.core.shared.database.BaseEntity;
import com.scprojekt.domain.core.shared.database.SQLInjectionSafe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "benutzertyp")
public class UserType extends BaseEntity {

    @Id
    @Column(name="benutzertypid")
    long userTypeId;

    @SQLInjectionSafe
    @Column(name="benutzerrolle")
    String userRoleType;

    @SQLInjectionSafe
    @Column(name="benutzertypbeschreibung")
    String userTypeDescription;

    @OneToMany
    List<User> user;
}
