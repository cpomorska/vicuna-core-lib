package com.scprojekt.domain.model;

import com.scprojekt.domain.validation.SQLInjectionSafe;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "kostentyp")
public class CostType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="kostentypid")
    long costTypeId;

    @SQLInjectionSafe
    @Column(name="kostentyp")
    String costKostenType;

    @SQLInjectionSafe
    @Column(name="kostentypbeschreibung")
    String costTypeDescription;
}
