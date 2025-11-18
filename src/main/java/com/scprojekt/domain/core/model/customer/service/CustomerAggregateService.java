package com.scprojekt.domain.core.model.customer.service;

import com.scprojekt.domain.core.model.customer.aggregate.CustomerAggregate;
import com.scprojekt.domain.core.model.customer.entity.CustomerType;
import com.scprojekt.domain.core.model.user.dto.UuidResponse;

import java.util.List;
import java.util.UUID;

/**
 * Domain service for customer management operations using the CustomerAggregate.
 * This service provides methods for customer-specific business operations.
 */
public interface CustomerAggregateService extends CustomerBaseService {

    /**
     * Gets a customer aggregate by its ID
     * 
     * @param id The customer ID
     * @return The customer aggregate
     */
    CustomerAggregate getById(long id);

    /**
     * Gets a customer aggregate by its UUID
     * 
     * @param uuid The customer UUID
     * @return The customer aggregate
     */
    CustomerAggregate getByUuid(UUID uuid);

    /**
     * Creates a new customer in the system
     * 
     * @param customerAggregate The customer aggregate to create
     * @return The UUID of the created customer
     */
    UuidResponse create(CustomerAggregate customerAggregate);

    /**
     * Updates an existing customer in the system
     * 
     * @param customerAggregate The customer aggregate to update
     */
    void update(CustomerAggregate customerAggregate);

    /**
     * Removes a customer from the system
     * 
     * @param customerAggregate The customer aggregate to remove
     */
    void remove(CustomerAggregate customerAggregate);

    /**
     * Finds all customers with the specified customer type
     * 
     * @param customerType The customer type to search for
     * @return A list of matching customer aggregates
     */
    List<CustomerAggregate> findByCustomerType(CustomerType customerType);


    /**
     * Finds all customers with the specified name
     * 
     * @param name The name to search for
     * @return A list of matching customer aggregates
     */
    List<CustomerAggregate> findAllByName(String name);

    /**
     * Registers a new customer in the system
     * 
     * @param customerName The customer name
     * @param customerSurename The customer surename
     * @param customerType The customer type
     * @return The UUID of the created customer
     */
    UuidResponse registerCustomer(String customerName, String customerSurename, CustomerType customerType);

    /**
     * Disables a customer account
     * 
     * @param customerId The customer ID
     */
    void disableCustomer(UUID customerId);

    /**
     * Enables a previously disabled customer account
     * 
     * @param customerId The customer ID
     */
    void enableCustomer(UUID customerId);

    /**
     * Updates a customer's address information
     * 
     * @param customerId The customer ID
     * @param street1 The first street line
     * @param street2 The second street line (optional)
     * @param street3 The third street line (optional)
     * @param zip The ZIP/postal code
     * @param city The city
     */
    void updateCustomerAddress(UUID customerId, String street1, String street2, String street3, String zip, String city);

    /**
     * Updates a customer's title and salutation
     * 
     * @param customerId The customer ID
     * @param title The title (e.g., "Dr.", "Prof.")
     * @param salutation The salutation (e.g., "Mr.", "Mrs.", "Ms.")
     */
    void updateCustomerTitleAndSalutation(UUID customerId, String title, String salutation);

    /**
     * Finds customers by a partial name match
     * 
     * @param namePattern The name pattern to match
     * @return A list of matching customer aggregates
     */
    List<CustomerAggregate> findCustomersByNamePattern(String namePattern);
}
