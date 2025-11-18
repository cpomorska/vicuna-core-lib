package com.scprojekt.domain.core.model.customer.repository;

import com.scprojekt.domain.core.model.customer.entity.Customer;
import com.scprojekt.domain.core.model.customer.entity.CustomerType;
import com.scprojekt.domain.core.shared.database.BaseRepository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for Customer entities.
 */
public interface CustomerRepository extends BaseRepository<Customer> {
    
    /**
     * Finds a customer by its UUID
     * 
     * @param uuid The UUID to search for
     * @return The customer with the specified UUID, or null if not found
     */
    Customer findByUUID(UUID uuid);
    
    /**
     * Finds customers by name
     * 
     * @param name The name to search for
     * @return A list of customers with the specified name
     */
    List<Customer> findByName(String name);
    
    /**
     * Finds customers by type
     * 
     * @param customerType The customer type to search for
     * @return A list of customers with the specified type
     */
    List<Customer> findByType(CustomerType customerType);
}