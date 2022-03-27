package com.scprojekt.domain.model.assurance;

import com.scprojekt.domain.validation.SQLInjectionSafe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
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
