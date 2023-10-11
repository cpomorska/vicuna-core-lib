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
@Table(name = "versicherungstyp")
public class AssuranceType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="versicherungstypid")
    long assuranceTypeId;

    @NoSQLInjection
    @Column(name="versicherungstyp")
    String assuranceVersicherungsType;

    @NoSQLInjection
    @Column(name="versicherungstypbeschreibung")
    String assuranceTypeDescription;
}
