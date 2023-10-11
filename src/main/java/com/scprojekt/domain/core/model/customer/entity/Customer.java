package com.scprojekt.domain.core.model.customer.entity;

import com.scprojekt.domain.core.shared.database.BaseEntity;
import com.scprojekt.domain.core.shared.database.NoSQLInjection;
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
    @NoSQLInjection
    @Column(unique = true, name="kundenummer")
    private String customerNumber;

    @OneToOne(orphanRemoval = true, targetEntity = CustomerType.class, cascade = CascadeType.ALL)
    private CustomerType customerType;

    @NoSQLInjection
    @Column(name="kundentitel")
    private
    String customerTitel;

    @NoSQLInjection
    @Column(name="kundenanrede")
    private
    String customerSalutation;
  
    @NoSQLInjection
    @Column(name="kundenname")
    private
    String customerName;

    @NoSQLInjection
    @Column(name="kundenmittelname")
    private
    String customerMiddlename;

    @NoSQLInjection
    @Column(name="kundenvorname")
    private
    String customerSurename;

    @NoSQLInjection
    @Column(name="kundenstrasse1")
    private
    String customerStreet1;

    @NoSQLInjection
    @Column(name="kundenstrasse2")
    private
    String customerStreet2;

    @NoSQLInjection
    @Column(name="kundenstrasse3")
    private
    String customerStreet3;

    @NoSQLInjection
    @Column(name="kundenplz")
  
    private
    String customerZip;

    @NoSQLInjection
    @Column(name="kundenstadt")
    private
    String customerCity;
}
