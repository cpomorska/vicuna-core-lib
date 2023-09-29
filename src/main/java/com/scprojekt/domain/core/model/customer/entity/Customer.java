package com.scprojekt.domain.core.model.customer.entity;

import com.scprojekt.domain.core.shared.database.BaseEntity;
import com.scprojekt.domain.core.shared.database.SQLInjectionSafe;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "kunde")
public class Customer extends BaseEntity {

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
