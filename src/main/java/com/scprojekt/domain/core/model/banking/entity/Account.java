package com.scprojekt.domain.core.model.banking.entity;

import com.scprojekt.domain.core.model.banking.dto.AccountNumber;
import com.scprojekt.domain.core.model.banking.dto.Money;
import com.scprojekt.domain.core.shared.database.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Account entity representing a bank account in the system.
 * This entity contains all the data and basic operations related to an account.
 */
@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="accountid")
    private
    long accountId;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "value", column = @Column(name = "account_number", unique = true)),
        @AttributeOverride(name = "uuid", column = @Column(name = "account_uuid", unique = true))
    })
    private AccountNumber accountNumber;

    @Column(name="kundenid")
    private
    long customerId;

    @NotNull
    @Column(name = "account_holder_name")
    private String accountHolderName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_type_id")
    private AccountType accountType;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "amount", column = @Column(name = "balance")),
        @AttributeOverride(name = "currency", column = @Column(name = "currency"))
    })
    private Money balance;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "amount", column = @Column(name = "overdraft_limit")),
        @AttributeOverride(name = "currency", column = @Column(name = "overdraft_currency"))
    })
    private Money overdraftLimit;

    @Column(name = "is_active")
    private boolean active = true;

    @Column(name = "created_by")
    private UUID createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "last_transaction_at")
    private LocalDateTime lastTransactionAt;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions = new ArrayList<>();

    /**
     * Factory method to create a new account
     */
    public static Account createAccount(String accountHolderName, AccountType accountType, 
                                      Money initialBalance, Money overdraftLimit, UUID createdBy) {
        Account account = new Account();
        account.accountNumber = AccountNumber.generate();
        account.accountHolderName = accountHolderName;
        account.accountType = accountType;
        account.balance = initialBalance != null ? initialBalance : Money.zero(accountType.getDefaultCurrency());
        account.overdraftLimit = overdraftLimit != null ? overdraftLimit : Money.zero(accountType.getDefaultCurrency());
        account.active = true;
        account.createdBy = createdBy;
        account.createdAt = LocalDateTime.now();
        account.lastTransactionAt = LocalDateTime.now();
        return account;
    }

    /**
     * Disables the account
     */
    @Override
    public void disable() {
        this.active = false;
    }

    /**
     * Enables the account
     */
    @Override
    public void enable() {
        this.active = true;
    }

    /**
     * Updates account information
     */
    public void updateAccountInfo(String newAccountHolderName, AccountType newAccountType) {
        this.accountHolderName = newAccountHolderName;
        this.accountType = newAccountType;
    }

    /**
     * Checks if the account is enabled/active
     */
    @Override
    public boolean isEnabled() {
        return this.active;
    }
}