package com.scprojekt.domain.core.model.assurance;

import com.scprojekt.domain.core.shared.database.SQLInjectionSafe;
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
