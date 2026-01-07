package com.scprojekt.domain.core.model.banking.entity;

import com.scprojekt.domain.core.model.banking.enums.TransactionStatus;
import com.scprojekt.domain.core.model.banking.dto.Money;
import com.scprojekt.domain.core.shared.database.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Transaction entity representing a financial transaction within an account.
 * This entity contains all transaction data and basic operations.
 */
@Entity
@Table(name = "transactions", indexes = {
    @Index(name = "idx_transaction_account", columnList = "account_id"),
    @Index(name = "idx_transaction_id", columnList = "transaction_id"),
    @Index(name = "idx_transaction_reference", columnList = "transaction_reference"),
    @Index(name = "idx_transaction_processed_at", columnList = "processed_at")
})
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Transaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID transactionKey;

    @NotNull
    @Column(name = "transaction_id", unique = true, length = 50)
    private String transactionId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionStatus.TransactionType type;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "amount", column = @Column(name = "amount", nullable = false)),
        @AttributeOverride(name = "currency", column = @Column(name = "currency", nullable = false))
    })
    private Money amount;
    
    @Column(name = "description", length = 500)
    private String description;
    
    @Column(name = "transaction_reference", length = 100)
    private String transactionReference;
    
    @Column(name = "processed_at", nullable = false)
    private LocalDateTime processedAt;
    
    @Column(name = "balance_after", nullable = false)
    private BigDecimal balanceAfter;
    
    @Column(name = "created_by")
    private UUID createdBy;
    
    @Column(name = "authorized_by")
    private UUID authorizedBy;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TransactionStatus status = TransactionStatus.COMPLETED;
    
    @Column(name = "external_reference", length = 100)
    private String externalReference;
    
    @Column(name = "channel", length = 50)
    private String channel = "SYSTEM";
    
    @Column(name = "notes", length = 1000)
    private String notes;
    
    // Factory methods
    public static Transaction createDebitTransaction(Account account, Money amount, 
                                                   String description, String reference) {
        Transaction transaction = new Transaction();
        transaction.transactionId = generateTransactionId();
        transaction.account = account;
        transaction.type = TransactionStatus.TransactionType.DEBIT;
        transaction.amount = amount;
        transaction.description = description;
        transaction.transactionReference = reference;
        transaction.processedAt = LocalDateTime.now();
        transaction.balanceAfter = account.getBalance().getAmount();
        transaction.status = TransactionStatus.COMPLETED;
        return transaction;
    }
    
    public static Transaction createCreditTransaction(Account account, Money amount, 
                                                    String description, String reference) {
        Transaction transaction = new Transaction();
        transaction.transactionId = generateTransactionId();
        transaction.account = account;
        transaction.type = TransactionStatus.TransactionType.CREDIT;
        transaction.amount = amount;
        transaction.description = description;
        transaction.transactionReference = reference;
        transaction.processedAt = LocalDateTime.now();
        transaction.balanceAfter = account.getBalance().getAmount();
        transaction.status = TransactionStatus.COMPLETED;
        return transaction;
    }
    
    public static Transaction createPendingTransaction(Account account, Money amount,
                                                       TransactionStatus.TransactionType type, String description,
                                                       String reference) {
        Transaction transaction = new Transaction();
        transaction.transactionId = generateTransactionId();
        transaction.account = account;
        transaction.type = type;
        transaction.amount = amount;
        transaction.description = description;
        transaction.transactionReference = reference;
        transaction.processedAt = LocalDateTime.now();
        transaction.balanceAfter = account.getBalance().getAmount();
        transaction.status = TransactionStatus.PENDING;
        return transaction;
    }
    
    /**
     * Marks the transaction as completed
     */
    public void complete() {
        this.status = TransactionStatus.COMPLETED;
    }
    
    /**
     * Marks the transaction as failed
     */
    public void fail(String reason) {
        this.status = TransactionStatus.FAILED;
        this.notes = reason;
    }
    
    /**
     * Marks the transaction as cancelled
     */
    public void cancel(String reason) {
        this.status = TransactionStatus.CANCELLED;
        this.notes = reason;
    }
    
    /**
     * Updates transaction metadata
     */
    public void updateMetadata(String channel, String externalReference, UUID authorizedBy) {
        this.channel = channel;
        this.externalReference = externalReference;
        this.authorizedBy = authorizedBy;
    }
    
    /**
     * Adds notes to the transaction
     */
    public void addNotes(String additionalNotes) {
        if (this.notes == null) {
            this.notes = additionalNotes;
        } else {
            this.notes += "; " + additionalNotes;
        }
    }
    
    /**
     * Checks if the transaction is completed
     */
    public boolean isCompleted() {
        return TransactionStatus.COMPLETED.equals(this.status);
    }
    
    /**
     * Checks if the transaction is pending
     */
    public boolean isPending() {
        return TransactionStatus.PENDING.equals(this.status);
    }
    
    /**
     * Checks if the transaction is failed
     */
    public boolean isFailed() {
        return TransactionStatus.FAILED.equals(this.status);
    }
    
    /**
     * Checks if the transaction is a debit
     */
    public boolean isDebit() {
        return TransactionStatus.TransactionType.DEBIT.equals(this.type);
    }
    
    /**
     * Checks if the transaction is a credit
     */
    public boolean isCredit() {
        return TransactionStatus.TransactionType.CREDIT.equals(this.type);
    }
    
    private static String generateTransactionId() {
        return "TXN-" + UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase();
    }

}
