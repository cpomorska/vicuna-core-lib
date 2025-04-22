package com.scprojekt.domain.core.model.customer.event;

import com.scprojekt.domain.core.shared.event.DomainEvent;
import lombok.Getter;

import java.util.UUID;

/**
 * Domain event that is published when a new customer is created.
 * This event can be used by other bounded contexts that need to react to customer creation.
 */
@Getter
public class CustomerCreatedEvent extends DomainEvent {
    /**
     *  Gets the ID of the created customer
     */
    private final UUID customerId;
    /**
     *  Gets the name of the created customer
     */
    private final String customerName;
    
    /**
     * Creates a new customer created event
     * 
     * @param customerId The ID of the created customer
     * @param customerName The name of the created customer
     */
    public CustomerCreatedEvent(UUID customerId, String customerName) {
        super();
        this.customerId = customerId;
        this.customerName = customerName;
    }

}