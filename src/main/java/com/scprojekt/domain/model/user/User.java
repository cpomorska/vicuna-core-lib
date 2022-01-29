package com.scprojekt.domain.model.user;

import com.scprojekt.domain.validation.SQLInjectionSafe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    @Column(nullable = false)
    List<UserType> userType;

    @SQLInjectionSafe
    @Column(name="benutzername", nullable = false)
    String userName;

    @Embedded
    @Column(nullable = false)
    UserNumber userNumber;

    @SQLInjectionSafe
    @Column(name="benutzerdescription")
    String userDescription;

    @Embedded
    UserHash userHash;
}
