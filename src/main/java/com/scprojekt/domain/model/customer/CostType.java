package com.scprojekt.domain.model.customer;

import com.scprojekt.domain.validation.SQLInjectionSafe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
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
