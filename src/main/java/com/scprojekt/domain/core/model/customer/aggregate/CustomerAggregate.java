package com.scprojekt.domain.core.model.customer.aggregate;

import com.scprojekt.domain.core.model.customer.entity.Customer;
import com.scprojekt.domain.core.model.customer.entity.CustomerType;
import com.scprojekt.domain.core.model.customer.event.CustomerUpdatedEvent;
import com.scprojekt.domain.core.model.customer.exception.CustomerCreationException;
import com.scprojekt.domain.core.shared.event.DomainEventPublisher;

import java.util.UUID;

/**
 * CustomerAggregate is the aggregate root for the Customer domain.
 * It encapsulates the Customer entity and enforces all invariants and business rules related to customers.
 * 
 * This class follows the DDD aggregate pattern, ensuring that all changes to the Customer and its
 * associated value objects go through the aggregate root, maintaining consistency.
 */
public class CustomerAggregate {
    
    private final Customer customer;
    
    /**
     * Private constructor to enforce creation through factory methods
     * 
     * @param customer The customer entity
     */
    private CustomerAggregate(Customer customer) {
        this.customer = customer;
    }
    
    /**
     * Creates a new customer aggregate with the given parameters
     *
     * @param customerName The customer name
     * @param customerSurename The customer surename
     * @param customerType The customer type
     * @return A new CustomerAggregate instance
     * @throws CustomerCreationException if validation fails
     */
    public static CustomerAggregate createCustomer(String customerName, String customerSurename, CustomerType customerType) {
        // Create the customer
        Customer customer = Customer.createCustomer(customerName, customerSurename, customerType);
        
        // Create the aggregate
        return new CustomerAggregate(customer);
    }
    
    /**
     * Creates a customer aggregate from an existing customer entity
     * 
     * @param customer The customer entity
     * @return A new CustomerAggregate instance
     */
    public static CustomerAggregate fromCustomer(Customer customer) {
        return new CustomerAggregate(customer);
    }
    
    /**
     * Gets the underlying customer entity
     * 
     * @return The customer entity
     */
    public Customer getCustomer() {
        return customer;
    }
    
    /**
     * Gets the customer's unique identifier
     * 
     * @return The customer's UUID
     */
    public UUID getCustomerId() {
        return customer.getCustomerNumber().getUuid();
    }
    
    /**
     * Updates the customer with new information
     *
     * @param newCustomerName The new customer name
     * @param newCustomerSurename The new customer surename
     * @param newCustomerType The new customer type
     */
    public void updateCustomerInfo(String newCustomerName, String newCustomerSurename, CustomerType newCustomerType) {
        customer.updateCustomerInfo(newCustomerName, newCustomerSurename, newCustomerType);
    }
    
    /**
     * Updates the customer's address information
     *
     * @param street1 The first street line
     * @param street2 The second street line (optional)
     * @param street3 The third street line (optional)
     * @param zip The ZIP/postal code
     * @param city The city
     */
    public void updateAddress(String street1, String street2, String street3, String zip, String city) {
        customer.setCustomerStreet1(street1);
        customer.setCustomerStreet2(street2);
        customer.setCustomerStreet3(street3);
        customer.setCustomerZip(zip);
        customer.setCustomerCity(city);
        
        // Publish domain event
        DomainEventPublisher.publish(new CustomerUpdatedEvent(this.getCustomerId(), customer.getCustomerName()));
    }
    
    /**
     * Updates the customer's title and salutation
     *
     * @param title The title (e.g., "Dr.", "Prof.")
     * @param salutation The salutation (e.g., "Mr.", "Mrs.", "Ms.")
     */
    public void updateTitleAndSalutation(String title, String salutation) {
        customer.setCustomerTitel(title);
        customer.setCustomerSalutation(salutation);
        
        // Publish domain event
        DomainEventPublisher.publish(new CustomerUpdatedEvent(this.getCustomerId(), customer.getCustomerName()));
    }
    
    /**
     * Disables the customer
     */
    public void disable() {
        customer.disable();
    }
    
    /**
     * Enables the customer
     */
    public void enable() {
        customer.setEnabled(true);
        
        // Publish domain event
        DomainEventPublisher.publish(new CustomerUpdatedEvent(this.getCustomerId(), customer.getCustomerName()));
    }
    
    /**
     * Checks if the customer is enabled
     * 
     * @return true if the customer is enabled
     */
    public boolean isEnabled() {
        return customer.isEnabled();
    }
    
    /**
     * Gets the customer name
     * 
     * @return The customer name
     */
    public String getCustomerName() {
        return customer.getCustomerName();
    }
    
    /**
     * Gets the customer surename
     * 
     * @return The customer surename
     */
    public String getCustomerSurename() {
        return customer.getCustomerSurename();
    }
    
    /**
     * Gets the customer type
     * 
     * @return The customer type
     */
    public CustomerType getCustomerType() {
        return customer.getCustomerType();
    }
}