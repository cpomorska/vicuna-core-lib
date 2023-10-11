package com.scprojekt.domain.core.model.customer.entity;

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
@Table(name = "kostentyp")
public class CostType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="kostentypid")
    long costTypeId;

    @NoSQLInjection
    @Column(name="kostentyp")
    String costKostenType;

    @NoSQLInjection
    @Column(name="kostentypbeschreibung")
    String costTypeDescription;
}
