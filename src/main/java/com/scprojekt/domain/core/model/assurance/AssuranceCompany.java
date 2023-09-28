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
