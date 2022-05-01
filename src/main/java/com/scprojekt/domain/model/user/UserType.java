package com.scprojekt.domain.model.user;

import com.scprojekt.domain.validation.SQLInjectionSafe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
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

    @OneToMany
    List<User> user;
}
