package com.scprojekt.domain.model.assurance;

import com.scprojekt.domain.validation.SQLInjectionSafe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
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
