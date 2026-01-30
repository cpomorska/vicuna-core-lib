package com.scprojekt.domain.core.model.customer.event;

import com.scprojekt.domain.core.shared.event.DomainEvent;
import lombok.Getter;

import java.util.UUID;

/**
 * Domain event that is published when a customer is updated.
 * This event can be used by other bounded contexts that need to react to customer updates.
 */
@Getter
public class CustomerUpdatedEvent extends DomainEvent {
    /**
     *  Gets the ID of the updated customer
     */
    private final UUID customerId;
    /**
     *  Gets the name of the updated customer
     */
    private final String customerName;
    
    /**
     * Creates a new customer updated event
     * 
     * @param customerId The ID of the updated customer
     * @param customerName The name of the updated customer
     */
    public CustomerUpdatedEvent(UUID customerId, String customerName) {
        super();
        this.customerId = customerId;
        this.customerName = customerName;
    }

}