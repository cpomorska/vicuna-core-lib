package com.scprojekt.domain.entities;

import com.scprojekt.domain.validation.SQLInjectionSafe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "kunde")
public class Customer {

    private static final long serialVersionUID = 1962843934972368188L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="kundenid")
    private
    long customerId;

    @NotNull
    @SQLInjectionSafe
    @Column(unique = true, name="kundenummer")
    private String customerNumber;

    @OneToOne(orphanRemoval = true, targetEntity = CustomerType.class, cascade = CascadeType.ALL)
    private CustomerType customerType;

    @SQLInjectionSafe
    @Column(name="kundentitel")
    private
    String customerTitel;

    @SQLInjectionSafe
    @Column(name="kundenanrede")
    private
    String customerSalutation;

    @SQLInjectionSafe
    @Column(name="kundenname")
    private
    String customerName;

    @SQLInjectionSafe
    @Column(name="kundenmittelname")
    private
    String customerMiddlename;

    @SQLInjectionSafe
    @Column(name="kundenvorname")
    private
    String customerSurename;

    @SQLInjectionSafe
    @Column(name="kundenstrasse1")
    private
    String customerStreet1;

    @SQLInjectionSafe
    @Column(name="kundenstrasse2")
    private
    String customerStreet2;

    @SQLInjectionSafe
    @Column(name="kundenstrasse3")
    private
    String customerStreet3;

    @SQLInjectionSafe
    @Column(name="kundenplz")
    private
    String customerZip;
    @SQLInjectionSafe
    @Column(name="kundenstadt")
    private
    String customerCity;
}
