package com.scprojekt.domain.entities;

import com.scprojekt.domain.validation.SQLInjectionSafe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

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

    @ManyToMany(targetEntity = UserType.class, cascade = CascadeType.ALL)
    List<UserType> userType;

    @SQLInjectionSafe
    @Column(name="benutzername")
    String userName;

    @Column(name="benutzernummer", unique = true, columnDefinition = "BINARY(16)")
    UUID userNumber;

    @SQLInjectionSafe
    @Column(name="benutzerdescription")
    String userDescription;

    @SQLInjectionSafe
    @Column(name="benutzerhash")
    String userhash;

    @SQLInjectionSafe
    @Column(name="benutzersalz")
    String usersalt;

}
