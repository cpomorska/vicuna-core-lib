package com.scprojekt.domain.model;


import com.scprojekt.domain.validation.SQLInjectionSafe;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "konto")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="kontoid")
    long accountId;

    @OneToOne(orphanRemoval = true, targetEntity = AccountType.class, cascade = CascadeType.ALL)
    AccountType accountType;

    @SQLInjectionSafe
    @Column(name="kontobeschreibung")
    String accountDescription;

    @SQLInjectionSafe
    @Column(name="kontonnummer")
    String accountNumber;

}
