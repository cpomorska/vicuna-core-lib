package com.scprojekt.domain.entities;

import com.scprojekt.domain.validation.SQLInjectionSafe;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "versicherungstyp")
public class AssuranceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="versicherungstypid")
    long assuranceTypeId;

    @SQLInjectionSafe
    @Column(name="versicherungstyp")
    String assuranceVersicherungsType;

    @SQLInjectionSafe
    @Column(name="versicherungstypbeschreibung")
    String assuranceTypeDescription;
}
