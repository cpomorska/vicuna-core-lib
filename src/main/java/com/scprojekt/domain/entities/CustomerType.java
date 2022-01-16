package com.scprojekt.domain.entities;


import com.scprojekt.domain.validation.SQLInjectionSafe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
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
