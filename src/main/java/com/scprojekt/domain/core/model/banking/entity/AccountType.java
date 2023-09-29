package com.scprojekt.domain.core.model.banking.entity;


import com.scprojekt.domain.core.shared.database.BaseEntity;
import com.scprojekt.domain.core.shared.database.SQLInjectionSafe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "kontotyp")
public class AccountType extends BaseEntity {

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
