package com.scprojekt.domain.model.user;

import com.scprojekt.domain.validation.SQLInjectionSafe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @ManyToMany(targetEntity = UserType.class, cascade = CascadeType.ALL)
    List<UserType> userType;

    @SQLInjectionSafe
    @Column(name="benutzername")
    String userName;

    @NotNull
    @Embedded
    UserNumber userNumber;

    @NotNull
    @SQLInjectionSafe
    @Column(name="benutzerdescription")
    String userDescription;

    @NotNull
    @SQLInjectionSafe
    @Column(name="benutzerhash")
    String userhash;

    @NotNull
    @SQLInjectionSafe
    @Column(name="benutzersalz")
    String usersalt;

}
