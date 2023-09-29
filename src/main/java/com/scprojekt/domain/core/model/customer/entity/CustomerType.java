package com.scprojekt.domain.core.model.customer.entity;


import com.scprojekt.domain.core.shared.database.BaseEntity;
import com.scprojekt.domain.core.shared.database.SQLInjectionSafe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "kundentyp")
public class CustomerType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="kundentypid")
    long customerTypeId;

    @SQLInjectionSafe
    @Column(name="kundentyp")
    String customerRoleType;

    @SQLInjectionSafe
    @Column(name="kundentypbeschreibung")
    String customerTypeDescription;
}
