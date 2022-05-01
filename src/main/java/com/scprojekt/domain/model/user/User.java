package com.scprojekt.domain.model.user;

import com.scprojekt.domain.validation.SQLInjectionSafe;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
