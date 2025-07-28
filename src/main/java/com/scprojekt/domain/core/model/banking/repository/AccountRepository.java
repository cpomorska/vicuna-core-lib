package com.scprojekt.domain.core.model.banking.repository;

import com.scprojekt.domain.core.model.banking.entity.Account;
import com.scprojekt.domain.core.model.banking.entity.AccountType;
import com.scprojekt.domain.core.shared.database.BaseRepository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for Account entities.
 * Defines the contract for data access operations related to accounts.
 */
public interface AccountRepository extends BaseRepository<Account> {
    
    /**
     * Find an account by its UUID
     * 
     * @param uuid The UUID to search for
     * @return The account if found, null otherwise
     */
    Account findByUUID(UUID uuid);
    
    /**
     * Find accounts by account type
     * 
     * @param accountType The account type to search for
     * @return List of accounts with the specified type
     */
    List<Account> findByType(AccountType accountType);
    
    /**
     * Find accounts by account holder name
     * 
     * @param accountHolderName The account holder name to search for
     * @return List of accounts with the specified holder name
     */
    List<Account> findByAccountHolderName(String accountHolderName);
    
    /**
     * Find accounts by account number value
     * 
     * @param accountNumber The account number to search for
     * @return The account if found, null otherwise
     */
    Account findByAccountNumber(String accountNumber);
    
    /**
     * Find all active accounts
     * 
     * @return List of all active accounts
     */
    List<Account> findAllActive();
    
    /**
     * Find all inactive accounts
     * 
     * @return List of all inactive accounts
     */
    List<Account> findAllInactive();
    
    /**
     * Find accounts created by a specific user
     * 
     * @param createdBy The UUID of the user who created the accounts
     * @return List of accounts created by the specified user
     */
    List<Account> findByCreatedBy(UUID createdBy);
}
