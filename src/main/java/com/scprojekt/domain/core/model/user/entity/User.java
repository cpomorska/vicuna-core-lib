package com.scprojekt.domain.core.model.user.entity;

import com.scprojekt.domain.core.shared.database.SQLInjectionSafe;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "benutzer")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name= "benutzerid_seq", sequenceName="BENUTZERID_SEQ", allocationSize=1)
    @Column(name="benutzerid")
    long userId;

    @NotEmpty
    @ManyToMany(targetEntity = UserType.class, cascade = CascadeType.MERGE)
    @JoinColumn(nullable = false)
    List<UserType> userType;

    @SQLInjectionSafe
    @Column(name="benutzername", nullable = false)
    String userName;

    @Embedded
    @Column(nullable = false)
    UserNumber userNumber;

    @NotNull
    @SQLInjectionSafe
    @Column(name="benutzerdescription")
    String userDescription;

    @Embedded
    UserHash userHash;
}
