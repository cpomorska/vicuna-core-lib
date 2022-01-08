package com.scprojekt.domain.model;

import com.scprojekt.domain.validation.SQLInjectionSafe;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "versicherung")
public class Assurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="versicherungsid")
    long assuranceId;

    @SQLInjectionSafe
    @Column(name="versicherungstyp")
    String assuranceType;

    @SQLInjectionSafe
    @Column(name="versicherungstypbeschreibung")
    String assuranceTypeDescription;
}
