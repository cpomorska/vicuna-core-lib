package com.scprojekt.domain.model.banking;


import com.scprojekt.domain.validation.SQLInjectionSafe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
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
