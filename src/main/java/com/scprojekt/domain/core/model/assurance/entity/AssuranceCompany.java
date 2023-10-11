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
@Table(name = "versicherungsfirma")
public class AssuranceCompany extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="versicherungsfirmaid")
    long assuranceCompanyId;
  
    @NoSQLInjection
    @Column(name="versicherungsfirmatyp")
    String assuranceCompanyType;

    @NoSQLInjection
    @Column(name="versicherungsfirmabeschreibung")
    String assuranceCompanyTypeDescription;
}
