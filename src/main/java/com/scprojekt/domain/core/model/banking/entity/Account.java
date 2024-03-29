package com.scprojekt.domain.core.model.banking.entity;


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
@Table(name = "konto")
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="kontoid")
    long accountId;

    @OneToOne(orphanRemoval = true, targetEntity = AccountType.class, cascade = CascadeType.ALL)
    AccountType accountType;

    @NoSQLInjection
    @Column(name="kontobeschreibung")
    String accountDescription;

    @NoSQLInjection
    @Column(name="kontonnummer")
    String accountNumber;

}
