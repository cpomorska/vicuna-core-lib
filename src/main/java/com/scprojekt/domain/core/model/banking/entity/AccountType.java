package com.scprojekt.domain.core.model.banking.entity;

import com.scprojekt.domain.core.shared.database.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * AccountType entity representing different types of bank accounts.
 */
@Entity
@Table(name = "account_types")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccountType extends BaseEntity {

    @Column(name = "account_type_id", unique = true)
    private Long accountTypeId;

    @NotNull
    @Column(name = "type_name")
    private String typeName;

    @Column(name = "type_description")
    private String typeDescription;

    @Column(name = "default_currency")
    private String defaultCurrency = "USD";

    @Column(name = "minimum_balance")
    private BigDecimal minimumBalance = BigDecimal.ZERO;

    @Column(name = "maximum_overdraft")
    private BigDecimal maximumOverdraft = BigDecimal.ZERO;

    @Column(name = "is_active")
    private boolean active = true;
    @Id
    private Long id;

    /**
     * Factory method to create a new account type
     */
    public static AccountType createAccountType(String typeName, String typeDescription, 
                                              String defaultCurrency, BigDecimal minimumBalance, 
                                              BigDecimal maximumOverdraft) {
        AccountType accountType = new AccountType();
        accountType.typeName = typeName;
        accountType.typeDescription = typeDescription;
        accountType.defaultCurrency = defaultCurrency != null ? defaultCurrency : "USD";
        accountType.minimumBalance = minimumBalance != null ? minimumBalance : BigDecimal.ZERO;
        accountType.maximumOverdraft = maximumOverdraft != null ? maximumOverdraft : BigDecimal.ZERO;
        accountType.active = true;
        return accountType;
    }

    /**
     * Disables the account type
     */
    @Override
    public void disable() {
        this.active = false;
    }

    /**
     * Enables the account type
     */
    @Override
    public void enable() {
        this.active = true;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}