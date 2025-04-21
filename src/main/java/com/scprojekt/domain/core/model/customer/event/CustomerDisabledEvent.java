package com.scprojekt.domain.core.model.customer.event;

import com.scprojekt.domain.core.shared.event.DomainEvent;

import java.util.UUID;

/**
 * Domain event that is published when a customer is disabled.
 * This event can be used by other bounded contexts that need to react to customer disabling.
 */
public class CustomerDisabledEvent extends DomainEvent {
    private final UUID customerId;
    private final String customerName;
    
    /**
     * Creates a new customer disabled event
     * 
     * @param customerId The ID of the disabled customer
     * @param customerName The name of the disabled customer
     */
    public CustomerDisabledEvent(UUID customerId, String customerName) {
        super();
        this.customerId = customerId;
        this.customerName = customerName;
    }
    
    /**
     * Gets the ID of the disabled customer
     * 
     * @return The customer ID
     */
    public UUID getCustomerId() {
        return customerId;
    }
    
    /**
     * Gets the name of the disabled customer
     * 
     * @return The customer name
     */
    public String getCustomerName() {
        return customerName;
    }
}