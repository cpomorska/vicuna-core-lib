package com.scprojekt.domain.core.model.user.entity;

import com.scprojekt.domain.core.shared.database.BaseEntity;
import com.scprojekt.domain.core.shared.database.NoSQLInjection;
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
@Table(name = "user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "userid_seq", sequenceName = "USERID_SEQ", allocationSize = 1)
    @Column(name = "id")
    long id;

    @NotEmpty
    @ManyToMany(cascade = CascadeType.ALL)
    @CollectionTable
    private List<UserType> userTypes;

    @Embedded
    UserNumber userNumber;

    @Embedded
    UserHash userHash;

    @NoSQLInjection
    @Column(name = "username", nullable = false)
    String userName;

    @NotNull
    @NoSQLInjection
    @Column(name = "description")
    String userDescription;
}
