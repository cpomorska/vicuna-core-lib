package com.scprojekt.domain.model;

import com.scprojekt.domain.validation.SQLInjectionSafe;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "benutzertyp")
public class UserType {

    @Id
    @Column(name="benutzertypid")
    long userTypeId;

    @SQLInjectionSafe
    @Column(name="benutzerrolle")
    String userRoleType;

    @SQLInjectionSafe
    @Column(name="benutzertypbeschreibung")
    String userTypeDescription;

    @ManyToMany
    List<User> user;
}
