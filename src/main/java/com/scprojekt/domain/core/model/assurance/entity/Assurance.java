package com.scprojekt.domain.core.model.assurance.entity;

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
@Table(name = "versicherung")
public class Assurance extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="versicherungsid")
    long assuranceId;

    @NoSQLInjection
    @Column(name="versicherungstyp")
    String assuranceType;

    @NoSQLInjection
    @Column(name="versicherungstyp")
    String assuranceType;

    @Column(name="versicherungstypbeschreibung")
    String assuranceTypeDescription;
}
