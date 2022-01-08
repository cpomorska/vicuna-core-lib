package com.scprojekt.domain.model;


import com.scprojekt.domain.validation.SQLInjectionSafe;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "kundentyp")
public class CustomerType {

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
