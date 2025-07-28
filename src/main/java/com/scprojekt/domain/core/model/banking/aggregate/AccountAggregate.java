package com.scprojekt.domain.core.model.banking.aggregate;


import com.scprojekt.domain.core.model.banking.entity.Account;
import com.scprojekt.domain.core.model.banking.entity.AccountType;
import com.scprojekt.domain.core.model.banking.entity.Transaction;
import com.scprojekt.domain.core.model.banking.event.AccountCreatedEvent;
import com.scprojekt.domain.core.model.banking.event.AccountDisabledEvent;
import com.scprojekt.domain.core.model.banking.event.AccountUpdatedEvent;
import com.scprojekt.domain.core.model.banking.event.TransactionProcessedEvent;
import com.scprojekt.domain.core.model.banking.exception.AccountCreationException;
import com.scprojekt.domain.core.model.banking.exception.InsufficientFundsException;
import com.scprojekt.domain.core.model.banking.exception.InvalidAccountOperationException;
import com.scprojekt.domain.core.model.banking.dto.Money;
import com.scprojekt.domain.core.shared.aggregate.BaseAggregate;
import com.scprojekt.domain.core.shared.event.DomainEventPublisher;
import lombok.Getter;
import lombok.extern.java.Log;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * AccountAggregate is the aggregate root for the Banking domain.
 * It encapsulates the Account entity and enforces all invariants and business rules related to accounts.
 * 
 * This class follows the DDD aggregate pattern, ensuring that all changes to the Account and its
 * associated value objects go through the aggregate root, maintaining consistency.
 */
@Log
@Getter
public final class AccountAggregate implements BaseAggregate {

    private final Account account;
    
    /**
     * Private constructor to enforce creation through factory methods
     * 
     * @param account The account entity
     */
    private AccountAggregate(Account account) {
        this.account = account;
    }
    
    /**
     * Creates a new account aggregate with the given parameters
     *
     * @param accountHolderName The account holder name
     * @param accountType The account type
     * @param initialBalance The initial balance (can be null for zero balance)
     * @param overdraftLimit The overdraft limit (can be null for zero limit)
     * @param createdBy The UUID of the user creating the account
     * @return A new AccountAggregate instance
     * @throws 
     */
    public static AccountAggregate createAccount(String accountHolderName, AccountType accountType,
                                                 Money initialBalance, Money overdraftLimit, UUID createdBy) {
        // Create the account
        Account account = Account.createAccount(accountHolderName, accountType, initialBalance, overdraftLimit, createdBy);
        
        // Create the aggregate
        AccountAggregate aggregate = new AccountAggregate(account);
        
        // Validate the account
        aggregate.validate();
        
        // Publish domain event
        DomainEventPublisher.publish(new AccountCreatedEvent(
            account.getAccountNumber().getUuid(),
            account.getCustomerId()
        ));
        
        return aggregate;
    }
    
    /**
     * Creates an account aggregate from an existing account entity
     * 
     * @param account The account entity
     * @return A new AccountAggregate instance
     */
    public static AccountAggregate fromAccount(Account account) {
        return new AccountAggregate(account);
    }
    
    /**
     * Gets the underlying account entity
     * 
     * @return The account entity
     */

    /**
     * Gets the account's unique identifier
     * 
     * @return The account's UUID
     */
    public UUID getAccountId() {
        return account.getAccountNumber().getUuid();
    }
    
    /**
     * Updates the account holder information
     *
     * @param newAccountHolderName The new account holder name
     * @param newAccountType The new account type
     */
    public void updateAccountInfo(String newAccountHolderName, AccountType newAccountType) {
        account.setAccountHolderName(newAccountHolderName);
        account.setAccountType(newAccountType);
        
        // Validate the updated account
        validate();
        
        // Publish domain event
        DomainEventPublisher.publish(new AccountUpdatedEvent(this.getAccountId(), account.getCustomerId()));
    }
    
    /**
     * Process a debit transaction
     *
     * @param amount The amount to debit
     * @param description The transaction description
     * @param transactionReference The transaction reference
     * @return The created transaction
     * @throws InsufficientFundsException if there are insufficient funds
     */
    public Transaction debit(Money amount, String description, String transactionReference) {
        validateActiveAccount();
        validateAmount(amount);

        Money availableBalance = calculateAvailableBalance();
        if (amount.isGreaterThan(availableBalance)) {
            throw new InsufficientFundsException(
                String.format("Insufficient funds. Available: %s, Requested: %s", 
                    availableBalance, amount)
            );
        }

        account.setBalance(account.getBalance().subtract(amount));
        account.setLastTransactionAt(LocalDateTime.now());

        Transaction transaction = Transaction.createDebitTransaction(account, amount, description, transactionReference);
        account.getTransactions().add(transaction);

        // Publish domain event
        DomainEventPublisher.publish(new TransactionProcessedEvent(
            account.getAccountNumber().getValue(),
            transaction.getTransactionId(),
            "DEBIT",
            amount.getAmount(),
            account.getBalance().getAmount(),
            description
        ));

        return transaction;
    }
    
