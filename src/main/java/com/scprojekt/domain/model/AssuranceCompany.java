package com.scprojekt.domain.model;

import com.scprojekt.domain.validation.SQLInjectionSafe;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "versicherungsfirma")
public class AssuranceCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="versicherungsfirmaid")
    long assuranceCompanyId;

    @SQLInjectionSafe
    @Column(name="versicherungsfirmatyp")
    String assuranceCompanyType;

    @SQLInjectionSafe
    @Column(name="versicherungsfirmabeschreibung")
    String assuranceCompanyTypeDescription;
}
