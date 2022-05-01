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
