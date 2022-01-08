package com.scprojekt.domain.model;


import com.scprojekt.domain.validation.SQLInjectionSafe;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "kontotyp")
public class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="kontotypid")
    long accountTypeId;

    @SQLInjectionSafe
    @Column(name="kontotyp")
    String accountKontoType;

    @SQLInjectionSafe
    @Column(name="kontotypbeschreibung")
    String accountTypeDescription;
}