    /**
     * Process a credit transaction
     *
     * @param amount The amount to credit
     * @param description The transaction description
     * @param transactionReference The transaction reference
     * @return The created transaction
     */
    public Transaction credit(Money amount, String description, String transactionReference) {
        validateActiveAccount();
        validateAmount(amount);

        account.setBalance(account.getBalance().add(amount));
        account.setLastTransactionAt(LocalDateTime.now());

        Transaction transaction = Transaction.createCreditTransaction(account, amount, description, transactionReference);
        account.getTransactions().add(transaction);

        // Publish domain event
        DomainEventPublisher.publish(new TransactionProcessedEvent(
            account.getAccountNumber().getValue(),
            transaction.getTransactionId(),
            "CREDIT",
            amount.getAmount(),
            account.getBalance().getAmount(),
            description
        ));

        return transaction;
    }
    
    /**
     * Transfer money to another account aggregate
     *
     * @param targetAccount The target account aggregate
     * @param amount The amount to transfer
     * @param description The transfer description
     * @return The debit transaction from this account
     */
    public Transaction transferTo(AccountAggregate targetAccount, Money amount, String description) {
        validateActiveAccount();
        targetAccount.validateActiveAccount();
        validateAmount(amount);

        if (!this.account.getBalance().getCurrency().equals(targetAccount.account.getBalance().getCurrency())) {
            throw new InvalidAccountOperationException("Currency mismatch between accounts");
        }

        // Process debit on source account
        Transaction debitTransaction = this.debit(amount, 
            String.format("Transfer to %s: %s", targetAccount.getAccountNumber(), description),
            generateTransferReference());

        // Process credit on target account
        targetAccount.credit(amount, 
            String.format("Transfer from %s: %s", this.getAccountNumber(), description),
            debitTransaction.getTransactionReference());

        return debitTransaction;
    }
    
    /**
     * Updates the overdraft limit
     *
     * @param newOverdraftLimit The new overdraft limit
     */
    public void updateOverdraftLimit(Money newOverdraftLimit) {
        validateActiveAccount();
        if (newOverdraftLimit.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidAccountOperationException("Overdraft limit cannot be negative");
        }
        account.setOverdraftLimit(newOverdraftLimit);
        
        // Publish domain event
        DomainEventPublisher.publish(new AccountUpdatedEvent(this.getAccountId(), account.getCustomerId()));
    }
    
    /**
     * Disables the account
     */
    public void disable() {
        account.setActive(false);
        
        // Publish domain event
        DomainEventPublisher.publish(new AccountDisabledEvent(
            account.getAccountNumber().getUuid(),
            account.getCustomerId()
        ));
    }
    
    /**
     * Enables the account
     */
    public void enable() {
        account.setActive(true);
        
        // Publish domain event
        DomainEventPublisher.publish(new AccountUpdatedEvent(this.getAccountId(), account.getCustomerId()));
    }
    
    /**
     * Checks if the account is active
     * 
     * @return true if the account is active
     */
    public boolean isActive() {
        return account.isActive();
    }
    
    /**
     * Gets the account holder name
     * 
     * @return The account holder name
     */
    public String getAccountHolderName() {
        return account.getAccountHolderName();
    }
    
    /**
     * Gets the account number
     * 
     * @return The account number
     */
    public String getAccountNumber() {
        return account.getAccountNumber().getValue();
    }
    
    /**
     * Gets the account type
     * 
     * @return The account type
     */
    public AccountType getAccountType() {
        return account.getAccountType();
    }
    
    /**
     * Gets the current balance
     * 
     * @return The current balance
     */
    public Money getBalance() {
        return account.getBalance();
    }
    
    /**
     * Gets the overdraft limit
     * 
     * @return The overdraft limit
     */
    public Money getOverdraftLimit() {
        return account.getOverdraftLimit();
    }
    
    /**
     * Calculate available balance including overdraft
     * 
     * @return The available balance
     */
    public Money calculateAvailableBalance() {
        return account.getBalance().add(account.getOverdraftLimit());
    }
    
    /**
     * Check if account is overdrawn
     * 
     * @return true if the account is overdrawn
     */
    public boolean isOverdrawn() {
        return account.getBalance().getAmount().compareTo(BigDecimal.ZERO) < 0;
    }
    
    /**
     * Get overdraft usage
     * 
     * @return The amount of overdraft currently used
     */
    public Money getOverdraftUsage() {
        if (!isOverdrawn()) {
            return Money.zero(account.getBalance().getCurrency());
        }
        return Money.of(account.getBalance().getAmount().abs(), account.getBalance().getCurrency());
    }
    
    /**
     * Gets an unmodifiable view of the transactions
     *
     * @return Unmodifiable list of transactions
     */
    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(account.getTransactions());
    }
    
    /**
     * Validates that the account meets all business rules
     *
     * @throws AccountCreationException if validation fails
     */
    private void validate() {
        if (account.getAccountHolderName() == null || account.getAccountHolderName().trim().isEmpty()) {
            throw new AccountCreationException("Account holder name cannot be empty");
        }
        
        if (account.getAccountType() == null) {
            throw new AccountCreationException("Account must have an account type");
        }
        
        if (account.getBalance() == null) {
            throw new AccountCreationException("Account balance cannot be null");
        }
        
        if (account.getOverdraftLimit() == null) {
            throw new AccountCreationException("Account overdraft limit cannot be null");
        }
    }
    
    // Private helper methods
    private void validateActiveAccount() {
        if (!account.isActive()) {
            throw new InvalidAccountOperationException("Account is not active");
        }
    }

    private void validateAmount(Money amount) {
        if (amount == null || amount.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAccountOperationException("Amount must be positive");
        }
    }

    private String generateTransferReference() {
        return "TXF-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
